package com.zhangyingwei.treehole.admin.log.service;

import com.zhangyingwei.treehole.admin.log.dao.LogDao;
import com.zhangyingwei.treehole.admin.log.model.LogModel;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

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

    public Integer getVisitCount() throws TreeHoleException {
        try {
            return this.logDao.getVisitCount();
        } catch (Exception e) {
            throw new TreeHoleException("获取访问总人数错误");
        }
    }

    public Object[] countByDay() throws TreeHoleException {
        Long timestamp = DateUtils.getTimeStampBefore(7);
        try {
            Map<String, Integer> countMap = new HashMap<String,Integer>(){
                {
                    put(DateUtils.getDateBeforeBy(1, "yyyy-MM-dd"), 0);
                    put(DateUtils.getDateBeforeBy(2, "yyyy-MM-dd"), 0);
                    put(DateUtils.getDateBeforeBy(3, "yyyy-MM-dd"), 0);
                    put(DateUtils.getDateBeforeBy(4, "yyyy-MM-dd"), 0);
                    put(DateUtils.getDateBeforeBy(5, "yyyy-MM-dd"), 0);
                    put(DateUtils.getDateBeforeBy(6, "yyyy-MM-dd"), 0);
                    put(DateUtils.getDateBeforeBy(7, "yyyy-MM-dd"), 0);
                }
            };
            List<LogModel> logs = this.logDao.countByDay(timestamp);
            for (LogModel log:logs) {
                String date = DateUtils.getDateBy(log.getTimestamp(),"yyyy-MM-dd");
                if(countMap.containsKey(date)){
                    countMap.put(date,countMap.get(date)+1);
                }
            }
            return new Object[]{countMap.keySet(),countMap.values()};
        } catch (Exception e) {
            throw new TreeHoleException("按天统计访问情况错误");
        }
    }

    public List visitSources() throws TreeHoleException {
        List<LogModel> referers = this.logDao.visits();
        Map<String, Integer> refererMap = new HashMap<String, Integer>();
        try {
            for (LogModel log : referers) {
                String host = this.formateReferer(log.getReferer());
                if(refererMap.containsKey(host)){
                    refererMap.put(host,refererMap.get(host) + 1);
                }else{
                    refererMap.put(host, 1);
                }
            }
        } catch (MalformedURLException e) {
            throw new TreeHoleException("地址格式化错误");
        }
        Set<Map.Entry<String, Integer>> eneity = refererMap.entrySet();
        List list = new ArrayList();
        for (Map.Entry<String, Integer> ent : eneity) {
            list.add(new HashMap<String, Object>() {
                {
                    put("name",ent.getKey());
                    put("value", ent.getValue());
                }
            });
        }
        return list;
    }

    private String formateReferer(String referer) throws MalformedURLException {
        URL url = new URL(referer);
        return url.getHost();
    }
}
