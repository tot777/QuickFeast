package com.xx.reggie.common;

import org.apache.logging.log4j.message.Message;

//自定义业务异常类
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
