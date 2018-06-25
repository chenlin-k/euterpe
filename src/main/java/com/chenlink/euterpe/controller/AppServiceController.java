package com.chenlink.euterpe.controller;

import cn.wanghaomiao.seimi.config.SeimiConfig;
import cn.wanghaomiao.seimi.core.Seimi;
import com.chenlink.euterpe.entity.BillCycleInfoEntity;
import com.chenlink.euterpe.entity.MobileOperatorEntity;
import com.chenlink.euterpe.mybatis.mapper.BillCycleMapper;
import com.chenlink.euterpe.mybatis.service.impl.*;
import com.chenlink.euterpe.util.MobileUtils;
import com.chenlink.euterpe.util.StringUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 邮箱controller
 * @author chenlink
 * @version 1.0, 2018-05-08
 */
@RestController
public class AppServiceController {

    @Autowired
    private BillCycleMapper bm;

    @Autowired
    private QQemailImpl ets;

    @Autowired
    private AliyunemailImpl alm;

    @Autowired
    private CmmailImpl cll;

    @Autowired
    private OperatorImpl uo;


    private static final Logger logger = Logger.getLogger(AppServiceController.class);



    /**
     * 测试专用接口
     * @return
     */
    @RequestMapping("/test")
    public String test(int id) {
        return "";

    }



    /**
     * 根据idcard查询邮箱账单信息
     * @param idcard
     * @return
     */
    @RequestMapping("/selectMail")
    public String queryMail(String idcard) {
        String msg = "成功";
        String success = "1";
        List<BillCycleInfoEntity> list=new ArrayList<>();
        if(StringUtils.isEmpty(idcard)){
            return StringUtil.genetateErrorJson("Idcard不能为空！").toString();
        }else{
            list=bm.selectByidcard(idcard);
        }
        JSONObject json=new JSONObject();
        json.put("msg",msg);
        json.put("success",success);
        json.put("data",list);
        return json.toString();
    }

    /**
     * QQ邮箱的银行邮件过滤
     * @param file
     * @param idcard
     * @return
     */
    @RequestMapping("/qqMailHtml")
    public String qqMailHtml(@RequestParam("file") MultipartFile file,String idcard) {
        String msg = "成功";
        String success = "1";
        String data=" ";
        JSONObject jb = new JSONObject();
        try {
            String[] sts = this.readTxtFile(file);
            data = ets.mailAnaly(sts,idcard).toString();
            Date day = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            logger.info("QQ邮箱解析返回信息："+data);
            logger.info("时间：" + df.format(day) + "   ");
        } catch (Exception e) {
            msg="解析文件出现异常！";
            success="2";
            logger.error("解析文件出现异常："+e);
        }
        jb.put("msg",msg);
        jb.put("success",success);
        return jb.toString();
    }

    /**
     * aliyun邮箱的银行邮件过滤
     * @param file
     * @param idcard
     * @return
     */
    @RequestMapping("/aliyunMailHtml")
    public String aliyunMailHtml(@RequestParam("file") MultipartFile file,String idcard) {
        String msg = "成功";
        String success = "1";
        String data=" ";
        JSONObject jb = new JSONObject();
        try {
            String[] sts = this.readTxtFile(file);
            data = alm.mailAnaly(sts,idcard).toString();
            Date day = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            logger.info("aliyun邮箱解析返回信息："+data);
            logger.info("时间：" + df.format(day) + "   ");
        } catch (Exception e) {
            msg="解析文件出现异常！";
            success="2";
            logger.error("解析文件出现异常："+e);
        }
        jb.put("msg",msg);
        jb.put("success",success);
        return jb.toString();
    }

    /**
     * 139邮箱的银行邮件过滤
     * @param file
     * @param param
     * @return
     */
    @RequestMapping("/139MailHtml")
    public String cmMailHtml(@RequestParam("file") MultipartFile file,String param) throws IOException {
        BillCycleInfoEntity bcie = new BillCycleInfoEntity();
        String msg = "成功";
        String success = "1";
        String data=" ";
        String[] sts = this.readTxtFile(file);
        JSONObject jb = new JSONObject();
        try {
            data = cll.mailAnalys(sts,param).toString();
            Date day = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            logger.info("139邮箱解析返回信息："+data);
            logger.info("时间：" + df.format(day) + "   ");
            logger.info("解析返回信息："+data);
        } catch (Exception e) {
            msg="解析文件出现异常！";
            success="2";
            logger.error("解析文件出现异常："+e);
        }
        jb.put("msg",msg);
        jb.put("success",success);
        return jb.toString();
    }


    /**
     * 联通、移动运营商（传输的是json）
     * @param idcard
     * @param phone
     * @param mobileType
     * @param param
     * @return
     */
    @RequestMapping("/mobileOperator")
    public String mobileOperator(String idcard,String phone,String mobileType,String param) {
        logger.info("联通、移动运营商接口被调用！");
        if(org.springframework.util.StringUtils.isEmpty(idcard)){
             return StringUtil.resultParamIsEmpty("身份证号").toString();
        }
        if(org.springframework.util.StringUtils.isEmpty(phone)){
            return StringUtil.resultParamIsEmpty("手机号").toString();
        }
        if(org.springframework.util.StringUtils.isEmpty(mobileType)){
            return StringUtil.resultParamIsEmpty("运营商类型").toString();
        }
        if(org.springframework.util.StringUtils.isEmpty(param)){
            return StringUtil.resultParamIsEmpty("账单信息").toString();
        }
        String msg = "成功";
        String success = "1";
        List<MobileOperatorEntity> list = new ArrayList<>();
        MobileUtils mu = new MobileUtils();
        if (mobileType.equals("10010")) {//联通数据处理
            list = mu.getCuccPeopleData(idcard, phone, mobileType, param);
        }else if (mobileType.equals("10086")) {//移动数据处理
            list = mu.getCmccPeopleData(idcard, phone, mobileType, param);
        }else {
            success = "0";
            msg = "不支持该运营商！";
        }
        if (list.size() > 0) {
            try {
                    uo.insert(list);
            } catch (Exception e) {
                success = "0";
                msg = "出现异常信息："+e.getCause();
                e.printStackTrace();
            }
        } else {
            success = "0";
            msg = "未获取到运营商数据";
        }
        JSONObject json = new JSONObject();
        json.put("msg", msg);
        json.put("success", success);
        return json.toString();
    }

    /**
     * 电信运营商(传输的是html)
     * @param file
     * @param idcard
     * @param phone
     * @return
     */
    @RequestMapping("/ctccOperator")
    public String ctccOperator(@RequestParam("file") MultipartFile file,String idcard,String phone) throws IOException {
        logger.info("电信运营商接口被调用！");
        String[] sts = this.readTxtFile(file);
        if(org.springframework.util.StringUtils.isEmpty(idcard)){
            return StringUtil.resultParamIsEmpty("身份证号").toString();
        }
        if(org.springframework.util.StringUtils.isEmpty(phone)){
            return StringUtil.resultParamIsEmpty("手机号").toString();
        }
        if(sts.length==0){
            return StringUtil.resultParamIsEmpty("账单信息").toString();
        }
        String msg = "成功";
        String success = "1";
        List<MobileOperatorEntity> list = new ArrayList<>();
        MobileUtils mu=new MobileUtils();
        int i=0;
        for(String param:sts){
            list.add(mu.getCtccPeopleData(idcard,phone,"10000",param));
        }
        if (list.size() > 0) {
            try {
                uo.insert(list);
            } catch (Exception e) {
                success = "0";
                msg = "保存电信运营商数据出现异常："+e.getCause();
                e.printStackTrace();
            }
        } else {
            success = "0";
            msg = "未获取到电信运营商数据";
        }
        JSONObject json = new JSONObject();
        json.put("msg", msg);
        json.put("success", success);
        return json.toString();
    }

    @RequestMapping("/mybatisSeimi")
    public String mybatisSeimi(){
        SeimiConfig config = new SeimiConfig();
        config.setSeimiAgentHost("127.0.0.1");
        config.redisSingleServer().setAddress("redis://127.0.0.1:6379");
        Seimi s = new Seimi(config);
        s.goRun("mybatis");
//        BlogContent blog=new BlogContent();
//        blog.setId(12);
//        blog.setTitle("chenlink");
//        blog.setContent("chenchenchen");
//        uo.save(blog);
        return "success";
    }

    /**
     * 读取文件并切割处理
     * @param multfile
     * @return
     * @throws IOException
     */
    public String [] readTxtFile(MultipartFile multfile) throws IOException {
        String fileName = multfile.getOriginalFilename();
        final File file = File.createTempFile(StringUtil.generateGUID(), "txt");
        multfile.transferTo(file);
        StringBuffer sb=new StringBuffer();
        try {
            if (file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String lineTxt = null;
                while ((lineTxt = br.readLine()) != null) {
                    sb.append(lineTxt);
                }
                br.close();
            } else {
                logger.error("文件不存在!");
            }
        } catch (Exception e) {
            logger.error("文件读取错误!");
        }
            String [] sts= String.valueOf(sb).split("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return sts;
    }

}
