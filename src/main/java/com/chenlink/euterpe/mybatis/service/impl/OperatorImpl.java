package com.chenlink.euterpe.mybatis.service.impl;

import com.chenlink.euterpe.entity.MobileOperatorEntity;
import com.chenlink.euterpe.mybatis.mapper.MobileOperatorMapper;
import com.chenlink.euterpe.mybatis.service.MobileOperatorTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 运营商资料保存、查询
 */
@Component
public class OperatorImpl implements MobileOperatorTemplate {

    private Logger logger=Logger.getLogger(OperatorImpl.class);

    @Autowired
    private MobileOperatorMapper bm;

    @Override
    public MobileOperatorEntity findMobileOperator(int id) {
        return bm.findMobileOperator(id);
    }

    @Override
    public void insert(List<MobileOperatorEntity> list) throws Exception {
        bm.insertMobileOperator(list);
        return ;
    }


}
