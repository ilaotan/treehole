package com.zhangyingwei.treehole.admin.service;

import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: zhangyw
 * @date: 2017/5/27
 * @time: 下午11:05
 * @desc:
 */
@Service
public class FileManagerService {
    private Logger logger = LoggerFactory.getLogger(FileManagerService.class);
    public void saveFile(MultipartFile file) throws TreeHoleException {
        try {
            TreeHoleUtils.saveUploadFile(file.getOriginalFilename(),file.getBytes());
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new TreeHoleException("保存文件错误");
        }
    }
}
