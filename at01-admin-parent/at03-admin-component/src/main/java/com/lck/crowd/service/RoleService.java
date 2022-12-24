package com.lck.crowd.service;

import com.github.pagehelper.PageInfo;
import com.lck.crowd.entity.Role;

import java.util.List;

public interface RoleService {

    PageInfo<Role> getPageInfo(Integer pageNum,Integer pageSize,String keyword);

    void saveRole(Role role);

    void updateRole(Role role);

    void removeRole(List<Integer> roleIdList);

    /**
     * 查询已分配的角色
     */
    List<Role> getAssignedRole(Integer adminId);

    /**
     * 查询未分配的角色
     */
    List<Role> getUnAssignedRole(Integer adminId);
}
