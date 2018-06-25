package com.chenlink.euterpe.jdbc.impl;


import com.chenlink.euterpe.jdbc.BlogDao;
import com.chenlink.euterpe.entity.BlogContent;
import com.chenlink.euterpe.jdbc.JDBCHelper;

/**
 * Describe: blog网站数据插入
 * Author:   chenlink
 * Data:     2018/5/31.
 */
public class BlogDaoImpl implements BlogDao {

    public int save(BlogContent blog) {

        String sql = "INSERT INTO blog (title,content,update_time) VALUES(?,?,now())";
        Object[] params = new Object[]{blog.getContent(), blog.getTitle()};
        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
        int is = jdbcHelper.executeUpdate(sql, params);
        return is;
    }

}
