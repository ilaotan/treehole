package com.zhangyingwei.treehole.common.utils;

import java.util.Date;

/**
 * Created by zhangyw on 2017/6/15.
 */
public class DateUtils {

    /**
     * 获取时间戳
     * @return
     */
    public static Long getTimeStamp(){
        return new Date().getTime();
    }
}
