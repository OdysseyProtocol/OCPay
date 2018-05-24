package com.stormfives.ocpay.common.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by lyc on 17/2/27.
 */
public class DownloadUtil {

    /**
     * by tony 2013-10-17
     * @param byteArrayOutputStream 将文件内容写入ByteArrayOutputStream
     * @param response HttpServletResponse	写入response
     * @param returnName 返回的文件名
     */
    public void download(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response, String returnName) throws IOException {
        response.setContentType("application/octet-stream;charset=utf-8");
        returnName = response.encodeURL(new String(returnName.getBytes(),"iso8859-1"));			//保存的文件名,必须和页面编码一致,否则乱码
        response.addHeader("Content-Disposition",   "attachment;filename=" + returnName);
        response.setContentLength(byteArrayOutputStream.size());

        ServletOutputStream outputstream = response.getOutputStream();	//取得输出流
        byteArrayOutputStream.writeTo(outputstream);					//写到输出流
        byteArrayOutputStream.close();									//关闭
        outputstream.flush();											//刷数据
    }
}
