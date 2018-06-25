package com.chenlink.euterpe.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import com.chenlink.euterpe.entity.BlogContent;
import com.chenlink.euterpe.jdbc.impl.BlogDaoImpl;
import com.chenlink.euterpe.jdbc.impl.DAOFactory;
import org.seimicrawler.xpath.JXDocument;

import java.util.List;

/**
 * 将解析出来的数据直接存储到数据库中,整合jdbchelper实现
 *
 * @author chenlink
 * @since 2018/05/27.
 */
@Crawler(name = "mybatis")
public class DatabaseMybatisDemo extends BaseSeimiCrawler {

    @Override
    public String[] startUrls() {
        return new String[]{"http://www.cnblogs.com/"};
    }

    @Override
    public void start(Response response) {
        JXDocument doc = response.document();
        try {
            List<Object> urls = doc.sel("//a[@class='titlelnk']/@href");
            logger.info("{}", urls.size());
            for (Object s : urls) {
                //对结果进行回调
                push(Request.build(s.toString(), DatabaseMybatisDemo::renderBean));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renderBean(Response response) {
        try {
            BlogContent blog = response.render(BlogContent.class);
            logger.info("bean resolve res={},url={}", blog, response.getUrl());
            //使用jdbchelper存储到DB
            BlogDaoImpl bdl = DAOFactory.getBlogDao();
            int changeNum = bdl.save(blog);
            logger.info("store success,changeNum={}", changeNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
