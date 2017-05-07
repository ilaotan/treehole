package com.zhangyingwei.treehole.common.controller;

import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by zhangyw on 2017/4/24.
 */

@Controller
@ControllerAdvice
public class ExceptionController {

    private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = {BindException.class, TreeHoleException.class,NullPointerException.class})
    public Object bindExceptionHandler(Exception ex){
        String message = "";
        logger.info("hello exception: "+message);
        if(ex instanceof BindException){
            BindException bex = (BindException) ex;
            message = bex.getFieldError().getDefaultMessage();
        }else{
            message = ex.getMessage();
        }
        ex.printStackTrace();
        return Ajax.error(message);
    }
}
