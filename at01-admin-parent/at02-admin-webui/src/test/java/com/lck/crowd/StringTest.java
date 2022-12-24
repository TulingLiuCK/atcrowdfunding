package com.lck.crowd;

import com.lck.crowd.util.CrowdUtil;
import org.junit.jupiter.api.Test;

/***
 #Create by LCK on 2022/11/23
 # 用法: 
 */
public class StringTest {
    @Test
    public void test(){
        String soucr = "123123";
        String s = CrowdUtil.MD5(soucr);
        System.out.println(s);
    }
}
