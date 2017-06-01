package com.zhangyingwei.treehole.blog.controller;

import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.common.config.TreeHoleConfig;
import com.zhangyingwei.treehole.common.utils.TreeHoleConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by zhangyw on 2017/4/21.
 */
@Controller
@RequestMapping("/articles")
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TreeHoleConfig treeHoleConfig;

    @RequestMapping
    public String index(Map<String,Object> model){
        Map<String, Object> siteConfig = TreeHoleConfigUtils.readThremeYmlConfig(treeHoleConfig);
        model.put("site", siteConfig);
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_INDEX);
    }
}
