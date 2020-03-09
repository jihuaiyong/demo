package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.Result;
import com.example.demo.service.IFileDownLoadService;

import lombok.extern.slf4j.Slf4j;

/** 
* @ClassName: FileController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 纪淮永
* @date 2020年3月9日 上午12:46:19 
*  
*/ 
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {
    
    @Autowired
    private IFileDownLoadService fileDownLoadService;
    /**
     * 文件下载请求
     * 
     * @param fileName 文件id
     */
    @GetMapping("/download")
    public Result fileDownload(String fileName, HttpServletResponse response, HttpServletRequest request) {
        Result result = new Result();
        try {
            if (StringUtils.isBlank(fileName)) {
                log.info("文件名为空");
                result.setCode(0);
                result.setMsg("文件名不能为空");
                return result;
            }
            fileDownLoadService.fileDownLoad(fileName, response, request);
            log.info("下载文件成功 : {}", fileName);
            result.setCode(1);
            result.setMsg("下载文件成功");
            return result;
        } catch (Exception e) {
            log.error("下载文件失败", e);
            result.setCode(0);
            result.setMsg("文件名不能为空");
            return result;
        }
    }
}
