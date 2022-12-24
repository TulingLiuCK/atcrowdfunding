package com.lck.crowd.mvc.handler;

import com.lck.crowd.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/***
 #Create by LCK on 2022/11/25
 # 用法: 
 */
@RestController
public class AuthHandler {
    @Autowired
    private AuthService authService;
}
