package com.zhangyingwei.treehole.admin.service;

import com.zhangyingwei.treehole.admin.dao.ArticleDao;
import com.zhangyingwei.treehole.admin.dao.KindDao;
import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.admin.model.Kind;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangyw on 2017/5/15.
 */
@Service
public class ArticleService {
    @Autowired
    private KindDao kindDao;
    @Autowired
    private ArticleDao articleDao;

    /**
     * 查询所有文章类型列表
     * @return
     * @throws TreeHoleException
     */
    public List<Kind> getKinds() throws TreeHoleException {
        try {
            return this.kindDao.selectKinds();
        } catch (Exception e) {
            throw new TreeHoleException("查询文章类别列表错误", e);
        }
    }

    /**
     * 查询所有文章
     * @return
     * @throws TreeHoleException
     */
    public List<Article> getArticles() throws TreeHoleException {
        try {
            return this.articleDao.selectArticles();
        } catch (Exception e) {
            throw new TreeHoleException("查询所有文章错误: "+e.getMessage());
        }
    }

    /**
     * 根据subpath查询文章
     * @param subpath
     * @return
     */
    public Article getArticleBySubPath(String subpath){
        return this.articleDao.selectArticleBySubpath(subpath);
    }

    /**
     * 根据 title 查询文章
     * @param title
     * @return
     */
    public Article getArticleByTitle(String title){
        return this.articleDao.selectArticleByTitle(title);
    }

    /**
     * 新增文章
     * @param article
     * @throws TreeHoleException
     */
    public void addArticle(Article article) throws TreeHoleException{
        try {
            if(StringUtils.isNotEmpty(article.getSubpath())){
                Article tmpArticle = this.articleDao.selectArticleBySubpath(article.getSubpath());
                if (tmpArticle != null && StringUtils.isNotEmpty(tmpArticle.getSubpath()) && tmpArticle.getSubpath().equals(article.getSubpath())) {
                    throw new TreeHoleException("自定义访问地址 "+article.getSubpath() + " 已存在");
                }
            }
            if(StringUtils.isNotEmpty(article.getTitle())){
                Article tmpArticle = this.articleDao.selectArticleByTitle(article.getTitle());
                if (tmpArticle != null && StringUtils.isNotEmpty(tmpArticle.getTitle()) && tmpArticle.getTitle().equals(article.getTitle())) {
                    throw new TreeHoleException("文章标题 "+article.getSubpath() + " 已存在");
                }
            }
            this.articleDao.insertArticle(article);
        } catch (Exception e) {
            throw new TreeHoleException(e.getLocalizedMessage());
        }
    }
}
