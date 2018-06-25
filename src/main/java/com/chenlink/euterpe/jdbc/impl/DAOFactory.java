package com.chenlink.euterpe.jdbc.impl;


/**
 * Describe: 工厂类
 * Author:   chenlink
 * Data:     2018/5/31.
 */
public class DAOFactory {

    public static BlogDaoImpl getBlogDao() {
        return new BlogDaoImpl();
    }

}
