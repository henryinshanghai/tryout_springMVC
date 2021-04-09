package com.henry.springMVC4.customize_springmvc_02.otherConfig_04;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class quickJumpControllerDemo_01 {

    @RequestMapping("/ToIndex")
    public String hello() {
        return "index"; // 没有业务处理，只是单纯地做了跳转页面的动作
    }



}
