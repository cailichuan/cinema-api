package com.cinema.main_api.auth;


import model.support.ResponseResult;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import util.JwtTokenUtil;
import util.ResponseUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MySecurityFilter extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        //从请求头中取出token
        String token = request.getHeader(JwtTokenUtil.TOKEN_HEADER);

        //token为空则放行
        //如果接下来进入的Url不是公共的地址SpringSecurity会返回403的错误
        if (token == null || token.equals("null")) {
            filterChain.doFilter(request,response);
            return;
        }


        //判断JWT Token是否过期
        if (JwtTokenUtil.isExpiration(token)){
            ResponseUtil.writeJson(response,new ResponseResult<>(403,"令牌已经过期请重新登录" ));
            return;
        }

        //解析JWT获取用户信息
        String userName = JwtTokenUtil.getUserName(token);
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for (String tokenRole : JwtTokenUtil.getTokenRoles(token)) {
            authorities.add(new SimpleGrantedAuthority(tokenRole));
        }


        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userName,null,authorities)
        );

        filterChain.doFilter(request,response);
    }
}
