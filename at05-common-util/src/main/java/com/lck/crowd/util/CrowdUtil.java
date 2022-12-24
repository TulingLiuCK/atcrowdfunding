package com.lck.crowd.util;

import com.lck.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/***
 #Create by LCK on 2022/11/23
 # 用法:  判断请求类型的工具方法
 */
public class CrowdUtil {
    /**
     * 判断当前请求是否为Ajax请求
     *
     * @param request 请求对象
     * @return true 当前请求是Ajax请求
     */
    public static boolean judgeRequestType(HttpServletRequest request) {

        //获取请求消息头
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Requested-With");

        //判断

        return (acceptHeader != null && acceptHeader.contains("application/json"))
                || (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
    }

    /**
     * 对明文字符串进行MD5加密
     * @param source 传入的明文字符串
     * @return 加密结果
     */
    public static String MD5(String source) {
        //判断source是否有效
        if (source == null || source.length() == 0) {
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }
        //获取MEssageDiges对象
        String algorithm = "md5";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            //获取明文字符串对应的字节数组
            byte[] input = source.getBytes();
            //执行加密
            byte[] output = messageDigest.digest(input);
            //创建BigInteger对象
            int signum = 1; //正数
            BigInteger bigInteger = new BigInteger(signum, output);
            //按照16禁止将bigInteger转换为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
