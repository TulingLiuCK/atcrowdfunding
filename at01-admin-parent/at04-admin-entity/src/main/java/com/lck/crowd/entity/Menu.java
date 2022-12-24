package com.lck.crowd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    //主键
    private Integer id;
    //父节点
    private Integer pid;
    //节点名称
    private String name;
    //节点附带的URL地址，点击菜单项跳转的i地址
    private String url;
    //节点图标央视
    private String icon;
    //存储子节点的集合 初始化避免空指针异常
    private List<Menu> children = new ArrayList<>();
    //控制节点是否默认打开 设置为true默认打开
    private Boolean open = true;
}