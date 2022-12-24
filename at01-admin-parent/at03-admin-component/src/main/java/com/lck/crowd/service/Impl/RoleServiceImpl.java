package com.lck.crowd.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lck.crowd.entity.Role;
import com.lck.crowd.entity.RoleExample;
import com.lck.crowd.mapper.RoleMapper;
import com.lck.crowd.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 #Create by LCK on 2022/11/24
 # 用法: 
 */
@Service
public class RoleServiceImpl implements RoleService {

    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roleList = roleMapper.selectRoleByKeyword(keyword);

        return new PageInfo<>(roleList);
    }

    @Override
    public void saveRole(Role role) {
        int insert = roleMapper.insert(role);
        if (insert > 0) {
            logger.info("新增成功");
        }
    }

    @Override
    public void updateRole(Role role) {
        int result = roleMapper.updateByPrimaryKey(role);
        if (result > 0) {
            logger.info("更新成功");
        }
    }

    @Override
    public void removeRole(List<Integer> roleIdList) {
        logger.info("roleIdList:{}", roleIdList);
        RoleExample ex = new RoleExample();
        RoleExample.Criteria criteria = ex.createCriteria();
        criteria.andIdIn(roleIdList);
        int result = roleMapper.deleteByExample(ex);
        if (result > 0) {
            logger.info("批量删除成功");
        }
    }

    @Override
    public List<Role> getAssignedRole(Integer adminId) {

        return roleMapper.selectAssignedRole(adminId);
    }

    @Override
    public List<Role> getUnAssignedRole(Integer adminId) {
        return roleMapper.selectUnAssignedRole(adminId);
    }

}
