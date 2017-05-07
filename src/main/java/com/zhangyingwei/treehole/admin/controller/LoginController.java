package com.zhangyingwei.treehole.admin.controller;

import com.sun.deploy.net.HttpResponse;
import com.zhangyingwei.treehole.admin.model.User;
import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2017/4/23
 * @time: 上午10:23
 * @desc: 登录
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping
    public String index(Map<String, Object> model, HttpSession session, HttpServletRequest request) {
        if (TreeHoleUtils.isLogin(session)) {
            model.put("user", TreeHoleUtils.getLoginUser(session));
            model.put("menus", TreeHoleUtils.getMenu());
            return Pages.ADMIN_INDEX;
        } else {
            Cookie[] cookie = request.getCookies();
            return Pages.ADMIN_LOGIN;
        }
    }

    @PostMapping("/login")
    public String login(HttpSession session, @Valid User user){
        logger.info(user.toString());
        //记录登录用户信息到session
        TreeHoleUtils.markAsLogin(session,user);
        return "redirect:/admin";
    }
}
