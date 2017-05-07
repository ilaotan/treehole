package com.zhangyingwei.treehole.common.config;

import com.zhangyingwei.treehole.TreeholeApplication;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangyw
 * @date: 2017/5/7
 * @time: 上午12:08
 * @desc:
 */

@Configuration
public class BeetlConf {
    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {

        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader();
        beetlGroupUtilConfiguration.setResourceLoader(classpathResourceLoader);

        //读取配置文件信息
//        beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource("classpath:beetl.properties"));
        return beetlGroupUtilConfiguration;
    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setPrefix("/templates/");
        beetlSpringViewResolver.setSuffix(".html");
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;
    }
}