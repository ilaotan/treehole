package com.zhangyingwei.treehole.admin.log.controller;

import com.zhangyingwei.treehole.admin.log.service.LogService;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.annotation.TreeHoleAtcion;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangyw on 2017/6/15.
 */
@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/visits/count")
    @ResponseBody
    @TreeHoleAtcion("获取访问总人数")
    public Map<String,Object> getVisitCount() throws TreeHoleException {
        Integer count = this.logService.getVisitCount();
        return Ajax.success(count);
    }

    @GetMapping("/visits/count/days")
    @ResponseBody
    @TreeHoleAtcion("按天统计访问量")
    public Map<String,Object> countByDay() throws TreeHoleException {
        Object[] result = this.logService.countByDay();
        return Ajax.success(result);
    }

    @GetMapping("/visits/sources")
    @ResponseBody
    @TreeHoleAtcion("访问来源统计")
    public Map<String,Object> visitsSources() throws TreeHoleException {
        List result = this.logService.visitSources();
        return Ajax.success(result);
    }
}
