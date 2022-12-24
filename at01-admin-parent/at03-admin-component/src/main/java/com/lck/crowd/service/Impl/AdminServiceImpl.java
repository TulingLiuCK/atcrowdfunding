package com.lck.crowd.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lck.crowd.entity.Admin;
import com.lck.crowd.entity.AdminExample;
import com.lck.crowd.exception.LoginFailedException;
import com.lck.crowd.mapper.AdminMapper;
import com.lck.crowd.service.AdminService;
import com.lck.crowd.util.CrowdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.lck.crowd.constant.CrowdConstant.*;

/***
 #Create by LCK on 2022/11/22
 # 用法: 
 */
@Service
public class AdminServiceImpl implements AdminService {

    private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void saveAdmin(Admin admin) {
        String userPswd = admin.getUserPswd();
//        String pwd = CrowdUtil.MD5(userPswd);
        userPswd = passwordEncoder.encode(userPswd);
        admin.setUserPswd(userPswd);
        logger.info("userPswd:{}",userPswd);
        Date date = new Date();
        String date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        admin.setCreateTime(date1);
        logger.info("admin:{}", admin);
        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增失败");
        }
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> admins = adminMapper.selectByExample(new AdminExample());
        return admins;
    }

    @Override
    public Admin getAdminByLoginAcct(String login, String userPswd) {
        //根据登录账号查询Admin对象
        Admin admin = adminMapper.selectByAcct(login);
        //判断Admin是否为null
        if (admin == null) {
            //如果Admin对象为null则抛出异常
            throw new LoginFailedException(MESSAGE_LOGIN_FAILED);
        }
        //将输入密码进行加密
        String pwd = CrowdUtil.MD5(userPswd);
        logger.info("加密后的密码为,{}", pwd);
        //获取数据库中的密码
        String userPwd = admin.getUserPswd();
        logger.info("数据库中的密码为：{}", userPwd);
        //对密码进行比较
        if (!Objects.equals(pwd, userPwd)) {
            throw new LoginFailedException(MESSAGE_LOGIN_FAILED);
        }
        return admin;
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //执行查询
        List<Admin> list = adminMapper.selectAdminByKeyWord(keyword);
        //封装到pageInfo中
        return new PageInfo<>(list);
    }

    @Override
    public Integer removeById(Integer id) {
        int result = adminMapper.deleteByPrimaryKey(id);
        return result;
    }

    @Override
    public Admin getAdminById(Integer id) {
        Admin admin = adminMapper.selectByPrimaryKey(id);
        return admin;
    }

    @Override
    public void updateAdmin(Admin admin) {
        //有选择的更新
        int i = adminMapper.updateByPrimaryKeySelective(admin);
        if (i > 0) {
            logger.info("更新成功");
        }
    }

    @Override
    public void saveAdminRoleReolation(Integer adminId, List<Integer> roleIdList) {
        //先根据adminId删除旧的数据，再根据adminId保存心得
        int result = adminMapper.deleteOldRelationShip(adminId);
        if (result > 0){
            logger.info("删除旧的adminId成功");
        }
        //保存新的
        if (roleIdList != null && roleIdList.size() > 0) {
            int resultNew = adminMapper.insertNewRelationShip(adminId, roleIdList);
            if (resultNew>0){
                logger.info("新增{}条关系数据",resultNew);
            }

        }
    }

    @Override
    public Admin getAdminByLoginAcct(String username) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginEqualTo(username);
        List<Admin> list = adminMapper.selectByExample(adminExample);
        Admin admin = list.get(0);
        return admin;
    }
}
