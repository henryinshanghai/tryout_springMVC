package com.henry.springMVC4.customize_springmvc_02;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* step01 自定义一个类型 来作为 拦截器类型 */
public class DemoInterceptor_02 extends HandlerInterceptorAdapter { // 继承自 HandlerInterceptorAdapter 类型

    /* 1 定义在处理请求之前 需要做的事情 */
    // 手段：重写 preHandle 方法
    // 特征：在请求发生之前执行
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }

    // 在请求发生之后执行
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        Long startTime = (Long)request.getAttribute("startTime");
        request.removeAttribute("startTime");

        long endTime = System.currentTimeMillis();

        System.out.println("本次请求的处理时间为： " + (endTime - startTime) + "ms");
        request.setAttribute("handlingTime", endTime - startTime);
    }
}
