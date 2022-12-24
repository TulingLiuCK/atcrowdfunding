package com.lck.crowd.service.Impl;

import com.lck.crowd.entity.Auth;
import com.lck.crowd.entity.AuthExample;
import com.lck.crowd.mapper.AuthMapper;
import com.lck.crowd.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/***
 #Create by LCK on 2022/11/25
 # 用法: 
 */
@Service
public class AuthServiceImpl implements AuthService {
    private Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Auth> getAll() {
        List<Auth> auths = authMapper.selectByExample(new AuthExample());
        logger.info("auths:{}",auths);
        return auths;
    }

    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
        authMapper.selectAssignedAuthIdByRoleId(roleId);
        return null;
    }

    @Override
    public void saveRoleSuthRelationShip(Map<String, List<Integer>> map) {
        //先获取roleId
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);
        //删除旧的关联数据
        authMapper.deleteOldRealtionShip(roleId);

        //去获取authIdlist
        List<Integer> authIdList = map.get("authIdArray");
        //判断是否有效
        if (authIdList != null && authIdList.size()>0){
            int reultNew = authMapper.insertNewRelationShip(roleId, authIdList);
            logger.info("新增{}条数据关系",reultNew);
        }
    }

    @Override
    public List<String> getAssignedAuthNameByAdminId(Integer adminId) {

        return authMapper.selectAssignedAuthNameByAdminId(adminId);
    }
}
