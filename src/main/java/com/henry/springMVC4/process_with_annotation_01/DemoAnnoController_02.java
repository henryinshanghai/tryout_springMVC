package com.henry.springMVC4.process_with_annotation_01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/anno") // 映射到当前类型的访问路径为: /anno
public class DemoAnnoController_02 {

    /* 如何接收Web请求 */
    // 当前方法自动继承 注解到当前类上面的路径
    @RequestMapping(produces = "text/plain;charset=UTF-8") // 使用produces属性来 指定response的媒体类型与字符集合
    public @ResponseBody String index(HttpServletRequest request) { // 在Controller的方法中,可以使用 HttpServletRequest来作为参数
        // @ResponseBody注解添加在 返回值类型之前
        return "url:" + request.getRequestURL() + "can access";
    }

    /* 如何使用@PathVariable注解 来 接收路径参数 [/anno/pathvar/xxx]*/
    // 1 在路径中 引用路径变量
    @RequestMapping(value = "/pathvar/{str}", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String demoPathVar(@PathVariable String str, // 在方法参数列表中，使用@PathVariable注解 来 定义路径变量
                              HttpServletRequest request) {
        return "url: " + request.getRequestURL() + "can access, str: " + str; // 在方法体中使用参数列表中的参数
    }


    /* 如何接收Web请求 request中的参数 - 使用与之相同的的方法参数名 [/anno/requestParam?id=1]*/
    @RequestMapping(value = "/requestParam", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String passRequestParam(Long id,
            HttpServletRequest request) {

        return "url: " + request.getRequestURL() + "can access, id: " + id;
    }

    /* 如何把Web请求所包含的多个参数 绑定到 某个POJO类型的同名属性上 [/anno/obj?id=1&name=xxx]*/
    @RequestMapping(value = "/obj", produces = "application/json;charset=UTF-8")
    @ResponseBody // 使用方式2：把@ResponseBody注解添加到方法体上
    public String passObj(DemoObj_01 obj, HttpServletRequest request) {

        return "url: " + request.getRequestURL()
                + " can access, obj id is: " + obj.getId() +
                "obj name is: " + obj.getName();

    }

    /* 如何把多个不同的路径映射到 同一个controller方法上 */
    @RequestMapping(value = {"/name1", "/name2"}, produces = "text/plain;charset=UTF-8")
    public @ResponseBody String remove(HttpServletRequest request) {

        return "url: " + request.getRequestURL() + "can access";

    }

}
