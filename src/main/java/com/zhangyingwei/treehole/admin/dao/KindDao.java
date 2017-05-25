package com.zhangyingwei.treehole.admin.dao;

import com.zhangyingwei.treehole.admin.model.Kind;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhangyw on 2017/5/15.
 */
@Mapper
public interface KindDao {
    @Select("select * from kind")
    List<Kind> selectKinds();
    @Select("select * from kind where flag <> 9")
    List<Kind> selectActiveKinds();
}
