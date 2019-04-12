package com.dhy.yycompany.lock.dao;

import com.dhy.yycompany.lock.bean.DailyRecordInfo;
import com.dhy.yycompany.lock.bean.DailyRecordInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyRecordInfoMapper {
    long countByExample(DailyRecordInfoExample example);

    int deleteByExample(DailyRecordInfoExample example);

    int deleteByPrimaryKey(Integer dId);

    int insert(DailyRecordInfo record);

    int insertSelective(DailyRecordInfo record);

    List<DailyRecordInfo> selectByExample(DailyRecordInfoExample example);

    DailyRecordInfo selectByPrimaryKey(Integer dId);

    int updateByExampleSelective(@Param("record") DailyRecordInfo record, @Param("example") DailyRecordInfoExample example);

    int updateByExample(@Param("record") DailyRecordInfo record, @Param("example") DailyRecordInfoExample example);

    int updateByPrimaryKeySelective(DailyRecordInfo record);

    int updateByPrimaryKey(DailyRecordInfo record);
}