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
/*
注：需要拼接出到 controller的method的完整路径，否则就会有404的错误

在项目结构 中，
    - Modules 与 Facets的配置信息 基本一致：项目使用到了哪一些框架。
    - Artifacts中，当想要以war包的形式部署时，选择 web application:archive;
        然后把可用元素 中的依赖项 添加到  WEB-INF/lib之下

为部署后的war包 配置一个URI：
    Edit Configuration - deployment:
        artifact -> application context(aka URL上下文中的路径信息)

拼接完成的URL：
    URL + application context + method_uri
aka
    http://localhost:8080/springmvcDemo/index
 */