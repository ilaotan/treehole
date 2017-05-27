package com.zhangyingwei.treehole.common.utils;

import com.zhangyingwei.treehole.admin.model.Menu;
import com.zhangyingwei.treehole.admin.model.User;
import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.install.model.DbConf;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

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
                        .addChild(new Menu("基础信息","/admin/blog/basic"))
                        .addChild(new Menu("统计信息","/admin/blog/statistic"))
                        .addChild(new Menu("系统设置","/admin/blog/settings"))
        );
        root.addChild(
                new Menu("文章管理", "index2","fa-edit")
                        .addChild(new Menu("新建发布","/admin/articles/publish"))
                        .addChild(new Menu("历史管理","/admin/articles/history"))
                        .addChild(new Menu("评论管理","/admin/articles/commonts"))
                        .addChild(new Menu("分类管理","/admin/articles/tags"))
        );
        root.addChild(
                new Menu("素材管理", "index3","fa-desktop")
                        .addChild(new Menu("素材管理","index/index1"))
        );
        return root;
    }

    /**
     * 登出
     * 销毁 session中的user信息与menu信息
     * @param session
     */
    public static void logout(HttpSession session) {
        session.removeAttribute(TreeHoleEnum.LOGIN_MENU_KEY.getValue());
        session.removeAttribute(TreeHoleEnum.LOGIN_USER_KEY.getValue());
    }

    /**
     * 模拟登陆
     * 用户测试
     * @param session
     */
    public static void login(HttpSession session) {
        User user = new User();
        user.setId(1);
        user.setUsername("张英伟");
        user.setPassword("123456");
        Menu menu = TreeHoleUtils.getMenu();
        session.setAttribute(TreeHoleEnum.LOGIN_USER_KEY.getValue(), user);
        session.setAttribute(TreeHoleEnum.LOGIN_MENU_KEY.getValue(), menu);
    }

    /**
     * 把 \n 之类的 转换成 <br/> 之类的
     * @param desc
     * @return
     */
    public static String trans2Html(String desc) {
        if (StringUtils.isNotEmpty(desc)){
            desc = desc.replaceAll("\\n", "<br/>");
        }
        return desc;
    }

    /**
     * 获取系统参数
     * @return
     */
    public static Map<String,String> systemInfo(){
        Map<String, String> map = new HashMap<String,String>();
        map.putAll(osInfo());
        return map;
    }

    private static Map<String,String> osInfo(){
        Properties prop = System.getProperties();
        Map<String, String> map = new HashMap<String,String>();
        map.put("iosname", prop.getProperty("os.name"));
        map.put("iosdesktop", prop.getProperty("sun.desktop"));
        map.put("ioscup", prop.getProperty("sun.cpu.isalist"));
        map.put("ijdkversion", prop.getProperty("java.version"));
        return map;
    }

    /**
     * 获取ip地址对应的物理地址
     * @param ip
     * @return
     */
    public static String ipLocal(String ip){
        if(!isIpv4(ip)){
            return "我好想不认识你的IPv6";
        }
        IPUtils.load("src/main/resources/17monipdb.dat");
        String[] res = IPUtils.find(ip);
        String result = "";
        for (String re : res) {
            if(StringUtils.isNotEmpty(re)){
                result += re;
                result += ",";
            }
        }
        return result.substring(0, result.length() - 1);
    }

    /**
     * 判断ip是不是ipv4
     * 因为ipip库貌似不识别ipv6
     * @param ip
     * @return
     */
    private static boolean isIpv4(String ip) {
        return ip.contains(".");
    }

    /**
     * 获取当前日期
     * @return
     */
    public static String getCurrentDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    /**
     * 获取当前日期+时间
     * @return
     */
    public static String getCurrentDateTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime(){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * 获取统一数据状态数据字典
     */
    public static Map<String,String> getGolbleStateDic(){
        return new HashMap<String, String>(){
            {
                put("0", "正常");
                put("9", "已删除");
            }
        };
    }

    /**
     * 获取浏览器类型
     * @param request
     * @return
     */
    public static String getBorwe(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }
}
