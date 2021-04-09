package com.henry.springMVC4.customize_springmvc_02.controllerAdvice_03;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

// 作用：把 对控制器的全局配置 放到同一个类型中
@ControllerAdvice // 声明当前类型是一个控制器建言(advice)     这个注解组合了@Component，所以会把类型注册成为Spring容器中的bean实例
public class ExceptionHandlerAdvice_01 {

    // 全局处理 当前控制器类型中出现的异常
    // 手段：使用@ExceptionHandler()注解
    @ExceptionHandler(value = Exception.class) // 使用value属性 来 过滤 拦截的条件(此处为 拦截所有的Exception)
    public ModelAndView exception(Exception exception, WebRequest request) {
        // 设置出现异常时会跳转的页面为 error页面
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }

    // 把 键值对信息 绑定到 model对象中；
    // 作用：model对象中的键值对 可以 在当前类型中 注解了@RequestMapping的任意方法中 无差别地使用
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "额外信息");
    }

    // 设置 WebDataBinder
    // 作用：用来自动绑定前台请求参数 到 Model中去
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        // 设置的配置： 忽略request中的id参数
        webDataBinder.setDisallowedFields("id");
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("index").setViewName("/index");
//    }
}
