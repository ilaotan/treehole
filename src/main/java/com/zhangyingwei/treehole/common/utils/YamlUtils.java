package com.zhangyingwei.treehole.common.utils;

import org.ho.yaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Created by zhangyw on 2017/5/11.
 * yaml配置文件操作工具类
 */
public class YamlUtils {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/resources/templates/theme/default/_config.yml");
        Map<String,String> yaml = (Map<String, String>) Yaml.load(file);
        System.out.println(yaml);
    }
}