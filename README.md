# euterpe
日常项目汇总

spring boot项目

框架：集成了mybatis、websocket、redis、dubbo、jsoup解析器、xPath解析器、轻量级爬虫框架、selenium、phantomJs

功能：
     
     1.常见的QQ邮箱、aliyun邮箱、139邮箱的14家主流银行信用卡邮件内容解析，获取信用卡账单详细内容，并提供APP接入接口。

     2.联通、移动、电信的模拟登录，并且爬取登录后的用户近半年消费记录的页面，进行解析，存储到数据库中，并提供APP接入接口。
     
     3.可以对需要爬取内容的网站进行配置化的爬取。
     
     4.南昌公积金网站模拟登录并且进行抓取个人公积金数据，登录时验证码识别，个人公积金数据（图片）识别，采用Ocr图片识别+百度sdk双重识别提高准确率获取图片信息。
     
     5.芝麻信用的授权获取。
     
     6.行驶证图片信息识别获取个人资料，根据行驶证信息+个人车辆信息，对车辆进行价格区间估值。
     
要点：

     1.编写了常见的StringUtils、DateUtil的工具类
     2.package-》mybatis，可方便进行db交互
     3.JDBCHelper辅助工具类
     4.package-》crawlers中配置爬取网站的模板，该爬虫框架支持分布式，采用xPath进行html解析
     5.websocket+selenium模拟用户进行登录操作。websocket通信实现验证码传输，selenium实现模拟登录过程，并抓取数据。
     
