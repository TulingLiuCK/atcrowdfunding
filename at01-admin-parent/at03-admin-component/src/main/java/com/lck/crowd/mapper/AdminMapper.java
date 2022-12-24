package com.lck.crowd.mapper;

import com.lck.crowd.entity.Admin;
import com.lck.crowd.entity.AdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin selectByAcct(@Param("login") String login);

    List<Admin> selectAdminByKeyWord(String keyword);

    int deleteOldRelationShip(Integer adminId);

    int insertNewRelationShip(@Param("adminId") Integer adminId,@Param("roleIdList") List<Integer> roleIdList);
}
