package com.lck.crowd.mvc.config;

import com.lck.crowd.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/***
 #Create by LCK on 2022/11/26
 # 用法: 
 */
public class SecurityAdmin extends User {

    private static final long serialVersionUID = 1L;

    private Admin originalAdmin;

    public SecurityAdmin(Admin admin, List<GrantedAuthority> authorities){
        super(admin.getUserName(),admin.getUserPswd(),authorities);

        this.originalAdmin = admin;
        // 为了保证安全性，擦除放入originalAdmin的对象的密码
        this.originalAdmin.setUserPswd(null);
    }

    public Admin getOriginalAdmin(){
        return this.originalAdmin;
    }
}
