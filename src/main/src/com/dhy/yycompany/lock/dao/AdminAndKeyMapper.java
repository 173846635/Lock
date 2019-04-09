package com.dhy.yycompany.lock.dao;

import com.dhy.yycompany.lock.bean.AdminAndKey;
import com.dhy.yycompany.lock.bean.AdminAndKeyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminAndKeyMapper {
    long countByExample(AdminAndKeyExample example);

    int deleteByExample(AdminAndKeyExample example);

    int insert(AdminAndKey record);

    int insertSelective(AdminAndKey record);

    List<AdminAndKey> selectByExample(AdminAndKeyExample example);

    int updateByExampleSelective(@Param("record") AdminAndKey record, @Param("example") AdminAndKeyExample example);

    int updateByExample(@Param("record") AdminAndKey record, @Param("example") AdminAndKeyExample example);
}