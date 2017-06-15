package com.zhangyingwei.treehole.admin.log.service;

import com.zhangyingwei.treehole.admin.log.dao.LogDao;
import com.zhangyingwei.treehole.admin.log.model.LogModel;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangyw on 2017/6/15.
 */
@Service
public class LogService {

    private Logger logger = LoggerFactory.getLogger(LogService.class);

    @Autowired
    private LogDao logDao;

    public void addLog(LogModel log) throws TreeHoleException {
        try {
            this.logDao.insert(log);
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new TreeHoleException("写入日志错误",e);
        }
    }
}
