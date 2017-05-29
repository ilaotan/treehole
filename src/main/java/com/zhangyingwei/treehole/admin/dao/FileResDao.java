package com.zhangyingwei.treehole.admin.dao;

import com.zhangyingwei.treehole.admin.model.FileRes;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zhangyw on 2017/5/28.
 */
@Mapper
public interface FileResDao {
    @Insert("insert into files(name,path,alias,contenttype) values(#{file.name},#{file.path},#{file.alias},#{file.contentType})")
    void insert(@Param("file") FileRes fileRes) throws Exception;

    @Select("select * from files where alias=#{alias}")
    FileRes selectByAlias(@Param("alias") String alias) throws  Exception;

    @Select("select * from files where id=#{id}")
    FileRes selectById(@Param("id") String id);

    @Select("select * from files order by id desc")
    List<FileRes> selectFiles() throws Exception;

    @Delete("delete from files where id=#{id}")
    void deleteById(@Param("id") String id) throws Exception;
}
