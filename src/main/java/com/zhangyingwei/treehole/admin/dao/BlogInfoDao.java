package com.zhangyingwei.treehole.admin.dao;

import com.zhangyingwei.treehole.install.model.BlogConf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zhangyw on 2017/5/8.
 */

@Mapper
public interface BlogInfoDao {
    @Select("select * from bloginfo")
    BlogConf select();
}
