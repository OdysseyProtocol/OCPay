package com.stormfives.ocpay.common;

import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class UploadHelper {

    static Logger logger = LoggerFactory.getLogger(UploadHelper.class);

    private static final String access_key = "jm-P8T2xAfYhsXqlpnGYiLYnqLxR9I8ZPE0rK39K";
    private static final String secret_key = "8-CveWMZH0-7HaM56KI-hNQQdfCXNn_58uWSSBu0";
    private static final String bucket = "xiaoming";  //空间名称
    public static final String http = "http://image.xiaomingdanche.com/";

    public static final String image = "uploads/image/"; //头像

    private static UploadManager uploadManager = new UploadManager();

    public static UploadManager getUploadmanger() {
        return uploadManager == null ? new UploadManager() : uploadManager;
    }

    public static String upload(MultipartFile file, String dir) {

        String url = "";

        Auth auth = Auth.create(access_key, secret_key);
        String name = file.getOriginalFilename();
        name = name.substring(name.lastIndexOf(".")).toLowerCase();
        String key = UUID.randomUUID().toString();
        try {
            UploadManager uploadManager = getUploadmanger();
            name = DigestUtils.md5Hex(key) + name; //七牛服务器上文件名
            key = dir + name;
            String uploadToken = auth.uploadToken(bucket, key);

            Response res = uploadManager.put(file.getBytes(), key, uploadToken);
            if (res.isOK()){
                url = http +key;
            }

        } catch (Exception e) {
            logger.error("上传文件失败", e);
        }
        return url;
    }

    public static String upload(File file, String bucket, String dir) {
        Auth auth = Auth.create(access_key, secret_key);
        String name = file.getName();
        name = name.substring(name.lastIndexOf(".")).toLowerCase();
        String key = UUID.randomUUID().toString();

        try {
            UploadManager uploadManager = getUploadmanger();
            name = DigestUtils.md5Hex(key) + name;
            key = dir + name;
            String uploadToken = auth.uploadToken(bucket, key);
            uploadManager.put(file, key, uploadToken);
        } catch (Exception var7) {
            logger.error("上传文件失败", var7);
            return "";
        }
        return name;
    }

    public static String getPrivateDownloadUrl(String url) {
        Auth auth = Auth.create(access_key, secret_key);
        return auth.privateDownloadUrl(url);
    }
}
