package com.chenlink.euterpe.jdbc;


import com.chenlink.euterpe.entity.BlogContent;


/**
 * Describe: blog网站保存数据
 * Author:   chenlink
 * Data:     2018/5/31.
 */
public interface BlogDao {

    public int save(BlogContent blog);
}
