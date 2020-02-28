package com.chenlink.euterpe.util;


import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;



/**
 * 获取个人南昌公积金
 * @author chenlink
 *
 */
public class NcGjjUtils {

    /**
     * 登录URL
     */
    private static final String LOGIN_URL = "http://www.ncgjj.com.cn:8081/wt-web/login";

    /**
     * 获取验证码URL
     */
    private static final String CHECKCODE_URL = "http://www.ncgjj.com.cn:8081/wt-web/captcha";

    /*
    获取个人信息URL
     */
    private static final String PERSON_JBXX="http://www.ncgjj.com.cn:8081/wt-web/person/jbxx";

    /**
     * 验证公积金账号密码是否正确
     * 因公积金验证码问题，需一一登录
     * @param idCard
     * @param password
     * @return
     * @throws Exception
     */
    public synchronized static JSONObject verify(String idCard, String password) {
        Map<String,String> cookie=new HashMap<String,String>();//存储cookie
        Map param=new HashMap();//存放表单的参数
        NcGjjUtils ng=new NcGjjUtils();
        String captcha=null;//验证码识别结果
        String path=" ";
        String reason=" ";
        int error_code=0;
        try {
            cookie= NcGjjUtils.getLoginHtml();
            captcha=ng.getCaptcha(cookie);
            param.put("logintype","1");
            param.put("username",idCard);
            param.put("password",ng.getRSAutils(password));
            param.put("captcha",captcha);
            param.put("token","");
            cookie= NcGjjUtils.getLoginSuccess(cookie,param);
            Set<String> keys = cookie.keySet();
            if(keys.size()>0) {
                for (String key : keys) {
                    if (key.equals("token")) {
                        if (null != cookie.get(key) && !cookie.get(key).equals("")) {
                            path = ng.getPersonInfo(cookie);
//                            path=path.substring(path.indexOf("img"),path.length());
                            reason = "成功";

                        } else {
                            reason = "登录失败，用户名/密码错误！";
                            error_code = 1;
                        }
                    }
                }
            }else{
                reason = "登录失败，用户名/密码错误！";
                error_code = 1;
            }
        }catch (Exception e) {
            reason="登录失败，用户名/密码错误！";
            error_code=2;
            e.printStackTrace();
        }finally {
            JSONObject json=new JSONObject();
            json.put("path",path);
            json.put("reason",reason);
            json.put("error_code",error_code);
            return json;
        }
    }

    //检验图片文件是否存在，存在则删除
    public static void delFile(String path) {
        File file=new File(path);
        if (file.exists())
            file.delete();
    }

    //获取密码加密的JS文件，通过java的javascript引擎调用js中的加密方法
    public String getRSAutils(String password) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
        NcGjjUtils nc=new NcGjjUtils();

        //修改为当前项目目录下的js文件路径
        String jsFileName = nc.getPath()+"js/security.js";   // 读取js文件
//        String jsFileName="d:\\security.js";
        InputStreamReader fr=new InputStreamReader(new FileInputStream(jsFileName),"UTF8");
        try {
            engine.eval(fr);
            if (engine instanceof Invocable) {
                Invocable invocable = (Invocable) engine;
                Methods executeMethod = invocable.getInterface(Methods.class);
                System.out.println(executeMethod.individualSubmitForm(password));
                password=executeMethod.individualSubmitForm(password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    //发起登录界面GET请求，获取cookie值
    private static Map getLoginHtml() throws IOException {
        //模拟浏览器创建连接，发起请求
        Connection conn1 = Jsoup.connect(LOGIN_URL);
        conn1.header("Host", "www.ncgjj.com.cn:8081");
        conn1.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0");
        Connection.Response responses = conn1.ignoreContentType(true).method(Connection.Method.GET).execute();
        Map<String,String> cookie = responses.cookies();
        return cookie;
    }
    //获取登录界面的图片验证码进行识别，获取识别的字符串
    private String getCaptcha(Map<String, String> cookie) throws Exception {
        Connection conn = Jsoup.connect(CHECKCODE_URL);
        conn.header("Host", "www.ncgjj.com.cn:8081");
        conn.header("Referer", "http://www.ncgjj.com.cn:8081/wt-web/captcha");
        conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0");
        Connection.Response response = conn.ignoreContentType(true).method(Connection.Method.GET).cookies(cookie).execute();
        NcGjjUtils nc=new NcGjjUtils();
        String path=nc.getPath()+"img/code"+ UUIDUtil.gettimestampFormat()+".jpg";
//        String path="d:\\code.jpg";
        /*byte[] b=response.bodyAsBytes();
        for(int i=0;i<b.length;++i){
            if(b[i]<0){//调整异常数据
                b[i]+=256;
            }
        }
        OutputStream out=new FileOutputStream(path);
        out.write(b);
        out.flush();
        out.close();*/
        String s= CheckCodeUtil.getCodeResult(path);
//        delFile(path);
        return s;
    }
    //进行模拟浏览器的登录操作
    private static Map<String,String> getLoginSuccess(Map<String,String> cookie, Map<String,String> param) throws Exception {
        String jsessionid=null;
        Set<String> keys = cookie.keySet();
        for(String key:keys){
            if(key.equals("JSESSIONID")){
                jsessionid=cookie.get(key);
            }
            System.out.println(key+":"+cookie.get(key));
        }
        Connection conns = Jsoup.connect(LOGIN_URL);
        conns.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conns.header("Content-Type","application/x-www-form-urlencoded");
        conns.header("Host","www.ncgjj.com.cn:8081");
        conns.header("Referer", "http://www.ncgjj.com.cn:8081/wt-web/login");
        conns.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0");
        //利用cookies保存的登录信息进行登录
        Connection.Response res = conns.ignoreContentType(true).method(Connection.Method.POST).cookies(cookie).data(param).execute();
        //获取cookies
        Map<String,String> cookies= res.cookies();
        Set<String> key1 = cookies.keySet();
        for(String key:key1){
            System.out.println(key+":"+cookies.get(key));
        }
        return cookies;
    }
    //获取登录成功后，个人公积金信息，并将图片存储到指定路径
    private String getPersonInfo(Map<String, String> cookie) {
        String path=null;
        try {
            Random random = new Random();
            Connection conn = Jsoup.connect(PERSON_JBXX + "?random=" + random.nextDouble());
            conn.header("Accept", "*/*");
            conn.header("Content-Type", "application/x-www-form-urlencoded");
            conn.header("Host", "www.ncgjj.com.cn:8081");
            conn.header("Referer", "http://www.ncgjj.com.cn:8081/wt-web/home?logintype=1");
            conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0");
            Connection.Response res = conn.ignoreContentType(true).method(Connection.Method.GET).cookies(cookie).execute();
//            String path = "d:\\info.png";
            NcGjjUtils nc=new NcGjjUtils();
            path=nc.getPath()+"img/persongjj"+ UUIDUtil.gettimestampFormat()+".png";
            byte[] b = res.bodyAsBytes();
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream outs = new FileOutputStream(path);
            outs.write(b);
            outs.flush();
            outs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
    //公积金个人图片识别
//    public static void savaPersonGjjInfo(String path) throws Exception {
////        String paths = "d:\\tPicture/jbxx.png";
//        BaiduImageUtil bu = new BaiduImageUtil();
//        String datapath= SysUtil.getInstanse().getPerperty("datapath");
//        net.sf.json.JSONObject json1 = bu.basicGeneral(path);
//        List<Word> result = Tess4jUtil.readChar(path, datapath, "chi_sim");
//        net.sf.json.JSONObject json = new net.sf.json.JSONObject();
//        for (Word word : result) {
//            String s = word.getText();
//            String[] ss = s.split("=");
//            String key1 = ss[0].trim();
//            String key2 = ss[1].trim().split(" ")[1];
//            String value1 = ss[1].trim().split(" ")[0];
//            String value2 = ss[2].trim();
//            json.put(key1, value1);
//            json.put(key2, value2);
//        }
//        net.sf.json.JSONObject json2 = new net.sf.json.JSONObject();
//        json2.putAll(json);
//        json2.putAll(json1);
//        AccumlationData ad = NcGjjUtils.ChangeJsonToObject(json2);
//        ad.setPath(path);
//        //将获取的数据存入到数据库中
//        AccumulationFundDao afd=new AccumulationFundDao();
//        java.sql.Connection con = DBManager.getInstanse().getConnection(DBManager.DB_ALIAS_DEFAULT);
//        afd.insertDataAccumulatin(con,ad);
//    }

    //处理图片识别后的数据
//    private static AccumlationData ChangeJsonToObject(net.sf.json.JSONObject json){
//        AccumlationData ad=new AccumlationData();
//        Iterator<String> it=json.keys();
//        while (it.hasNext()){
//            String key=it.next();
//            switch (key){
//                case "注册用户名":
//                    ad.setRegistname(json.getString(key));
//                    continue;
//                case "注册用户手机号":
//                    ad.setPhoneNo(json.getString(key));
//                    continue;
//                case "单位账号":
//                    ad.setUnitAccount(json.getString(key));
//                    continue;
//                case "单位名称":
//                    ad.setUnitName(json.getString(key));
//                    continue;
//                case "职工账号":
//                    ad.setWorkaccount(json.getString(key));
//                    continue;
//                case "职工姓名":
//                    ad.setWorkname(json.getString(key));
//                    continue;
//                case "账户状态":
//                    ad.setAccountStatus(json.getString(key));
//                    continue;
//                case "证件号码":
//                    ad.setIdCard(json.getString(key));
//                    continue;
//                case "个人缴存比例":
//                    ad.setPersonContribution(json.getString(key));
//                    continue;
//                case "单位缴存比例":
//                    ad.setUnitContribution(json.getString(key));
//                    continue;
//                case "工资基数":
//                    ad.setWageBase(json.getString(key));
//                    continue;
//                case "是否冻绾":
//                    ad.setIsFreeze(json.getString(key));
//                    continue;
//                case "是否冻结":
//                    ad.setIsFreeze(json.getString(key));
//                    continue;
//                case "开户日期":
//                    ad.setOpenMonth(json.getString(key).replaceAll("一","-"));
//                    continue;
//                case "月缴存额":
//                    ad.setMonthAmount(json.getString(key));
//                    continue;
//                case "上年结转金额":
//                    ad.setCarryForward(json.getString(key));
//                    continue;
//                case "账户余额":
//                    ad.setPayBalance(Double.valueOf(json.getString(key).replaceAll(",","")));
//                    continue;
//            }
//        }
//        return ad;
//
//
//    }


    public static void main(String[] args) throws Exception {
        NcGjjUtils ncn=new NcGjjUtils();
//        boolean s=ncn.ishaveGjjInfo("330327193110130994");
//        System.out.println(ncn.getPath());
//        ncn.getRSAutils("yxx235711");
//        JSONObject json=NcGjjUtils.verify("360203198807193535","yxx235711");
//        Random random = new Random();
//        double s=random.nextDouble();
//        System.out.println(s);
//        AccumlationData ad=NcGjjUtils.savaPersonGjjInfo("d:\\tPicture/jbxx.png");
//        System.out.println(s);

    }
    public String getPath(){
        URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
        String path = url.toString();
        path=path.replace("file:/", "");
        int index = path.indexOf("WEB-INF");

        if(index == -1){
            index = path.indexOf("classes");
        }
        if(index == -1){
            index = path.indexOf("bin");
        }
        path = path.substring(0, index);
        return path;
    }

//    public boolean ishaveGjjInfo(String idcard) throws SQLException {
//
//        AccumulationFundDao afd=new AccumulationFundDao();
//        java.sql.Connection con = DBManager.getInstanse().getConnection(DBManager.DB_ALIAS_DEFAULT);
//        boolean isSave=afd.exsitByIdCard(con,idcard);
//        return isSave;
//    }




}
