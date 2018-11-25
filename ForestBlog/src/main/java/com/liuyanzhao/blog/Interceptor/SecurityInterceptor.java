package com.liuyanzhao.blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author liuyanzhao
 */
public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws IOException {
        System.out.println("SecurityInterceptor...preHandle...");
        //这里可以根据session的用户来判断角色的权限，根据权限来转发不同的页面
        if(request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)  {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e)  {

    }
}

