package com.henry.springMVC4.customize_springmvc_02.advancedConfig_05.pushFromServer_03.Servlet3_0_02;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class PushService_02 {

    // 这是Spring框架提供的一个 用于支持异步特性的类型
    private DeferredResult<String> deferredResult; // deferred 推迟的

    // 在Service层中， 创建出 DeferredResult的bean实例 来 给控制器使用
    public DeferredResult<String> getAsyncUpdate() {
        deferredResult = new DeferredResult<String>();
        return deferredResult;
    }

    // 使用@Schedule 注解 来 定时更新 DeferredResult对象
    @Scheduled(fixedDelay = 5000)
    public void refresh() {
        if (deferredResult != null) {
            deferredResult.setResult(new Long(System.currentTimeMillis()).toString());
        }
    }

}
