package com.henry.springMVC4.customize_springmvc_02.advancedConfig_05.fileUpload_01;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartFile file) { // 1 使用MultipartFile file 来 接收上传的文件
        try {
            // 2 使用FileUtils的 wroteByteArrayToFile 来 快速写文件到磁盘
            FileUtils.writeByteArrayToFile(
                    new File("E:/develop/tryout_springMVC/src/main/resources/uploaded_files/" + file.getOriginalFilename()),
                    file.getBytes());
            return "okay";
        } catch (IOException e) {
            e.printStackTrace();
            return "wrong";
        }
    }
}
