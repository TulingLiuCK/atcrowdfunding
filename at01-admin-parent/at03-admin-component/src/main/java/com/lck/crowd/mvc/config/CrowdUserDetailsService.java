package com.lck.crowd.mvc.config;

import com.lck.crowd.entity.Admin;
import com.lck.crowd.entity.Role;
import com.lck.crowd.service.AdminService;
import com.lck.crowd.service.AuthService;
import com.lck.crowd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/***
 #Create by LCK on 2022/11/26
 # 用法: 
 */
@Component
public class CrowdUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名得到Admin对象
        Admin admin = adminService.getAdminByLoginAcct(username);

        // 通过AdminId得到角色List
        List<Role> roles = roleService.getAssignedRole(admin.getId());

        // 通过AdminId得到权限name地List
        List<String> authNameList = authService.getAssignedAuthNameByAdminId(admin.getId());

        // 创建List用来存放GrantedAuthority（权限信息）
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 向List存放角色信息，注意角色必须要手动加上 “ROLE_” 前缀
        for (Role role : roles){
            String roleName = "ROLE_" + role.getName();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
            authorities.add(simpleGrantedAuthority);
        }

        // 向List存放权限信息
        for (String authName : authNameList){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authName);
            authorities.add(simpleGrantedAuthority);
        }

        // 将Admin对象、权限信息封装入SecurityAdmin对象（User的子类）
        SecurityAdmin securityAdmin = new SecurityAdmin(admin,authorities);

        // 返回SecurityAdmin对象
        return securityAdmin;
    }
}
