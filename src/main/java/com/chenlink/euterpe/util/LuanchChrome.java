package com.chenlink.euterpe.util;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LuanchChrome {
	public static void main(String[] args) throws Exception {
		// 电话号码
		CharSequence phone = "15007007374";
		System.setProperty("webdriver.chrome.driver", "D:\\geckodriver\\chromedriver.exe");
		// 初始化一个浏览器实例，实例名称叫driver
		WebDriver driver = new ChromeDriver();
		// 设置隐性等待时间
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// 最大化窗口
		// driver.manage().window().maximize();

		// 打开一个站点
		driver.get("https://login.10086.cn/login.html?channelID=12003");
		// 获取当前页面title的值
		System.out.println("当前打开页面的标题是： " + driver.getTitle());
		Thread.sleep(2000);
		// 短信随机码登录
		driver.findElement(By.id("sms_login_1")).click();
		// 输入用户名
		WebElement loginname = driver.findElement(By.id("sms_name"));
		loginname.sendKeys(phone);
		// 发送手机验证码
		driver.findElement(By.id("getSMSPwd1")).click();
		// 获取cookie
		Set<Cookie> cookie = driver.manage().getCookies();
		System.err.println(net.sf.json.JSONObject.fromObject(cookie));
		// 输入手机验证码
		WebElement password = driver.findElement(By.id("sms_pwd_l"));
		Scanner input = new Scanner(System.in);
		System.err.println("请输入手机验证码：");
		String smsPwd = input.nextLine();
		password.sendKeys(smsPwd);
		input.close();
		// 提交
		driver.findElement(By.id("submit_bt")).click();
		Thread.sleep(4000);
		// 近6个月消费记录
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Calendar calenter = Calendar.getInstance();
		String now = format.format(calenter.getTime());
		calenter.add(Calendar.MONTH, -5);
		String before = format.format(calenter.getTime());
		String history = "http://shop.10086.cn/i/v1/fee/billinfo/" + phone + "?bgnMonth=" + now + "&endMonth=" + before
				+ "&_=" + new Date().getTime();
		driver.get(history);
		// 页面源码
		String pageSource = driver.getPageSource();
		System.err.println(pageSource);
		pageSource = pageSource.replaceAll("<!--[\\S\\s]+?-->", "")// 去除注释内容
				.replaceAll("<script[\\s\\S]+?</script>", "")// 去除js内容
				.replaceAll("<.+?>", "");// 去除html标签
		pageSource = pageSource.replaceAll("&nbsp;", "");
		pageSource = pageSource.replaceAll("[\\s\n\r]+", "\n");// 去除多余空白
		System.err.println(pageSource);
		// 关闭并退出浏览器
		driver.quit();
	}
}
