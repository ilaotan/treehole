package com.zhangyingwei.treehole.blog.controller;

import com.zhangyingwei.treehole.common.Pages;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by zhangyw on 2017/4/21.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping
    public String index(){
        return Pages.INDEX;
    }

    @RequestMapping("/{id}")
    public String index(Map<String,Object> model , @PathVariable String id){
        model.put("id", id);
        return Pages.INDEX;
    }
}
