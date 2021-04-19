package com.henry.springMVC4.customize_springmvc_02.springmvcTest_06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 定义控制器 - 处理请求 到 页面跳转 的映射关系
public class NormalController_03 {
	@Autowired
	DemoService_01 demoService;

	
	@RequestMapping("/normal")
	public String testPage(Model model){
		model.addAttribute("msg", demoService.saySomething());
		return "page";
	}

}
