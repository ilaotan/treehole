package com.zhangyingwei.treehole.admin.controller;

import com.zhangyingwei.treehole.admin.service.BlogManagerService;
import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.install.model.BlogConf;
import com.zhangyingwei.treehole.install.model.InstallConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by zhangyw on 2017/5/8.
 * 博客信息管理控制器
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogManageController {
    private Logger logger = LoggerFactory.getLogger(BlogManageController.class);

    @Autowired
    private BlogManagerService blogManagerService;

    @GetMapping("/basic")
    public String indexBasicInfoManage(Map<String,Object> model) throws TreeHoleException {
        BlogConf blogConf = this.blogManagerService.getBlogConf();
        InstallConf installConf = this.blogManagerService.getInstallinfo();
        model.put("bloginfo", blogConf);
        model.put("installinfo", installConf);
        return Pages.ADMIN_BLOG_BASIC;
    }

}
