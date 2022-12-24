package com.lck.crowd.service.Impl;

import com.lck.crowd.entity.Menu;
import com.lck.crowd.entity.MenuExample;
import com.lck.crowd.mapper.MenuMapper;
import com.lck.crowd.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 #Create by LCK on 2022/11/24
 # 用法: 
 */
@Service
public class MenuServiceImpl implements MenuService {
    private Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }

    @Override
    public void saveMenu(Menu menu) {
        int result = menuMapper.insert(menu);
        if (result>0){
            logger.info("新增成功");
        }
    }

    @Override
    public void updateMenu(Menu menu) {
        int result = menuMapper.updateByPrimaryKey(menu);
        if (result>0){
            logger.info("修改成功");
        }
    }

    @Override
    public void removeMenu(Integer id) {
        int result = menuMapper.deleteByPrimaryKey(id);
        if (result>0){
            logger.info("删除成功");
        }
    }
}
