package com.zhangyingwei.treehole.admin.controller;

import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.admin.model.Kind;
import com.zhangyingwei.treehole.admin.service.ArticleService;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
     * 发布文章
     * 修改文章状态为发布
     * @param model
     * @return
     */
    @PutMapping("/publish/{id}")
    @ResponseBody
    public Map publish(Map<String, Object> model, @PathVariable("id") String id) throws TreeHoleException {
        this.articleService.addPublishArticle(id);
        return Ajax.success("发布成功");
    }

    /**
     * 修改文章
     * TODO这里目前是个bug，不能跟新建文章用一个页面，随后再想想吧
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String editArticle(Map<String, Object> model, @PathVariable("id") String id) throws TreeHoleException {
        Article article = this.articleService.getArticleById(id);
        model.put("article", article);
        model.put("kinds", this.articleService.getKinds());
        return Pages.ADMIN_ARTICLES_EDIT;
    }

    @PostMapping
    public String saveEditArticle(Map<String, Object> model, @Valid Article article) throws TreeHoleException {
        try {
            this.articleService.editArticle(article);
        } catch (TreeHoleException e) {
            logger.error(e.getLocalizedMessage());
            model.put("article", article);
            model.put("msg", e.getLocalizedMessage());
            model.put("kinds", this.articleService.getKinds());
            return Pages.ADMIN_ARTICLES_EDIT;
        }
        return "redirect:/admin/articles/history";
    }

    /**
     * 删除文章
     * @param model
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Map delete(Map<String, Object> model, @PathVariable("id") String id,String state) throws TreeHoleException {
        if("state".equals(state)){
            this.articleService.deleteArticle(id,true);
            return Ajax.success("删除成功");
        }else if("delete".equals(state)){
            this.articleService.deleteArticle(id, false);
            return Ajax.success("删除成功");
        }
        return Ajax.error("删除失败");
    }

    /**
     * 路由到 历史文章管理页面
     * @return
     */
    @GetMapping("/history")
    public String indexHisroty(Map<String,Object> model) throws TreeHoleException {
        List<Article> articles = this.articleService.getArticles();
        model.put("articles", articles);
        return Pages.ADMIN_ARTICLES_HISTORY;
    }
}
