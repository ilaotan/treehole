package com.zhangyingwei.treehole.common.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhangyw on 2017/5/9.
 */
public class TreeHoleUtilsTest {
    @Test
    public void ipLocal() throws Exception {
        System.out.println(TreeHoleUtils.ipLocal("0.0.0.0"));
    }

    @Test
    public void systemInfo() throws Exception {
        System.out.println(TreeHoleUtils.systemInfo());
    }
}