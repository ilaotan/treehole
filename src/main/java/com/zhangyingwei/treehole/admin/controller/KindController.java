package com.zhangyingwei.treehole.admin.controller;

import com.zhangyingwei.treehole.admin.model.Kind;
import com.zhangyingwei.treehole.admin.service.KindService;
import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2017/5/25
 * @time: 下午9:31
 * @desc:
 */
@Controller
@RequestMapping("/admin/articles/tags")
public class KindController {
    @Autowired
    private KindService kindService;
    @RequestMapping
    public String indexKind(Map<String,Object> model) throws TreeHoleException {
        List<Kind> kinds = this.kindService.getKinds();
        model.put("kinds", kinds);
        return Pages.ADMIN_KIND;
    }
}
