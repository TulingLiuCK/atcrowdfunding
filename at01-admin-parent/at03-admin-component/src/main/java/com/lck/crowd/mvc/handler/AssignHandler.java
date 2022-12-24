package com.lck.crowd.mvc.handler;

import com.lck.crowd.entity.Auth;
import com.lck.crowd.entity.Role;
import com.lck.crowd.service.AdminService;
import com.lck.crowd.service.AuthService;
import com.lck.crowd.service.RoleService;
import com.lck.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/***
 #Create by LCK on 2022/11/25
 # 用法: 权限分配控制器
 */
@RestController
public class AssignHandler {
    private Logger logger = LoggerFactory.getLogger(AssignHandler.class);
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;

    @PostMapping("/assign/to/assign/role/page.html")
    public String toAssignRolePage(@RequestParam("adminId") Integer adminId, ModelMap modelMap) {
        //查询已分配的角色
        List<Role> assignedroleList = roleService.getAssignedRole(adminId);
        logger.info("已分配的角色：{}",assignedroleList);
        //查询未分配的角色
        List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);
        logger.info("未分配的角色：{}",unAssignedRoleList);
        //存入模型
        modelMap.addAttribute("assignedRoleList",assignedroleList);
        modelMap.addAttribute("unAssignedRoleList",unAssignedRoleList);
        return "assign-role";
    }
    //执行分配

    /**
     * Admin分配Role
     * @param adminId
     * @param pagerNum
     * @param keyword
     * @param roleIdList 允许用户在页面上取消所有已分配角色在提交表单
     * @return
     */
    @PostMapping("/assign/do/role/assign.html")
    public String saveAdminRoleRelationship(@RequestParam("adminId")Integer adminId,
                                            @RequestParam(value = "pageNum",defaultValue = "1")Integer pagerNum,
                                            @RequestParam(value = "keyword",defaultValue = "")String keyword,
                                            @RequestParam(value = "roleIdList",required = false)List<Integer> roleIdList){
        logger.info("roleIdList:{}",roleIdList);
        adminService.saveAdminRoleReolation(adminId,roleIdList);
        return "reidrect:/admin/get/page.html?pageNum="+pagerNum+"&keyword="+keyword;
    }

    @GetMapping("/assign/get/all/auth.json")
    public ResultEntity<List<Auth>> getAllAuth(){
        List<Auth> authList = authService.getAll();
        return ResultEntity.successWithoutData(authList);
    }

    @GetMapping("/assign/get/assigned/auth/id/by/role/id.json")
    public ResultEntity<List<Integer>> getAssignedAuthIdByRoleId(@RequestParam("roleId")Integer roleId){
        List<Integer> authIdList = authService.getAssignedAuthIdByRoleId(roleId);
        return ResultEntity.successWithoutData(authIdList);
    }
    //执行分配
    @PostMapping("/assign/do/role/assign/auth.json")
    public ResultEntity<String> saveRoleAuthRelationShip(@RequestBody Map<String,List<Integer>> map){
        authService.saveRoleSuthRelationShip(map);
        return ResultEntity.successWithoutData();
    }

}
