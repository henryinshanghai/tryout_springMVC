package com.henry.springMVC4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 1 声明 这是一个用作为控制器的类型
public class HelloController {

    // 2 配置/设置  URL与控制器方法之间的映射关系
    @RequestMapping("/index")
    public String hello() {
        return "index"; // 返回视图文件名称 + 在MVCConfig中配置的路径前缀 与 视图文件后缀名称 => 找到具体的视图文件返回
    }


}
