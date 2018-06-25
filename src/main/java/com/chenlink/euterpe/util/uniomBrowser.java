package com.chenlink.euterpe.util;

import com.chenlink.euterpe.server.SocketServer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author chenlink
 * @describe  使用selenium调用浏览器操作
 *
 */

public class uniomBrowser extends SocketServer {

    public String uniom(String username) throws IOException {
//    public static void main(String [] args) {
        WebDriver driver;   //声明WebDriver
        System.setProperty("webdriver.chrome.driver", "D:\\geckodriver\\chromedriver.exe");
        //指定Chrome浏览器的路径
        String Url = "https://uac.10010.com/oauth2/new_auth?" +
                "display=wap&page_type=05&app_code=ECS-YH-WAP&redirect_uri=http://wap.10010.com/t/loginCallBack.htm" +
                "&state=http://wap.10010.com/t/myunicom.htm&channel_code=113000001";   //联通登录的地址
//        String Url="http://127.0.0.1:8020/hj/new_file.html";
        driver = new ChromeDriver();        //new一个ChromeDriver()
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);    //设置隐式等待10秒钟
        driver.get(Url);    //打开百度首页
        driver.manage().window().maximize();    //把浏览器窗口最大化

        driver.findElement(By.id("randomPwdTips")).click();
        //向浏览器中的keyword标签发送value
        WebElement we = driver.findElement(By.id("userName"));
        we.clear();
        we.sendKeys(username);
        driver.findElement(By.id("resendTips")).click();
        System.out.println("获取验证码");
        slp(15000);
        String vcode=super.vcodemap.get(username);
        if("".equals(vcode)&&null==vcode){
            slp(20000);
            vcode=super.vcodemap.get(username);
        }
        if ("".equals(vcode)&&null==vcode){
            return "验证码输入超时！";
        }
        driver.findElement(By.id("userPwd")).sendKeys(vcode);
        //鼠标点击操作
        WebElement web = driver.findElement(By.id("login1"));
        web.click();
        String cssQuery = "html body div#container_l div.tiplogin div#loginend ul.adminarear li.passarear div.numbarea.logined dl dt p#showselect";
        String phone = driver.findElement(By.cssSelector(cssQuery)).getText();
        driver.findElement(By.id("myServer")).click();

//        System.out.println("chrome driver:"+ls);
//        //利用Screenshot对网页进行网页并且截图
//        File srcFile = ((TakesScreenshot)driver).
//                getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile
//                (srcFile,new File("d:\\screenshot.png"));
//
//        //鼠标悬浮操作
//        Actions builder1=new Actions(driver);
//        Action mouserOverlogin = builder1.moveToElement(we2).build();
//        mouserOverlogin.perform();

        //让线程等待3秒钟
        slp(10000);
        driver.quit();  //退出driver
        return phone;
    }

    //线程睡眠时间
    public static void slp(long l){
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}