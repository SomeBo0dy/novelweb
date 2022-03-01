package pers.xyj.controller.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//作为springMVC的异常处理器
//@ControllerAdvice
@RestControllerAdvice
public class ProjectExceptionAdvice {
    //拦截所有的异常信息
    @ExceptionHandler(Exception.class)
    public Map<String, Object> doException(Exception ex){
        Map<String,Object> result = new HashMap<>();
        result.put("code",400);
        result.put("message","服务器故障，请稍后再试！");
        //记录日志
        //通知运维
        //通知开发
        ex.printStackTrace();
        return result;
    }
}
