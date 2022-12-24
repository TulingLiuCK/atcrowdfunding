package com.lck.crowd;


import com.github.pagehelper.PageInfo;
import com.lck.crowd.entity.Admin;
import com.lck.crowd.entity.Menu;
import com.lck.crowd.entity.Role;
import com.lck.crowd.mapper.AdminMapper;
import com.lck.crowd.mapper.AuthMapper;
import com.lck.crowd.mapper.MenuMapper;
import com.lck.crowd.mapper.RoleMapper;
import com.lck.crowd.service.AdminService;
import com.lck.crowd.service.AuthService;
import com.lck.crowd.service.MenuService;
import com.lck.crowd.service.RoleService;
import org.junit.jupiter.api.Test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/***
 #Create by LCK on 2022/11/22
 # 用法: 
 */
@SpringJUnitConfig(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {
    Logger logger = LoggerFactory.getLogger(CrowdTest.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuService menuService;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthMapper authMapper;

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testMapeer(){
        logger.info("info!!!");
        for (int i = 0; i < 10; i++) {
            Admin admin1 = new Admin();
            Admin admin = new Admin(null, "tom"+i, "123123", "汤姆", "tom@qq.com", null);
            int insert = adminMapper.insert(admin);
            System.out.println(insert);
        }


//        Admin admin1 = adminMapper.selectByAcct("jerry");
//        logger.info("admin1:"+admin1);
//        System.out.println(admin1);
    }
    @Test
    public void saveServiceTest(){
//        Admin admin = new Admin(null, "jerry", "123123", "汤姆", "tom@qq.com", null);
//        System.out.println(adminService.getAll());
        Admin jerry = adminService.getAdminByLoginAcct("jerry", "123123");
        System.out.println(jerry);
    }
    @Test
    public void test1(){
//        Admin admin = new Admin();
//        admin.setLogin("jerr");
//        logger.info("admin:"+admin);
        String keyword = "jerr";
        List<Admin> admins = adminMapper.selectAdminByKeyWord(keyword );
        System.out.println(admins);
    }
    @Test
    public void tset2(){
//        Integer integer = adminService.removeById(142);
//        System.out.println(integer);
        Admin adminById = adminService.getAdminById(19);
        System.out.println(adminById);
    }

    /*role Tese*/
    @Test
    public void roleTest(){
//        List<Role> role = roleMapper.selectRoleByKeyword("三");
//        for (Role role1 : role) {
//            System.out.println(role1);
//        }
        PageInfo<Role> roleList = roleService.getPageInfo(1, 2, "");
        System.out.println(roleList);
        System.out.println(roleList.isHasNextPage());
    }
    @Test
    public void roleTest2(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        roleService.removeRole(list);
    }
    @Test
    public void test3(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }
    /*MenuTest*/
    @Test
    public void test4(){
        List<Menu> all = menuService.getAll();
        for (Menu menu : all) {
            System.out.println(menu);
        }
    }
    @Test
    public void test5(){
        List<Role> roles = roleMapper.selectAssignedRole(1);
        for (Role role : roles) {
            System.out.println(role);
        }
        List<Role> roles1 = roleMapper.selectUnAssignedRole(1);
        for (Role role : roles1) {
            System.out.println(role);
        }
    }
    @Test
    public void tset6(){
//        adminMapper.deleteOldRelationShip(2);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        adminMapper.insertNewRelationShip(2,list);;

    }
    @Test
    public void tset7(){
//        List<Auth> all = authService.getAll();
//        for (Auth auth : all) {
//            System.out.println(auth);
//        }
        List<Integer> integers = authMapper.selectAssignedAuthIdByRoleId(1);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
    @Test
    public void test8(){
//        authMapper.deleteOldRealtionShip(1);
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        int i = authMapper.insertNewRelationShip(1, list);
        System.out.println(i);
    }

    @Test
    public void test9(){
        List<String> strings = authMapper.selectAssignedAuthNameByAdminId(1);
        for (String string : strings) {
            System.out.println(string);
        }
    }
    @Test
    public void test10(){
        Admin admin = new Admin(null, "lck", "123123", "123123", "123123", "2022");
        adminService.saveAdmin(admin);
    }
}
