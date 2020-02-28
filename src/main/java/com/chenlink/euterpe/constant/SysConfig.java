package com.chenlink.euterpe.constant;

public class SysConfig {  

    public static  int PORT = 8000;  
    public static String CHARSET="GBK";
    
    //识别验证码相关配置
    public static String username;
    public static String password;
    public static String softId;
    public static String codeType;
    public static String tempPath;
    public static int discernType = 3;
    public static String apiKey;
    public static String pwdReg;
    
    static {
    	   String tmp = SysUtil.getInstanse().getPerperty("msg.charset");
    	   if(tmp != null && !"".equals(tmp) ){
    		   CHARSET = tmp;
    	   }
    	   String tmp2 = SysUtil.getInstanse().getPerperty("msg.port");
    	   if(tmp2 != null && !"".equals(tmp2) ){
    		   PORT = Integer.parseInt(tmp2);
    	   }
    	   username = SysUtil.getInstanse().getPerperty("username");
    	   password = SysUtil.getInstanse().getPerperty("password");
    	   softId = SysUtil.getInstanse().getPerperty("softId");
    	   codeType = SysUtil.getInstanse().getPerperty("codeType");
    	   tempPath = SysUtil.getInstanse().getPerperty("tempPath");
			discernType = Integer.parseInt(SysUtil.getInstanse().getPerperty("discernType"));
			apiKey = SysUtil.getInstanse().getPerperty("apiKey");
			pwdReg = SysUtil.getInstanse().getPerperty("pwdReg");
    }
}  