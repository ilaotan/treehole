package com.zhangyingwei.treehole.install.dao;

import com.zhangyingwei.treehole.install.model.AdminConf;
import com.zhangyingwei.treehole.install.model.BlogConf;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhangyw on 2017/4/26.
 */

@Mapper
public interface AdminInitDao {
    @Insert("insert into admin (username,password) values (#{admin.username},#{admin.password})")
    void insertOne(@Param("admin")AdminConf adminConf);
    @Insert("insert into bloginfo (name,url,desc) values (#{blog.name},#{blog.url},#{blog.desc})")
    void insertBlogInfo(@Param("blog") BlogConf blogConf);
}
