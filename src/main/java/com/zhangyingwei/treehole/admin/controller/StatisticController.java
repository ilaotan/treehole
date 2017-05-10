package com.zhangyingwei.treehole.admin.controller;

import com.zhangyingwei.treehole.common.Pages;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhangyw on 2017/5/10.
 * 统计信息
 */
@Controller
@RequestMapping("admin/blog")
public class StatisticController {
    @GetMapping("statistic")
    public String index(){
        return Pages.ADMIN_BLOG_STATISTIC;
    }
}
