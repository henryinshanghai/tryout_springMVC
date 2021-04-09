package com.henry.springMVC4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan("com.henry.springMVC4")
@EnableWebMvc // 开启 Spring对WebMVC的支持(aka 开启一些默认配置)
public class MyMVCConfig_simple { // bookmark3: 对SpringMVC的配置

    // 配置一个视图解析器 ViewResolver
    /*
        SpringMVC中的 ViewResolver；
        特征：是 SpringMVC 中 视图渲染的核心；
        是： SpringMVC框架中的一个接口；
        用法：
            1 开发者实现这个接口，并重写其中的 resolveViewName()方法；
            2 方法的返回值是 View接口类型。
            3 View的作用 - 使用model、request、response对象来渲染得到视图(html、json、xml、pdf等)， 然后把渲染的结果返回给浏览器
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        // 设置视图解析器的属性
        // 这个路径前缀有点子奇怪 - 它并不是开发时程序员放置视图文件的目录，而是一个全新的目录
        /*
            解释：
                1 最终在浏览器上看到的页面效果是 运行时的代码 呈现出来的。
                2 而在运行时，代码/框架/Maven会把我们编写的页面 自动编译到 /WEB-INF/classes/view 目录下。
                3 在Spring Boot中，如果使用 Thymeleaf作为模板技术，就不会需要这样的配置(因为页面没有 被编译然后被归置到指定目录下的过程)
         */
        viewResolver.setPrefix("/WEB-INF/classes/views/"); // 路径前缀
        viewResolver.setSuffix(".jsp"); // 视图文件名后缀
        viewResolver.setViewClass(JstlView.class); // 具体的视图类型

        return viewResolver;
    }

}
