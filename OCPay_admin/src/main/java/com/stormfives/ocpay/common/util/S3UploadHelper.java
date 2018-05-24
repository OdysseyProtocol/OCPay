package com.stormfives.ocpay.common.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by zxb on 2016/12/7.
 */
@Component
public class S3UploadHelper {

    private String url = "https://image.o.bike";
    private String bucketName = "image.o.bike";
    private String dir = "image/";

    public String uploadFile(MultipartFile multipartFile) throws IOException {

        AWSCredentials credentials = new ProfileCredentialsProvider().getCredentials();

        String name = multipartFile.getOriginalFilename();
        name = name.substring(name.lastIndexOf(".")).toLowerCase();
        String key = UUID.randomUUID().toString();

        AmazonS3 s3 = new AmazonS3Client(credentials);

        key = dir + DigestUtils.md5Hex(key) + name; //文件名

        File file = convert(multipartFile);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        s3.putObject(putObjectRequest);
        file.delete();
        return url + "/" + key;
    }

    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
