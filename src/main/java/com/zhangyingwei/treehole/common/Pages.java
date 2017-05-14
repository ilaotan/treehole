package com.zhangyingwei.treehole.common;

import com.zhangyingwei.treehole.common.config.TreeHoleConfig;

import java.util.Map;

/**
 * Created by zhangyw on 2017/4/21.
 */
public class Pages {
    public static final String INSTALL = "install";
    public static final String ADMIN_INDEX = "admin/index";
    public static final String ADMIN_LOGIN = "admin/login";
    public static final String ADMIN_BLOG_BASIC = "admin/blog/basic-index";
    public static final String ADMIN_BLOG_STATISTIC = "admin/blog/statistic-index";
    public static final String ADMIN_BLOG_SETTINGS = "admin/blog/settings-index";
    public static final String ADMIN_ARTICLES_PUBLISH = "admin/article/publish-index";
    public static final String BLOG_THEME_INDEX = "layout/layout";
    public static final String ADMIN_ARTICLES_HISTORY = "admin/article/history-index";

    public static String blog(TreeHoleConfig config,String themePage){
        return TreeHoleEnum.THEME_BASEPATH.getValue() + "/" + config.getTheme() + "/" + themePage;
    }
}