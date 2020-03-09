package com.example.demo.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.service.IFileDownLoadService;

import lombok.extern.slf4j.Slf4j;

/** 
* @ClassName: FileDownLoadServiceImpl 
* @Description: 文件下载接口实现
* @author 纪淮永
*  
*/ 
@Slf4j
@Service
public class FileDownLoadServiceImpl implements IFileDownLoadService {

    @Value("${demo.filePath}")
    private String path;
    
    @Override
    public void fileDownLoad(String fileName, HttpServletResponse response, HttpServletRequest request) throws IOException {
        log.info("文件{}下载开始",fileName);
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE")) {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        } else if (agent.contains("Chrome")) {
            // google浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        } else {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + filename);
        String  filePath = path + fileName;
        FileInputStream fis = null;
        OutputStream os = response.getOutputStream();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (IOException e) {
            log.error("文件下载失败IOException:{}",e.getMessage());
            throw e;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    log.error("流关闭异常IOException:{}",e1.getMessage());
                }
            }
        }
        
    }

}
