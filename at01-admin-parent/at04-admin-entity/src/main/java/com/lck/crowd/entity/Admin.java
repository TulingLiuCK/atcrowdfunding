package com.lck.crowd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/***
 #Create by LCK on 2022/11/22
 # 用法: 
 */
@Data
public class Admin {
    private Integer id;

    private String login;

    private String userPswd;

    private String userName;

    private String email;

    private String createTime;


    public Admin() {
    }

    public Admin(Integer id, String login, String userPswd, String userName, String email, String createTime) {
        this.id = id;
        this.login = login;
        this.userPswd = userPswd;
        this.userName = userName;
        this.email = email;
        this.createTime = createTime;
    }
}
