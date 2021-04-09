package com.henry.springMVC4.customize_springmvc_02;

import com.henry.springMVC4.customize_springmvc_02.advancedConfig_05.messageConverter_02.MyMessageConverter_01;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

// 这里通过重新编写 MyMVCConfig来 对SpringMVC的行为进行定制/设置
@Configuration
@EnableWebMvc // 1 使用@EnableWebMVC 来 开启Spring对SpringMVC的支持
@EnableScheduling // 异步任务之 配置以开启 对于计划任务的支持
@ComponentScan("com.henry.springMVC4")
public class MyMVCConfig_for_more_custom_demand_01 extends WebMvcConfigurerAdapter { // 2 继承 WebMvcConfigurerAdapter 类型，并重写其抽象方法

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

    /* Ⅰ 定制化 SpringMVC中对于静态资源的映射规则 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**") // 声明 静态资源文件对外所暴露的访问路径
                .addResourceLocations("classpath:/assets/"); // 指定 静态资源文件所放置的目录
    }

    /* 在Spring容器中 指定自定义的拦截器类型 */
    @Bean
    public DemoInterceptor_02 demoInterceptor02() {
        return new DemoInterceptor_02();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor02()); // 这里传参的方式是 - 直接调用当前类的方法
    }

    /*
        定义全局的 从URI到页面的快捷跳转 - 不需要再重复地定义controller方法
            for quickJumpControllerDemo_01.java
        直接配置 URI 与 跳转页面之间的关系
      */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("ToIndex").setViewName("/index");
        registry.addViewController("/toUpload").setViewName("/upload"); // for file upload jump
        registry.addViewController("/converter").setViewName("/converter"); // for converter jump
        registry.addViewController("/sse").setViewName("/sse"); // for sse push to client


        registry.addViewController("/async").setViewName("/async"); // for async push to client

    }

    /*
        配置后，不再忽略URL中.后面的路径变量参数
        比如：http://localhost:8080/springmvcDemo/anno/pathvar/xx.yy
        配置之前的输出：http://localhost:8080/springmvcDemo/anno/pathvar/xx.yy can access, str: xx

        配置之后的输出：url: http://localhost:8080/springmvcDemo/anno/pathvar/xx.yy can access, str: xx.yy
        结果：路径参数被完整地获取到了
     */

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    /*
        for file upload;
        配置 MultipartResolver类型 的bean实例 - 用于处理文件上传的工作
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000L);

        return multipartResolver;
    }

    /* 配置/添加 自定义的MessageConverter相关的Bean */
    @Bean
    public MyMessageConverter_01 converter01() {
        return new MyMessageConverter_01();
    }

    // 在Spring MVC中注册 HttpMessageConverter 这个Bean
    // 为什么在Spring容器中注册过后，还需要再在Spring MVC中去注册
    // 完成这个目标，有两种手段：approach01 - 重写configureMessageConverters
    // approach02 - 重写extendMessageConverters
    // 两种方式有不同的特性，这里使用第二种方式

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter01());
    }
}
/*
note: 如果两个配置类型同名的话，在新创建的配置类型中添加的 新配置信息就不会生效。
解决手段：给旧的配置类型改一个名字。 MyMVCConfig_simple.java
 */
