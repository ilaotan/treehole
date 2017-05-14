package com.zhangyingwei.treehole.admin.controller;

import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.common.Pages;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by zhangyw on 2017/5/10.
 * 文章管理controller
 */
@Controller
@RequestMapping("/admin/articles")
public class ArticleController {

    @GetMapping("/publish")
    public String indexPublish(){
        return Pages.ADMIN_ARTICLES_PUBLISH;
    }

    @PostMapping("/publish")
    public String publish(Map<String, Object> model, @Valid Article article) {
        return "redirect:/admin/articles/history";
    }

    @GetMapping("/history")
    public String hisrotyIndex(){
        return Pages.ADMIN_ARTICLES_HISTORY;
    }
}
