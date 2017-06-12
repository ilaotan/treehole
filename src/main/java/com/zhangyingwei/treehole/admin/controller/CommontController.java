package com.zhangyingwei.treehole.admin.controller;

import com.zhangyingwei.treehole.common.Pages;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhangyw on 2017/6/12.
 * 评论管理controller
 */
@Controller
@RequestMapping("/admin/articles")
public class CommontController {
    @GetMapping("/commonts")
    public String index(){
        return Pages.ADMIN_COMMONTS;
    }
}
