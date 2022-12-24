package com.lck.crowd.mvc.handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lck.crowd.entity.Menu;
import com.lck.crowd.service.MenuService;
import com.lck.crowd.util.ResultEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 #Create by LCK on 2022/11/24
 # 用法: 
 */
@RestController
public class MenuHandler {
    @Autowired
    private MenuService menuService;


    @GetMapping("/menu/get/whole/tree.json")
    public ResultEntity<Menu> getWholeTree(){
        List<Menu> menuList = menuService.getAll();
        //声明一个变量用来存储找到的根节点
        Menu root = null;
        //创建Map对象用来存储id 和Menu对象的对应关系便于查找父节点
        Map<Integer,Menu> menuMap = new HashMap<>();
        //遍历menuList填充的menuMap
        for (Menu menu : menuList) {
            Integer id = menu.getId();
            menuMap.put(id,menu);
        }
        //再次遍历组装父子节点
        for (Menu menu : menuList) {
            //获取当前menu对象的pid属性
            Integer pid = menu.getPid();
            if (pid == null){
                root = menu;
                continue;
            }
            //如果pid不为null 说明当前节点有父节点 可以根据pid查找对应的menu对象
            Menu father = menuMap.get(pid);
            //将当前节点存入到父节点的children集合
            father.getChildren().add(menu);
        }
        return ResultEntity.successWithoutData(root);
    }

    //添加子节点
    @PostMapping("/menu/save.json")
    public ResultEntity<String> saveMenu(@RequestBody Menu menu){
        menuService.saveMenu(menu);
        return ResultEntity.successWithoutData();
    }
    //更新子节点
    @PostMapping("/menu/update.json")
    public ResultEntity<String> updateMenu(@RequestBody Menu menu){
        menuService.updateMenu(menu);
        return ResultEntity.successWithoutData();
    }
    //删除
    @PostMapping("/menu/remove.json")
    public ResultEntity<String> removeMenu(@RequestParam("id")Integer id){
        menuService.removeMenu(id);
        return ResultEntity.successWithoutData();
    }



//    @GetMapping("/ment/get/whole/tree")
//    public ResultEntity<Menu> getWholeTreeOld() {
//        //查询全部的Menu对象
//        List<Menu> menuList = menuService.getAll();
//        //声明一个变量用来存储找到的根节点
//        Menu root = null;
//
//        //遍历menuList
//        for (Menu menu : menuList) {
//            //获取当前pid
//            Integer pid = menu.getPid();
//            //检查是否为null
//            if (pid == null) {
//                root = menu;
//                continue;
//            }
//            //如果 pid 不为null 说明当前节点有父节点，建立父子关系
//            for (Menu maybeFather : menuList) {
//                //获取maybeFathver的id属性
//                Integer id = maybeFather.getId();
//                //将子节点的pid和疑似父节点的id进行比较
//                if (Objects.equals(pid, id)) {
//                    maybeFather.getChildren().add(menu);
//                    //找到退出
//                    break;
//                }
//            }
//        }
//        return ResultEntity.successWithoutData(root);
//    }
}
