package com.zhangyingwei.treehole.common.controller;

import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * Created by zhangyw on 2017/5/8.
 */
@Aspect
@Configuration
public class InterCeptorController2 {
    private Logger logger = LoggerFactory.getLogger(InterCeptorController2.class);

    @Pointcut("execution(public * com.zhangyingwei.treehole..*.controller.*.*(..))")
    public void controllerMethodPointcut(){}

    @Before("execution(public * com.zhangyingwei.treehole..*.controller.*Controller.*(..))")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        logger.info("method:" + joinPoint.getThis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        HttpSession session = request.getSession();
        if(!TreeHoleUtils.isLogin(session)){
            TreeHoleUtils.login(session);
//            logger.info("没有登录");
//            response.sendRedirect("/admin");
        }
        if (session.getAttribute(TreeHoleEnum.STATE_DIC_KEY.getValue()) == null) {
            session.setAttribute(TreeHoleEnum.STATE_DIC_KEY.getValue(),TreeHoleUtils.getGolbleStateDic());
        }
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
    }

    @After("controllerMethodPointcut()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        // 处理完请求，返回内容
//        logger.info("method:" + joinPoint.getThis());
    }
}
