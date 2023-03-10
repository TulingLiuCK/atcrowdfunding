package com.lck.crowd.service;

import com.lck.crowd.entity.Menu;

import java.util.List;

public interface MenuService {

    public List<Menu> getAll();

    void saveMenu(Menu menu);

    void updateMenu(Menu menu);

    void removeMenu(Integer id);
}
