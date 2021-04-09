package com.henry.springMVC4.customize_springmvc_02.advancedConfig_05.pushFromServer_03.SSE_01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class SseController_01 {

    // produces属性 用于 指定方法返回值的媒体类型为 event-stream ———— 这是 SSE服务器端 的支持
    @RequestMapping(value = "/push", produces = "text/event-stream")
    public @ResponseBody String push() {
        Random r = new Random();

        try {
            Thread.sleep(500); // 睡5秒钟
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 向客户端返回一条消息
        return "data:Testing 1,2,3" + r.nextInt() + "\n\n";
    }
}
