package com.stormfives.ocpay.advertisment.controller;

import com.stormfives.ocpay.common.BaseMessage;
import com.stormfives.ocpay.common.util.S3UploadHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by liuhuan on 2018/5/23.
 */
@RestController
@RequestMapping("api/ocpay/upload")
public class UploadFileController {


    @Autowired
    private S3UploadHelper s3UploadHelper;
    protected Logger logger = LoggerFactory.getLogger(UploadFileController.class);

    @PostMapping("/v1/file")
    public BaseMessage uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String url = null;
        try {
            url = s3UploadHelper.uploadFile(file);
        } catch (IOException e) {
            logger.error("上传文件失败",e);
            e.printStackTrace();
        }
        return new BaseMessage(url);
    }
}
