package com.lck.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.lck.crowd.entity.Role;
import com.lck.crowd.service.RoleService;
import com.lck.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 #Create by LCK on 2022/11/24
 # 用法: 
 */
@RestController
public class RoleHandler {

    private Logger logger = LoggerFactory.getLogger(RoleHandler.class);

    @Autowired
    private RoleService roleService;


    @PostMapping("/role/get/page/info.json")
    public ResultEntity<PageInfo<Role>> getPageInfo(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                    @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                                                    @RequestParam(value = "keyword",defaultValue = "")String keyword,
                                                    ModelMap modelMap){
        PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);
        logger.info("pageInfo:{}",pageInfo);
        return ResultEntity.successWithoutData(pageInfo);
    }

    @PostMapping("/role/save.json")
    public ResultEntity<String> saveRole(@RequestBody Role role){
        roleService.saveRole(role);
        return ResultEntity.successWithoutData();
    }
    @PostMapping("/role/update.json")
    public ResultEntity<String> updateRole(@RequestBody Role role){
        roleService.updateRole(role);
        return ResultEntity.successWithoutData();
    }

    @PostMapping("/role/remove/by/role/id/array.json")
    public ResultEntity<String> removeByRoleIdArray(@RequestBody List<Integer> roleIdList){
        roleService.removeRole(roleIdList);
        return ResultEntity.successWithoutData();
    }

}
