package com.zhangyingwei.treehole.common.utils;


import com.zhangyingwei.treehole.common.config.TreeHoleConfig;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FilenameFilter;
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

    public static void formatFileType(String path,String oldSuffix,String newSuffix){
        File file = new File(path);
        String[] listFile = file.list();
        for (String name : listFile) {
            File temp = new File(path+"/"+name);
            if(temp.isFile()){
                if(temp.getName().endsWith(oldSuffix)){
                    temp.renameTo(new File(path + "/" + name.replace(oldSuffix, newSuffix)));
                }
            }else{
                formatFileType(path + "/" + name,oldSuffix,newSuffix);
            }
        }
    }

    public static void main(String[] args) {
        FileUtils.formatFileType("src/main/resources/templates/theme/default", ".ejs", ".html");
    }
}
