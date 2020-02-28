package com.chenlink.euterpe.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author mengxianliang
 * @email  835323719@qq.com
 * @time   2016-12-6上午9:43:26
 */
public class UUIDUtil {
	
	public static String getUnique(){
		return UUID.randomUUID().toString();
	}
	public static String getUniqueFormat(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	public static void main(String[] args) {
		System.out.println(UUIDUtil.getUnique());
		System.out.println(UUIDUtil.getUniqueFormat());
		System.out.println(UUIDUtil.gettimestampFormat());
	}
	public static String gettimestampFormat(){
		Random ran   =   new   Random();
		String id=String.valueOf(System.currentTimeMillis())+String.valueOf(ran.nextInt(10000));
		return id;
	}

}
