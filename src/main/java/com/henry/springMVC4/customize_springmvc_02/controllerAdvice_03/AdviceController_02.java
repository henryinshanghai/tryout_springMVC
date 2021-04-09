package com.henry.springMVC4.customize_springmvc_02.controllerAdvice_03;

import com.henry.springMVC4.process_with_annotation_01.DemoObj_01;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdviceController_02 {

    @RequestMapping("/advice")
    public String getSomething(@ModelAttribute("msg") String msg, DemoObj_01 obj) {
        throw new IllegalArgumentException("非常抱歉，参数有误/" + "来自" +
                "@ModelAttribute： " + msg);
    }
}
/*
验证： 是否成功配置了全局的异常；
访问：http://localhost:8080/springmvcDemo/advice?id=1&name=henry

添加断点：
1 查看id属性的值有没有被过滤掉 - @InitBinder注解的功能
2 查看msg的信息是否是 在01中添加到model中的信息 - @ModelAttribute
3 查看发生异常后跳转到的页面是否是 在01中配置的错误页面 - @ExceptionHandler

如果以上验证都是成功的，说明我们成功地把对控制器的配置放到了 类型01中 - @ControllerAdvice
 */
