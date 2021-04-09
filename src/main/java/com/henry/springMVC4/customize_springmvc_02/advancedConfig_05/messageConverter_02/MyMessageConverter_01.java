package com.henry.springMVC4.customize_springmvc_02.advancedConfig_05.messageConverter_02;

import com.henry.springMVC4.process_with_annotation_01.DemoObj_01;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

// 定义自己的 HttpMessageConverter类型
// 手段：继承自 AbstractHttpMessageConverter<T>泛型类型
public class MyMessageConverter_01 extends AbstractHttpMessageConverter<DemoObj_01> { // 泛型类 需要指定泛型参数

    // 构造方法
    public MyMessageConverter_01() {
        super(
                new MediaType("application",
                        "henry",
                        Charset.forName("UTF-8")
                ) // 新建一个自定义的媒体类型application/henry
        );
    }

    /* 重写父类中的抽象方法 */
    // 对请求中的数据进行处理
    @Override
    protected DemoObj_01 readInternal(Class<? extends DemoObj_01> clazz,
                                      HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        // 把请求体中的数据 以UTF-8编码的方式 转化成为字符串
        String temp = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));

        // 分割字符串，得到子字符串的数组
        String[] tempArr = temp.split("-");

        // 把字符串数组转化成为 DemoObj_01类型的对象
        return new DemoObj_01(new Long(tempArr[0]), tempArr[1]);
    }

    /*
        表示 当前的HttpMessageConverter类型 只能够处理 DemoObj这个类型
     */
    @Override
    protected boolean supports(Class<?> aClass) {
        return DemoObj_01.class.isAssignableFrom(aClass);
    }

    /*
        处理：如何输出数据到response中
     */
    @Override
    protected void writeInternal(DemoObj_01 obj01, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        String out = "hello: " + obj01.getId() + "-"
                + obj01.getName();
        // 把处理后的数据写入到 outputMessage中
        outputMessage.getBody().write(out.getBytes());

    }
}
