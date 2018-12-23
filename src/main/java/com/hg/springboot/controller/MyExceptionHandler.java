package com.hg.springboot.controller;

import com.hg.springboot.exception.UserNotExitException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    //浏览器客户端返回的都是JSON数据
//    @ResponseBody
//    @ExceptionHandler(UserNotExitException.class)
//    public Map<String,Object> handleException(Exception e){
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","user.notexit");
//        map.put("message",e.getMessage());
//        map.put("cause",e.getCause());
//        return map;
//    }

//    @ResponseBody
    @ExceptionHandler(UserNotExitException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //传入自己的错误状态码 4xx 5xx
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexit");
        map.put("message","用户出错啦！");
        request.setAttribute("ext",map);
        //转发到error
        return "forward:/error";
    }
}
