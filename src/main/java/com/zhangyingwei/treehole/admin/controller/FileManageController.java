package com.zhangyingwei.treehole.admin.controller;

import com.zhangyingwei.treehole.admin.service.FileManagerService;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2017/5/27
 * @time: 下午10:15
 * @desc:
 */
@Controller
@RequestMapping("/admin/files")
public class FileManageController {
    private Logger logger = Logger.getLogger(FileManageController.class);
    @Autowired
    private FileManagerService fileManagerService;
    @GetMapping
    public String fileManageIndex(){
        return Pages.ADMIN_FILEMANAGE;
    }

    @PostMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile file) throws TreeHoleException {
        this.fileManagerService.saveFile(file);
        return Ajax.success("success");
    }

}
