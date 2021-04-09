package com.henry.springMVC4.customize_springmvc_02.advancedConfig_05.messageConverter_02;

import com.henry.springMVC4.process_with_annotation_01.DemoObj_01;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConverterController {

    // 这里指定 返回的媒体类型 为 自定义的媒体类型 application/henry
    @RequestMapping(value = "/convert", produces = {"application/henry"})
    public @ResponseBody DemoObj_01 convert(@RequestBody DemoObj_01 demoObj01) {
         return demoObj01;
    }
}
