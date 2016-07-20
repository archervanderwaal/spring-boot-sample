package com.kerwin.controller;

import com.alibaba.media.MediaConfiguration;
import com.alibaba.media.MediaFile;
import com.alibaba.media.Result;
import com.alibaba.media.client.MediaClient;
import com.alibaba.media.client.impl.DefaultMediaClient;
import com.kerwin.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Kerwin on 2016/5/18.
 * File upload controller
 */
@RestController
@RequestMapping(method = RequestMethod.POST)
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "file_upload_test")
    public String testUpload(String path, String name) throws FileNotFoundException {
        // 0.1 定义全局配置信息
        MediaConfiguration configuration = new MediaConfiguration();
        configuration.setType(MediaConfiguration.TYPE_TOP);
        configuration.setAk("23368060");
        configuration.setSk("218af29646ba14fc4407c249c244e712");
        configuration.setNamespace("kerwin");

        // 0.2 获取Client
        MediaClient client = new DefaultMediaClient(configuration);

        // 0.3 待上传的文件
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);

        // 1. 简单上传接口，直接上传文件
        Result<MediaFile> result = client.upload("upload", name, inputStream, file.length());

        return result.toString();
    }

    @RequestMapping(value = "avatar_upload")
    public String upload(String path) throws FileNotFoundException {
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);
        return uploadService.fileUpload(inputStream, file.getName(), file.length());
    }
}
