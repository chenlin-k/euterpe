package com.chenlink.euterpe.util;

import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Describe: String工具类
 * Author:   chenlink
 * Data:     2018/5/31.
 */
public class StringUtil {

    public static String clearEmpty(String str) {
        return str.replaceAll(" ", " ").replaceAll(" ", " ");
    }

    @Test
    public void test() {
        String str = "AAAAAA";
        System.out.println(clearNumSpace(str));
    }

    /**
     * 将字符串中出现的小写大写连续的情况切割
     *
     * @param str
     * @return
     */
    public static String[] splitStrByaA(String str) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char chr = str.charAt(i);
            if (Character.isLowerCase(chr) && i + 1 < str.length() && Character.isUpperCase(str.charAt(i + 1))) {
                list.add(i);
            }
        }
        if (list.isEmpty()) {
            String[] result = {str};
            return result;
        }
        String[] result = new String[list.size() + 1];
        for (int i = 0; i < result.length; i++) {
            if (i == 0) {
                result[0] = str.substring(0, list.get(0) + 1);
            } else if (i == result.length - 1) {
                result[i] = str.substring(list.get(i - 1) + 1, str.length());
            } else {
                result[i] = str.substring(list.get(i - 1) + 1, list.get(i) + 1);
            }
        }
        return result;
    }

    /**
     * 去掉字符串最前面的空串或者数字字符
     */
    public static String clearNumSpace(String str) {
        String tmp = str.trim();
        int num = 0;
        for (int i = 0; i < tmp.length(); i++) {
            if (Character.isDigit(tmp.charAt(i))) {
                num++;
                continue;
            }
            break;
        }
        return tmp.substring(num,tmp.length());
    }
    /**
     * 生成错误的json
     */
    public static JSONObject genetateErrorJson(String message){
        JSONObject json = new JSONObject();
        json.put("success", 0);//0：失败 ，1：成功
        json.put("msg", message);
        json.put("data"," ");
        return json;
    }

    public static String generateGUID() {
        return new BigInteger(165, new Random()).toString(36).toUpperCase();
    }

    /**
     * 对金额为RMB 5,640.67的字符串进行处理
     */
    public static Double stringToDouble(String rmbStr){
        Double d= Double.valueOf(rmbStr.replaceAll("[a-zA-Z,]",""));
        System.out.println(d);
        return d;
    }
    public static  void main(String [] args){
        StringUtil.stringToDouble("RMB 5,640.67");
    }


    /**
     * 对于错误的邮件内容进行存储
     * @param content
     * @return
     */
    public static String saveAsFileWriter(String content) {
        String savefilepath = "D://mail/error/"+ StringUtil.generateGUID()+".txt";
        FileWriter fwriter = null;
        try {
            fwriter = new FileWriter(savefilepath);
            fwriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return savefilepath;
    }
    /**
     * 对于错误的邮件内容进行存储
     * @param content
     * @return
     */
    public static String saveAllAsFileWriter(String[] content) {
        String savefilepath = "D://mail/error/"+ StringUtil.generateGUID()+".txt";
        FileWriter fwriter = null;
        try {
            fwriter = new FileWriter(savefilepath);
            for(String s:content){
                fwriter.write(s);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return savefilepath;
    }


    public static void saveAllOperator(String idcard,String phone,String mobileType,String content) {

        String savefilepath = "D://mail/mobile/"+ idcard+"&"+phone+"&"+mobileType+".txt";
        FileWriter fwriter = null;
        try {
            fwriter = new FileWriter(savefilepath);
            fwriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return;
    }


    public static JSONObject resultParamIsEmpty(String paramString){
        JSONObject json=new JSONObject();
        json.put("msg",paramString+"不能为空！");
        json.put("success","0");
        return json;
    }

}
