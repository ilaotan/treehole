package com.zhangyingwei.treehole.admin.controller;

import com.zhangyingwei.treehole.admin.model.FileRes;
import com.zhangyingwei.treehole.admin.service.FileManagerService;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
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


    @DeleteMapping("/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") String id) throws TreeHoleException {
        this.fileManagerService.deleteFile(id);
        this.fileManagerService.deleteFileInfo(id);
        return Ajax.success("删除素材成功");
    }

    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> listFiles() throws TreeHoleException {
        List<FileRes> files = this.fileManagerService.findFiles();
        return Ajax.success(files);
    }
}
