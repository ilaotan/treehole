package com.zhangyingwei.treehole.admin.model;

import javax.validation.constraints.NotNull;

/**
 * Created by zhangyw on 2017/5/15.
 * 文章类别
 */
public class Kind {
    //编号
    private Integer id;
    //名称
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Kind{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
