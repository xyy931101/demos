package com.examplexyy.demo.web.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: Xiongyy
 * @Date: 2021/3/21 11:35
 * .............................................
 * 佛祖保佑             永无BUG
 */
@Order
@WebFilter(filterName = "myFilter", urlPatterns = "/api")
public class MyWebFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }
}
