package com.henry.springMVC4.customize_springmvc_02.springmvcTest_06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController_04 {
	
	@Autowired
	DemoService_01 demoService;
	
	@RequestMapping(value = "/testRest" ,produces="text/plain;charset=UTF-8")
	public @ResponseBody
    String testRest(){
		return demoService.saySomething();
	}

}
