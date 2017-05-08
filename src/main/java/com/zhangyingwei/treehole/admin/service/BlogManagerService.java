package com.zhangyingwei.treehole.admin.service;

import com.zhangyingwei.treehole.admin.dao.BlogInfoDao;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import com.zhangyingwei.treehole.install.model.BlogConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangyw on 2017/5/8.
 */
@Service
public class BlogManagerService {
    private Logger logger = LoggerFactory.getLogger(BlogManagerService.class);

    @Autowired
    private BlogInfoDao blogInfoDao;

    public BlogConf getBlogConf() throws TreeHoleException{
        BlogConf blogConf = null;
        try {
            blogConf = this.blogInfoDao.select();
            blogConf.setDesc(TreeHoleUtils.trans2Html(blogConf.getDesc()));
        }catch (Exception e){
            throw new TreeHoleException("查询博客基础信息错误", e);
        }
        return blogConf;
    }
}
