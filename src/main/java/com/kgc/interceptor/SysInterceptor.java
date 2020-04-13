package com.kgc.interceptor;

import com.kgc.pojo.BackendUser;
import com.kgc.pojo.DevUser;
import com.kgc.tools.Constants;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/7
 * @Explain:
 */
public class SysInterceptor extends HandlerInterceptorAdapter {
    private Logger log=Logger.getLogger(SysInterceptor.class);

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)throws  Exception{
        log.debug("==========SysInterceptor preHandle!==========");
        HttpSession session=request.getSession();
        BackendUser backendUser=(BackendUser) session.getAttribute(Constants.USER_SESSION);
        DevUser devUser=(DevUser) session.getAttribute(Constants.DEV_USER_SESSION);
        if (backendUser!=null || devUser!=null){
            return true;
        }else{
            response.sendRedirect(request.getContextPath()+"/403.jsp");
            return false;
        }
    }
}
