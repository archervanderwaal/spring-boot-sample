package com.kerwin.service;

import com.alibaba.media.MediaConfiguration;
import com.alibaba.media.MediaFile;
import com.alibaba.media.Result;
import com.alibaba.media.client.MediaClient;
import com.alibaba.media.client.impl.DefaultMediaClient;
import com.kerwin.model.UploadInfo;
import com.kerwin.repository.UploadInfoRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import static com.kerwin.utils.Base642Image.randomFileName;

/**
 * Created by Kerwin on 2016/5/18.
 * UploadInfo service
 */
@Service
public class UploadService {
    @Autowired
    private UploadInfoRepository uploadInfoRepository;

    /**
     * 获取文件上传信息
     *
     * @return 上传信息
     */
    private UploadInfo getUploadInfo() {
        return getUploadInfo("kerwin");
    }

    /**
     * 获取文件上传信息
     *
     * @param namespace 命名空间
     * @return 上传信息
     */
    private UploadInfo getUploadInfo(String namespace) {
        return uploadInfoRepository.getOne(namespace);
    }

    public String fileUpload(InputStream fileStream, String fileName, Long fileLength) {
        UploadInfo uploadInfo = getUploadInfo();
        int i = fileName.lastIndexOf(".");
        if (i == -1) {
            throw new RuntimeException("Unknown file type.");
        }
        String type = fileName.substring(i);

        MediaConfiguration configuration = new MediaConfiguration();
        configuration.setType(MediaConfiguration.TYPE_TOP);
        configuration.setAk(uploadInfo.getAk().trim());
        configuration.setSk(uploadInfo.getSk().trim());
        configuration.setNamespace(uploadInfo.getNamespace().trim());

        String name = randomFileName(fileName).replaceAll("/", "");

        try {
            // 将文件存到本地
            FileUtils.copyInputStreamToFile(fileStream, Paths.get("F:/", fileName).toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 0.2 获取Client
        MediaClient client = new DefaultMediaClient(configuration);

        // 1. 简单上传接口，直接上传文件
        Result<MediaFile> result = client.upload(type.substring(1), name, fileStream, fileLength);
        if (result.isSuccess()) return result.getData().getUrl();
        return "Failure";
    }
}
