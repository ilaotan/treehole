package com.zhangyingwei.treehole.admin.model;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * @author: zhangyw
 * @date: 2017/5/14
 * @time: 下午9:38
 * @desc: 文章
 */
public class Article {
    private String id;
    @NotNull(message = "文章标题不能为空")
    private String title;
    private String subpath;
    private String tags;
    @NotNull(message = "文章类别不能为空")
    private String kind;
    @NotNull(message = "文章内容不能为空")
    private String article;
    private String intro;
    private String usecommont;
    private String date;
    /**
     * 0 保存
     * 1 发布
     * 9 删除
     */
    private Integer flag = 0;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubpath() {
        return subpath;
    }

    public void setSubpath(String subpath) {
        this.subpath = subpath;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getIntro() {
        this.bulidIntro();//构造 文章简介
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    private void bulidIntro(){
        if(StringUtils.isNotEmpty(this.article)){
            if(this.article.contains("<!-- more -->")){
                this.intro = this.article.split("<!-- more -->")[0].trim();
            }
        }
    }

    public String getUsecommont() {
        return usecommont;
    }

    public void setUsecommont(String usecommont) {
        this.usecommont = usecommont;
    }

    public Integer getFlag() {
        if(StringUtils.isNotEmpty(this.type)){
            return bulidFlag(type);
        }
        return this.flag;
    }

    /**
     * 根据 type 生成 flag
     * save 0
     * save_publish 1
     * 9
     * @param type
     * @return
     */
    private Integer bulidFlag(String type) {
        switch (type){
            case "save":
                return 0;
            case "save_publish":
                return 1;
            default:
                return 9;
        }
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", subpath='" + subpath + '\'' +
                ", tags='" + tags + '\'' +
                ", kind='" + kind + '\'' +
                ", article='" + article + '\'' +
                ", intro='" + intro + '\'' +
                ", usecommont='" + usecommont + '\'' +
                ", date='" + date + '\'' +
                ", flag=" + flag +
                ", type='" + type + '\'' +
                '}';
    }
}
