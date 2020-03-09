package com.example.demo.common;

import lombok.Data;

/** 
* @ClassName: Result 
* @Description: 通用结果集
* @author 纪淮永
* @date 2020年3月9日 上午12:37:41 
*/ 
@Data
public class Result { 
    
    private int code;
    
    private String msg;
    
    private Object Data;
}
