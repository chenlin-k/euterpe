package com.chenlink.euterpe.mybatis.mapper;

import com.chenlink.euterpe.entity.BlogContent;
import com.chenlink.euterpe.entity.MobileOperatorEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MobileOperatorMapper {

    MobileOperatorEntity findMobileOperator(int id);

    void insertMobileOperator(List<MobileOperatorEntity> list) throws Exception;

    int save(@Param("blog") BlogContent blog);

}
