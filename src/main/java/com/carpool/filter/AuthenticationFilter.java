package com.carpool.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Crawlers on 4/25/2017.
 */
@Component
public class AuthenticationFilter implements Filter {
    private String[] excludedUrl = {"login", "css", "js", "signup", "socket", "images"};
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        HttpSession httpSession = httpServletRequest.getSession();
        String user = (String) httpSession.getAttribute("loggedUser");

        boolean containPath = false;
        String URI = httpServletRequest.getRequestURI();
        for (String str : excludedUrl){
            if (URI.contains(str)){
                containPath = true;
                break;
            }
        }
        if (containPath || user!=null){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login");
        }
    }

    @Override
    public void destroy() {

    }
}
