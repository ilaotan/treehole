package com.zhangyingwei.treehole.blog.controller;

import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.config.TreeHoleConfig;
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
    public String index(){
        logger.info(Pages.blog(treeHoleConfig, Pages.BLOG_THEME_INDEX));
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_INDEX);
    }
}
