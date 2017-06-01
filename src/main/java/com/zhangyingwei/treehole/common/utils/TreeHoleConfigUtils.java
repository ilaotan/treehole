package com.zhangyingwei.treehole.common.utils;

import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.common.config.TreeHoleConfig;

import java.util.Map;

/**
 * Created by zhangyw on 2017/5/31.
 */
public class TreeHoleConfigUtils {

    /**
     * 读取主题配置yml文件
     * @param treeHoleConfig
     * @return
     */
    public static Map<String,Object> readThremeYmlConfig(TreeHoleConfig treeHoleConfig){
        String path = TreeHoleEnum.THEME_BASEPATH + "/" + treeHoleConfig.getTheme() + "/" + TreeHoleEnum.THEME_CONFIG.getValue();
        return null;
    }
}
