package com.lck.crowd.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/***
 #Create by LCK on 2022/11/23
 # 用法: 公共返回值
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultEntity<T> {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    //用来封装当前请求的处理结果是成功还是失败
    private String result;

    //请求处理失败时返回的错误信息
    private String message;

    //要返回的数据
    private T data;

    /**
     * 请求处理成功且不需要返回数据时使用的工具方法
     */
    public static <E> ResultEntity<E> successWithoutData(){
        return new ResultEntity<E>(SUCCESS,null,null);
    }

    /**
     * 请求处理成功且需要返回数据时使用的工具方法
     */
    public static <E> ResultEntity<E> successWithoutData(E data){
        return new ResultEntity<E>(SUCCESS,null,data);
    }

    /**
     * 请求处理失败使用的工具方法
     */
    public static <E> ResultEntity<E> failed(String message){
        return new ResultEntity<>(FAILED,message,null);
    }
}
