package com.lck.crowd.mvc.handler;

import com.lck.crowd.entity.Admin;
import com.lck.crowd.service.AdminService;
import com.lck.crowd.util.CrowdUtil;
import com.lck.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 #Create by LCK on 2022/11/22
 # 用法: 
 */
@RestController
public class TestHandler {
    Logger logger = LoggerFactory.getLogger(TestHandler.class);
    @Autowired
    private AdminService adminService;

    @RequestMapping("/test/ssm.html")
    public String testSSm(ModelMap modelMap, HttpServletRequest request){
        boolean judgeResult = CrowdUtil.judgeRequestType(request);
        logger.info("judgeResult:"+judgeResult);
        List<Admin> adminList = adminService.getAll();
        modelMap.addAttribute("adminList",adminList);
        return "target";
    }

    @PostMapping("send/array/one.json")
    public ResultEntity<List<Integer>> arrayOne(@RequestParam("array[]")List<Integer> array){
        for (Integer integer : array) {
            System.out.println("array:"+integer);
        }
        System.out.println(10/0);
        return ResultEntity.successWithoutData(array);
    }

    /**
     * 错误写法， 建议写成复杂对象
     */
    @PostMapping("send/array/two.json")
    public ResultEntity<List<Integer>> arrayTwo(@RequestParam("array")List<Integer> array){

        for (Integer integer : array) {
            System.out.println("array:"+integer);
        }
        return ResultEntity.successWithoutData(array);
    }

}
