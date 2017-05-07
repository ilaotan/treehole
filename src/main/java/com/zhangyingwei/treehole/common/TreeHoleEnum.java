package com.zhangyingwei.treehole.common;

/**
 * Created by zhangyw on 2017/4/21.
 * 系统公用枚举参数
 */
public enum TreeHoleEnum {
    INSTALL_LOCK("install.lock"),//安装检测文件
    CONF_INSTALL_SQL("install.sql"),
    DB_MYSQL_CLASS("com.mysql.jdbc.Driver"),
    DB_SQLITE_CLASS("org.sqlite.JDBC"),
    SQL_CREATE_DB("db create"),
    SQL_CREATE_TABLE("table create"),
    LOGIN_USER_KEY("login_user");

    private String value;

    TreeHoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
