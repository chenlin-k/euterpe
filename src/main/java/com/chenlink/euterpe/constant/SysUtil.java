
package com.chenlink.euterpe.constant;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;




/**
 * 接口相关配置初始化
 *
 */
public class SysUtil {
	private static SysUtil instanse = null;
	
	private static Properties pros = new Properties();
	
	private Logger logger = Logger.getLogger(SysUtil.class);
	
	/**
	 * 私有构造函数
	 *
	 */
	private SysUtil() {
		initLog4j();
		initProperties();
		
	}
	/**
	 * 实例方法
	 * @return
	 */
	public synchronized static SysUtil getInstanse() {
		if (instanse == null) {
			instanse = new SysUtil();
		}
		return instanse;
	}
	/**
	 * 初始化log4j
	 */
	public void initLog4j() {
		// 初始化log4j
		InputStream in = SysUtil.class.getResourceAsStream("/META-INF/res/log4j.properties");
		Properties pros = new Properties();
		try {
			pros.load(in);
			PropertyConfigurator.configure(pros);	
			logger.info("初始化log4j配置完成");
		} catch (IOException e) {
			logger.error("初始化log4j配置异常\n"+e);
		}
	}
	
	/**
	 * 初始化系统配置
	 */
	private void initProperties(){
		try {
			//load common properties
			InputStream in = SysUtil.class.getResourceAsStream("/META-INF/res/sys.properties");
			pros.load(in);
			//load spring context
//			InputStream in1 = SysUtil.class.getResourceAsStream("/config/framework-context.xml");
//			pros.load(in1);

			
		} catch (IOException e) {
			logger.error("初始化系统配置异常\n"+e);
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getPerperty(String name){
		if (name==null || "".equals(name)){
			return "";
		}
		String value = pros.getProperty(name);
		if (value==null || "".equals(value)){
			return "";
		}
		return value;
	}
	
	/**
	 * 连续num次为true返回true，否则返回false
	 * @param list 数组
	 * @param num 连续次数
	 * @return 
	 */
	public static boolean multiBoolean(List<Boolean> list,int num){
		Object[] t = list.toArray();
		for(int i=0; i<t.length; i++){
			boolean x = (Boolean) t[i];
			if(x){
				boolean result = true;
				int cou=1;
				for(int j=i+1,z=0; j<t.length&&z<num-1; j++,z++){
					boolean y =  (Boolean) t[j];
					result = result && y;
					if(!result){
						break;
					}
					cou++;
				}
				if(result&&cou==num){
					return true;
				}
			}
			
		}
		return false;
	}
	
}