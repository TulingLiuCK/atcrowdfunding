package com.lck.crowd.mvc.config;

import com.google.gson.Gson;

import com.lck.crowd.exception.LoginAcctAlreadyInUserForUpdateException;
import com.lck.crowd.exception.LoginFailedException;
import com.lck.crowd.util.CrowdUtil;
import com.lck.crowd.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.lck.crowd.constant.CrowdConstant.ATTR_NAME_EXCPEITON;

/***
 #Create by LCK on 2022/11/23
 # 用法:  基于注解的异常映射
 @ControllerAdvice表示当前类是一个异常处理类
 */
@ControllerAdvice
public class CrowdExceptionResolver {


    /**
     * 将一个具体的异常类型和一个方法关联起来
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolveNullPointerExcpeteion(NullPointerException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "system-error";
        return commonResolveExcpetion(viewName,exception,request,response);
    }

    //登录异常
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveMathExcetpion(ArithmeticException exception,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "admin-login";
        return commonResolveExcpetion(viewName,exception,request,response);
    }
    //异常
    @ExceptionHandler(value = LoginAcctAlreadyInUserForUpdateException.class)
    public ModelAndView LoginAcctAlreadyInUserForUpdateException(NullPointerException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "system-error";
        return commonResolveExcpetion(viewName,exception,request,response);
    }

    /**
     *
     * @param viewName 跳转的视图
     * @param exception 实际捕获到的异常类型
     * @param request 当前请求对象
     * @param response 响应对象
     * @return
     * @throws IOException
     */
    private ModelAndView commonResolveExcpetion(String viewName,Exception exception,HttpServletRequest request,HttpServletResponse response) throws IOException {
        //判断当前请求类型
        boolean judgeResult = CrowdUtil.judgeRequestType(request);
        //如果是Ajax请求
        if (judgeResult){
            ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());
            //创建Gson对象
            Gson gson = new Gson();
            String json = gson.toJson(resultEntity);
            //将json字符串作为响应体返回给浏览器
            response.getWriter().write(json);
            //由于上面已经通过原生response对象返回了响应，所以不提供ModelAndView对象
            return null;
        }
        //不是Ajax请求 则创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ATTR_NAME_EXCPEITON,exception);
        //设置对应视图名称
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
