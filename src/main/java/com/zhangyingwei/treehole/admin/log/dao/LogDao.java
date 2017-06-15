package com.zhangyingwei.treehole.admin.log.dao;

import com.zhangyingwei.treehole.admin.log.model.LogModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhangyw on 2017/6/15.
 */
@Mapper
public interface LogDao {
    @Insert("insert into log (ip,reqtype,ip_location,referer,url,uri,agent,action,timestamp) values (#{log.ip},#{log.reqType},#{log.ip_location},#{log.referer},#{log.url},#{log.uri},#{log.agent},#{log.action},#{log.timestamp})")
    void insert(@Param("log") LogModel log) throws Exception;
}
