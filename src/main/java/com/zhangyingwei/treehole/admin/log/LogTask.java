package com.zhangyingwei.treehole.admin.log;

import com.zhangyingwei.treehole.admin.log.executer.DefaultLogExecuter;
import com.zhangyingwei.treehole.admin.log.executer.LogExecuter;
import com.zhangyingwei.treehole.admin.log.model.LogModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangyw on 2017/6/14.
 * 处理日志的线程
 */
public class LogTask implements Runnable {

    private Logger logger = LoggerFactory.getLogger(LogTask.class);

    private LogQueue queue;

    private LogExecuter defaultLogExecuter;

    public LogTask(LogQueue queue) {
        this.queue = queue;
        this.defaultLogExecuter = new DefaultLogExecuter();
    }

    @Override
    public void run() {
        while(true){
            try {
                LogModel log = this.queue.take();
                this.defaultLogExecuter.execute(log);
                logger.info(log.toString());
            } catch (InterruptedException e) {
                logger.info(e.getLocalizedMessage());
            }
        }
    }
}
