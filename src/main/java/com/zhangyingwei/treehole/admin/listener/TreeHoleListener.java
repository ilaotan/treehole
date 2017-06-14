package com.zhangyingwei.treehole.admin.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by zhangyw on 2017/6/14.
 */
@Component
public class TreeHoleListener implements ApplicationListener {
    private Logger logger = LoggerFactory.getLogger(TreeHoleListener.class);
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        ContextRefreshedEvent event = (ContextRefreshedEvent) applicationEvent;
        ApplicationContext app = event.getApplicationContext();
        logger.info(app.getDisplayName());
        logger.info("程序启动");
    }
}
