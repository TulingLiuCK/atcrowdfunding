package com.lck.spring;

import com.lck.spring.entity.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 #Create by LCK on 2022/11/25
 # 用法: 
 */
//表示当前是一个配置类
@Configuration
public class MyAnnotationConfiguration {

    @Bean
    public Employee getEmplyee(){
        return new Employee();
    }
}
