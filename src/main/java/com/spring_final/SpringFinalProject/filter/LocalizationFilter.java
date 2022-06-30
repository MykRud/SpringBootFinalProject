package com.spring_final.SpringFinalProject.filter;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", dispatcherTypes = {DispatcherType.REQUEST})
@Order(Ordered.LOWEST_PRECEDENCE - 4)
public class LocalizationFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if(request.getParameter("lang") != null){

            request.getSession().setAttribute("lang", request.getParameter("lang"));


        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
