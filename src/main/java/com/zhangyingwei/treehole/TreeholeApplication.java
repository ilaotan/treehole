package com.zhangyingwei.treehole;

import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ThymeleafAutoConfiguration.class})
@ComponentScan("com.zhangyingwei.treehole")
@Configuration
@MapperScan(basePackages = {"com.zhangyingwei.treehole.install.dao"})
public class TreeholeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreeholeApplication.class, args);
	}
}
