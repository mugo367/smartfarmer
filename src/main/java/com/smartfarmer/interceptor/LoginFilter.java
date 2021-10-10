package com.smartfarmer.interceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String servletPath = httpServletRequest.getServletPath();

        System.out.println("Servlet Path: " + servletPath);

        HttpSession session = httpServletRequest.getSession();

        if (session.getAttribute("username") == null){
            session.invalidate();

            if (servletPath.equals("/login") || servletPath.equals("/register") ) {
                chain.doFilter(request, response);
            }

            else if(servletPath.equals("/index.jsp") || servletPath.equals("/register.jsp") ||(servletPath.equals("/login.jsp")) ){
                chain.doFilter(request, response);
                response.getWriter().flush();
            } else {
                ((HttpServletResponse) response).sendRedirect("http://localhost:8080/SmarFarmer/index.jsp");
            }
        }else{
            chain.doFilter(request, response);
        }
    }

    public void destroy() {

    }

}
