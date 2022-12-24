package com.lck.crowd.service;

import com.github.pagehelper.PageInfo;
import com.lck.crowd.entity.Admin;

import java.util.List;

public interface AdminService {
    void saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminByLoginAcct(String login, String userPswd);

    /**
     *
     * @param keyword 分页条件
     * @param pageNum 页数
     * @param pageSize 每页几条
     * @return
     */
    PageInfo<Admin> getPageInfo(String keyword,int pageNum,int pageSize);

    Integer removeById(Integer id);

    Admin getAdminById(Integer id);

    void updateAdmin(Admin admin);

    void saveAdminRoleReolation(Integer adminId, List<Integer> roleIdList);

    Admin getAdminByLoginAcct(String username);
}
