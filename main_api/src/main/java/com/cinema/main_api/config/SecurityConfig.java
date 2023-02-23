package com.cinema.main_api.config;




import com.cinema.main_api.auth.MySecurityFilter;
import model.support.ResponseResult;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import util.ResponseUtil;

/**
 * SpringSecurity配置
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig   {

    /**
     * http配置
     * @param httpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        //开启跨域
        httpSecurity.csrf().disable().cors();

        //允许跨域使用iframe
        httpSecurity.headers().frameOptions().disable();

        //禁止使用session
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //身份验证失败
        httpSecurity.exceptionHandling().authenticationEntryPoint((request, response, authException) ->{
            ResponseUtil.writeJson(response,new ResponseResult<>(403,"身份认证失败, 请重新登录"));
        } );

        //使用过滤器
        //AuthenticationManagerDelegator()
        httpSecurity.addFilterBefore(new MySecurityFilter(), UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();
    }


    /**
     * SpringSecurity有默认的跨域配置 会无法放行RequestHeader带有"Authorization"请求
     * 防止前端请求api报出cors error
     * @return
     */

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        //设置访问源请求头
        corsConfiguration.addAllowedHeader("*");
        //设置访问源请求方法
        corsConfiguration.addAllowedMethod("*");
        //设置访问源地址
        corsConfiguration.addAllowedOrigin("*");
        //对接口配置跨域设置
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }





}
