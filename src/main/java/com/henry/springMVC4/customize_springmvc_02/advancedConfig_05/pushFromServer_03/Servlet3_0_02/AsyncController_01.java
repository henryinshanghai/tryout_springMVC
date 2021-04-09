package com.henry.springMVC4.customize_springmvc_02.advancedConfig_05.pushFromServer_03.Servlet3_0_02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
public class AsyncController_01 {

    @Autowired
    PushService_02 pushService02;

    /*
        为什么需要实现异步任务？
            答：###
        如何实现异步任务？
            通过控制器 从另一个线程返回一个 DeferredResult实例 - 此处从pushService中获取到的
     */
    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredCall() {
        return pushService02.getAsyncUpdate();
    }
}
