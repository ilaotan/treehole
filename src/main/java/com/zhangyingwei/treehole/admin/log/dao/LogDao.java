package com.zhangyingwei.treehole.admin.log.dao;

import com.zhangyingwei.treehole.admin.log.model.LogModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhangyw on 2017/6/15.
 */
@Mapper
public interface LogDao {
    @Insert("insert into log (ip,reqtype,ip_location,referer,url,uri,agent,action,timestamp) values (#{log.ip},#{log.reqType},#{log.ip_location},#{log.referer},#{log.url},#{log.uri},#{log.agent},#{log.action},#{log.timestamp})")
    void insert(@Param("log") LogModel log) throws Exception;
    @Select("select count(*) from (select * from log group by ip)")
    Integer getVisitCount() throws Exception;
    @Select("select * from log where timestamp>#{timestamp}")
    List<LogModel> countByDay(Long timestamp) throws Exception;
    @Select("select referer from log where referer is not null")
    List<LogModel> visits();
}
