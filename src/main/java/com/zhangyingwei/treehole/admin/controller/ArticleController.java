package com.zhangyingwei.treehole.admin.controller;

import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.admin.model.Kind;
import com.zhangyingwei.treehole.admin.service.ArticleService;
import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyw on 2017/5/10.
 * 文章管理controller
 */
@Controller
@RequestMapping("/admin/articles")
public class ArticleController {

    private Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    private ArticleService articleService;

    /**
     * 路由到 新建文章发布页面
     * @return
     */
    @GetMapping("/publish")
    public String indexPublish(Map<String,Object> model) throws TreeHoleException {
        List<Kind> kinds = this.articleService.getKinds();
        model.put("kinds", kinds);
        return Pages.ADMIN_ARTICLES_PUBLISH;
    }

    /**
     * 发布文章
     * @param model
     * @param article
     * @return
     */
    @PostMapping("/publish")
    public String publish(Map<String, Object> model, @Valid Article article) throws TreeHoleException {
        try {
            this.articleService.addArticle(article);
        } catch (TreeHoleException e) {
            logger.error(e.getLocalizedMessage());
            model.put("article", article);
            model.put("msg", e.getLocalizedMessage());
            model.put("kinds", this.articleService.getKinds());
            return Pages.ADMIN_ARTICLES_PUBLISH;
        }
        return "redirect:/admin/articles/history";
    }

    /**
     * 路由到 历史文章管理页面
     * @return
     */
    @GetMapping("/history")
    public String indexHisroty(){
        return Pages.ADMIN_ARTICLES_HISTORY;
    }
}
