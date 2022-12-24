package com.lck.spring;

import com.lck.spring.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/***
 #Create by LCK on 2022/11/25
 # 用法: 
 */
public class SpringTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyAnnotationConfiguration.class);
        Employee employee = applicationContext.getBean( Employee.class);
        System.out.println(employee);
    }
}
