package com.lck.crowd.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/***
 #Create by LCK on 2022/11/25
 # 用法: 
 */
@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication().withUser("jerry").password("123123").roles("ADMIN");
        //正式功能中使用基于数据库的认证
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        String[] permitUrls = {"/index.jsp","/bootstrap/**",
                "/crowd/**","/css/**","/fonts/**","/img/**",
                "/jquery/**","/layer/**","/script/**","/ztree/**","/admin/login/page.html"};
        security
                .authorizeRequests()        // 表示对请求进行授权
                .antMatchers(permitUrls)    // 传入的ant风格的url
                .permitAll()                // 允许上面的所有请求，不需要认证

                .antMatchers("/admin/page/page.html")   // 设置要得到admin的分页信息
                .access("hasRole('经理') or hasAuthority('user:get')") // 必须具有经理的角色

                //.antMatchers("/admin/page/page.html")
                //.hasRole("经理")
                .anyRequest()               // 其他未设置的全部请求
                .authenticated()            // 表示需要认证

                .and()
                .csrf()         // 设置csrf
                .disable()      // 关闭csrf

                .formLogin()                                    // 开启表单登录功能
                .loginPage("/admin/login/page.html")            // 指定登陆页面
                .usernameParameter("login-user")                // 设置表单中对应用户名的标签的name属性名
                .passwordParameter("login-pwd")                 // 设置表单中对应密码的标签的name属性名
                .loginProcessingUrl("/security/do/login.html")  // 设置登录请求的提交地址
                .defaultSuccessUrl("/admin/main/page.html")     // 设置登陆成功后前往的地址
                .and()
                .logout()                                       // 开启退出登录功能
                .logoutUrl("/security/do/logout.html")          // 设置退出登录的url
                .logoutSuccessUrl("/admin/login/page.html")    // 设置退出成功后前往的页面

//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(new AccessDeniedHandler() {
//                    @Override
//                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
//                        httpServletRequest.setAttribute("exception", new Exception("抱歉，您没有权限访问该资源！"));
//                        httpServletRequest.getRequestDispatcher("/WEB-INF/system-error.jsp").forward(httpServletRequest,httpServletResponse);
//                    }
//                })
        ;

    }
}
