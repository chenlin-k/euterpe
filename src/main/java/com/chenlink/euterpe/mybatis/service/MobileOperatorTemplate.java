package com.chenlink.euterpe.mybatis.service;



import com.chenlink.euterpe.entity.BlogContent;
import com.chenlink.euterpe.entity.MobileOperatorEntity;

import java.util.List;

public interface MobileOperatorTemplate {

    public MobileOperatorEntity findMobileOperator(int id);

    void insert(List<MobileOperatorEntity> b) throws Exception;

}
