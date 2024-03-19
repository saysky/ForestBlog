package com.liuyanzhao.ssm.blog.Filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XssFilter implements Filter {
    @Override
    public void destroy() {
    }
    /**
     * 过滤器用来过滤的方法
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //包装request
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
