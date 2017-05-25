package com.zhangyingwei.treehole.admin.dao;

import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhangyw on 2017/5/15.
 * 文章操作 dao
 */
@Mapper
public interface ArticleDao {
    @Select("select * from article")
    List<Article> selectArticles() throws Exception;

    @Select("select * from article where flag = 0")
    List<Article> selectActiveArticles() throws Exception;

    @Insert("insert into article(title,subpath,tags,intro,article,kind,usecommont,flag) values(#{article.title},#{article.subpath},#{article.tags},#{article.intro},#{article.article},#{article.kind},#{article.usecommont},#{article.flag})")
    void insertArticle(@Param("article") Article article) throws Exception;

    @Select("select * from article where subpath=#{subpath}")
    Article selectArticleBySubpath(@Param("subpath") String subpath);

    @Select("select * from article where title=#{title}")
    Article selectArticleByTitle(String title);
}
