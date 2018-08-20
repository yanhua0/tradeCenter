package org.trade.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.trade.entity.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //首先进入的方法

        //return false表示拦截，不向下执行
        //return true表示放行
//        System.out.println(request.getServletPath());
        HttpSession session = request.getSession();
        Users u=(Users)session.getAttribute("users");
        String url=request.getRequestURI();

        if(url.indexOf("/check")>=0)
        {   System.out.println("/check");
            return true;
        }
        if(url.indexOf("/add")>=0)
        {
            return true;
        }
        if(url.indexOf("/mesCheck")>=0)
        {
            return true;
        }
        if(url.indexOf("/login")>=0)
        {
            return true;
        }
        if(url.indexOf("/show")>=0)
        {
            return true;
        }
        if(url.indexOf("/findAll")>=0)
        {
            return true;
        }
        if(url.indexOf("/getVerifyCode")>=0)
        {
            return true;
        }
        if(u!=null){

            return true;
        }else{//登录页面跳转

            PrintWriter out=response.getWriter();
            out.println("<html>");
            out.println("<script>");
            out.println("window.open ('"+request.getContextPath()+"login','_top')");
            out.println("</script>");
            out.println("</html>");
            return false;
        }

    }
    //返回modelAndView之前执行

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

       // System.out.println("postHandle");
    }
    //执行Handler完成执行此方法

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

//        System.out.println("afterCompletion");
    }
}