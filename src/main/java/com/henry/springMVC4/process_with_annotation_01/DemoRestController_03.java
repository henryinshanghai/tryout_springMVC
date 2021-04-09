package com.henry.springMVC4.process_with_annotation_01;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 1 控制器类型；  2 返回的是数据，而不是页面；
@RequestMapping("/rest")
public class DemoRestController_03 {

    // 这里返回数据的媒体类型是 application/json, 而不是 text/plaintext
    @RequestMapping(value = "/getjson", produces = "application/json;charset=UTF-8")
    public DemoObj_01 getJson(DemoObj_01 obj01) {
        // 方法可以直接返回一个对象，有某种机制存在 - 会把java对象转化成为 json对象
        return new DemoObj_01(obj01.getId() + 1, obj01.getName() + "yy");
    }

    // 这里返回数据的媒体类型是 application/xml
    @RequestMapping(value = "/getxml", produces = "application/xml;charset=UTF-8")
    public DemoObj_01 getxml(DemoObj_01 obj) {
        // 同样 会完成 java对象到xml格式数据的转换
        return new DemoObj_01(obj.getId() + 1, obj.getName() + "yy");
    }
}
/*
用于验证的请求：
    http://localhost:8080/springmvcDemo/rest/getxml?id=1&name=xx
    http://localhost:8080/springmvcDemo/rest/getjson?id=1&name=xx
 */
