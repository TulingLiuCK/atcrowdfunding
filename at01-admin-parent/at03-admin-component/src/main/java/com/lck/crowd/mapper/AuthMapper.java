package com.lck.crowd.mapper;

import com.lck.crowd.entity.Auth;
import com.lck.crowd.entity.AuthExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface AuthMapper {
    long countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    List<Integer> selectAssignedAuthIdByRoleId(Integer roleId);

    int deleteOldRealtionShip(Integer roleId);

    int insertNewRelationShip(@Param("roleId") Integer roleId,@Param("authIdList") List<Integer> authIdList);

    List<String> selectAssignedAuthNameByAdminId(@Param("adminId") Integer adminId);
}