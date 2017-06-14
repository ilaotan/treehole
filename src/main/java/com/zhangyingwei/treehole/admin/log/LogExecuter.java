package com.zhangyingwei.treehole.admin.log;

import com.zhangyingwei.treehole.admin.log.model.LogModel;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by zhangyw on 2017/6/14.
 * 日志处理类
 */
public class LogExecuter {

    /**
     * 处理日志的方法
     * @param log
     * @return
     */
    public LogModel execute(LogModel log){
        //获取ip地址的位置信息
        if(StringUtils.isNotEmpty(log.getIp())){
            log.setIp_locatoin(TreeHoleUtils.ipLocal(log.getIp()));
        }
        return log;
    }
}
