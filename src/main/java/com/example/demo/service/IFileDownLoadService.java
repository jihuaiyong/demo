package com.example.demo.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
* @ClassName: IFileDownLoadService 
* @author 纪淮永
*  
*/ 
public interface IFileDownLoadService {

    public void fileDownLoad(String fileName, HttpServletResponse response, HttpServletRequest request)throws IOException;
}
