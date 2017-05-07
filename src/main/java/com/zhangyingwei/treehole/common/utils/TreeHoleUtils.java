package com.zhangyingwei.treehole.common.utils;

import com.zhangyingwei.treehole.admin.model.Menu;
import com.zhangyingwei.treehole.admin.model.User;
import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.install.model.DbConf;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyw on 2017/4/21.
 */
public class TreeHoleUtils {

    /**
     * 判断是否已经安装
     * @return
     */
    public static boolean isInstalled(){
        return FileUtils.exists(TreeHoleEnum.INSTALL_LOCK.getValue());
    }
    public static void markAsInstall() throws IOException {
        FileUtils.newFile(TreeHoleEnum.INSTALL_LOCK.getValue());
    }

    /**
     * 创建数据库
     * @param dbConf
     */
    public static void makeDatabase(DbConf dbConf) throws TreeHoleException {
        try {
            List<String> sqls = readSql(TreeHoleEnum.SQL_CREATE_DB.getValue());
            if (sqls != null && sqls.size() > 0) {
                Connection connection = DbUtils.getConnection(dbConf);
                DbUtils.execute(connection, sqls.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TreeHoleException("创建数据库错误", e);
        }
    }

    /**
     * 创建数据库
     * @param dbConf
     */
    public static void makeTables(DbConf dbConf) throws TreeHoleException {
        List<String> sqls = null;
        try {
            sqls = readSql(TreeHoleEnum.SQL_CREATE_TABLE.getValue());
            if(sqls!=null && sqls.size()>0){
                Connection connection = DbUtils.getConnection(dbConf);
                for (String sql : sqls) {
                    DbUtils.execute(connection,sql);
                }
            }
        } catch (TreeHoleException e) {
            e.printStackTrace();
            throw new TreeHoleException("初始化数据表错误", e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TreeHoleException(e);
        }
    }

    /**
     * 根据注释读取sql语句
     * @param common
     * @return
     */
    private static List<String> readSql(String common) throws TreeHoleException {
        List<String> sqlList = new ArrayList<String>();
        File sqlFile = new File("src/main/resources/" + TreeHoleEnum.CONF_INSTALL_SQL.getValue());
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(sqlFile));
            String line = reader.readLine();
            while(line!=null){
                if(line.replaceAll("--","").trim().equals(common)){
                    while ((line = reader.readLine()) != null && (!line.trim().startsWith("--"))) {
                        if(StringUtils.isNotEmpty(line)){
                            sqlList.add(line);
                        }
                    }
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new TreeHoleException("未找到数据库脚本文件", e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new TreeHoleException("打开数据库脚本文件错误", e);
        }
        return sqlList;
    }

    /**
     * encode password by md5
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encodePasswordByMD5(String password) throws NoSuchAlgorithmException {
        return EncodeUtils.getMD5String(password);
    }

    /**
     * 检查是否已登录
     * @param session
     * @return
     */
    public static boolean isLogin(HttpSession session) {
        Object loginUser = session.getAttribute(TreeHoleEnum.LOGIN_USER_KEY.getValue());
        if (loginUser == null) {
            return false;
        }else {
            User user = (User) loginUser;
            return StringUtils.isNotEmpty(user.getUsername()) && StringUtils.isNotEmpty(user.getPassword());
        }
    }

    /**
     * 将已登录用户信息保存到session中
     * @param session
     * @param user
     */
    public static void markAsLogin(HttpSession session, User user) {
        session.setAttribute(TreeHoleEnum.LOGIN_USER_KEY.getValue(), user);
    }

    /**
     * 获取保存的登录用户信息
     * @param session
     */
    public static User getLoginUser(HttpSession session) {
        return (User) session.getAttribute(TreeHoleEnum.LOGIN_USER_KEY.getValue());
    }

    /**
     * 获取菜单信息
     * @return
     */
    public static Menu getMenu(){
        Menu root = new Menu("仪表盘").setRoot(true);
        root.addChild(
                new Menu("博客管理", "index1","fa-home")
                        .addChild(new Menu("基础信息管理","index/index1"))
                        .addChild(new Menu("系统设置","index/index2"))
        );
        root.addChild(
                new Menu("文章管理", "index2","fa-edit")
                        .addChild(new Menu("新建发布","index/index1"))
                        .addChild(new Menu("历史管理","index/index2"))
                        .addChild(new Menu("评论管理","index/index3"))
                        .addChild(new Menu("分类与标签管理","index/index4"))
        );
        root.addChild(
                new Menu("素材管理", "index3","fa-desktop")
                        .addChild(new Menu("素材管理","index/index1"))
        );
        return root;
    }
}
