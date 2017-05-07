package com.zhangyingwei.treehole.common.utils;


import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhangyw on 2017/4/21.
 */
public class FileUtils {

    /**
     * 判断文件是否存在
     * @param path
     * @return
     */
    public static boolean exists(String path){
        if(StringUtils.isEmpty(path)){
            return false;
        }
        return new File(path).exists();
    }

    public static void newFile(String path) throws IOException {
        new File(path).createNewFile();
    }
}
