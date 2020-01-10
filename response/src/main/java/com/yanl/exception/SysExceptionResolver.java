package com.yanl.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: YanL
 * @Time: 13:17 2020/1/10
 * @Email: imyanl.dt@gmail.com
 * @Description: 自定义异常处理器
 */
@Component("sysExceptionResolver")
public class SysExceptionResolver implements HandlerExceptionResolver {
    /**
     * 处理异常业务逻辑
     * @param request
     * @param response
     * @param handler
     * @param ex 获取到的异常
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        SysException e = null;
        if(ex instanceof SysException){
            e = (SysException) ex;
        }else {
            e = new SysException("系统正在维护....");
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMsg", e.getMessage());
        mv.setViewName("error");
        return mv;
    }
}
