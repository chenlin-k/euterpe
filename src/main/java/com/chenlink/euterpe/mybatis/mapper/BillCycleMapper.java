package com.chenlink.euterpe.mybatis.mapper;

import com.chenlink.euterpe.entity.BillCycleInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BillCycleMapper {
    List<BillCycleInfoEntity> selectByidcard(String idCard);

    void insert(BillCycleInfoEntity b) throws Exception;

    int countByBankOrDate(BillCycleInfoEntity b)throws Exception;

    void updateBi(BillCycleInfoEntity b)throws Exception;

}
