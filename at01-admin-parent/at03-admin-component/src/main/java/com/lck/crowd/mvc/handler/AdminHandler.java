package com.lck.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.lck.crowd.entity.Admin;
import com.lck.crowd.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;

import static com.lck.crowd.constant.CrowdConstant.ATTR_NAME_LOGIN_ADMIN;
import static com.lck.crowd.constant.CrowdConstant.ATTR_NAME_PAGE_INFO;

/***
 #Create by LCK on 2022/11/23
 # 用法: 登录控制器
 */
@Controller
//@Controller
public class AdminHandler {

    private Logger logger = LoggerFactory.getLogger(AdminHandler.class);
    @Autowired
    private AdminService adminService;

    @PostMapping("admin/do/login.html")
    public String doLogin(@RequestParam("login") String login, @RequestParam("userPswd") String userPswd, HttpSession session) {
        Admin admin = adminService.getAdminByLoginAcct(login, userPswd);
        session.setAttribute(ATTR_NAME_LOGIN_ADMIN, admin);
        return "redirect:/admin/to/main/page.html";
    }

    @PostMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session) {
        //强制session失效
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }

    @PostMapping("/admin/get/page.html")
    public String getPage(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          ModelMap modelMap) {
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        logger.info("pageInfo:{}",pageInfo);
        modelMap.addAttribute(ATTR_NAME_PAGE_INFO, pageInfo);
        return "admin-page";

    }

    @PostMapping("/admin/remove/{id}/{pageNum}/{keyword}.html")
    public String remove(@PathVariable("id")Integer id,
                         @PathVariable("pageNum") Integer pageNum,
                         @PathVariable("keyword")String keyword){
        Integer result = adminService.removeById(id);
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    @PostMapping("/admin/save.html")
    public String save(@RequestBody Admin admin){
        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;
    }
    @GetMapping("/admin/to/edit/page.html")
    public String toEditPage(@RequestParam("id")Integer id,
                             ModelMap modelMap){
        Admin admin = adminService.getAdminById(id);
        modelMap.addAttribute("admin",admin);
        return "admin-edit";
    }
    @PostMapping("/admin/update.html")
    public String update(@RequestBody Admin admin,
                         @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                         @RequestParam(value = "keyword",defaultValue = "")String keyword){
        adminService.updateAdmin(admin);
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;

    }

}
