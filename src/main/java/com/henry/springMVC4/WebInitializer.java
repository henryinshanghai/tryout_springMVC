package com.henry.springMVC4;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/*
疑问：
1 为什么自己创建就会有 方法找不到的编译报错，而拷贝过来就没有？
2 为什么实现 接口中的抽象方法时，不能添加注解@Override??
 */
/*
WebApplicationInitializer：
    是：Spring提供的一个接口；
    作用：用来配置 Servlet3.0+的相关信息 - 从而替代web.xml文件；
    特征：
        实现了这个接口的类型 会
            被SpringServletContainerInitializer(这个类的作用是：启动Servlet3.0容器)
                自动获取到；
 */
public class WebInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MyMVCConfig.class); // 2-1 向Spring容器中注册 一个配置类
        ctx.setServletContext(servletContext); //2-2 把 配置型 与 当前的servletContext相关联

        //3 向Spring容器中 注册 SpringMVC的 DispatcherServlet类型
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        servlet.setAsyncSupported(true);//1

	}

}
