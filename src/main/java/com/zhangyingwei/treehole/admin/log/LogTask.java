package com.zhangyingwei.treehole.admin.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangyw on 2017/6/14.
 * 处理日志的线程
 */
public class LogTask implements Runnable {

    private Logger logger = LoggerFactory.getLogger(LogTask.class);

    private LogQueue queue;

    public LogTask(LogQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            try {
                this.queue.take();
            } catch (InterruptedException e) {
                logger.info(e.getLocalizedMessage());
            }
        }
    }
}
