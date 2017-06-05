package com.zhangyingwei.treehole.common.function;

import org.beetl.core.Context;
import org.beetl.core.Function;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: zhangyw
 * @date: 2017/6/1
 * @time: 下午10:19
 * @desc:
 */
public class MonthFromString implements Function {
    @Override
    public Object call(Object[] paras, Context context) {
        String timeStr = paras[0]+"";
        String patten = paras[1]+"";
        SimpleDateFormat format = new SimpleDateFormat(patten);
        try {
            Date date = format.parse(timeStr);
            return new SimpleDateFormat("MM").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}