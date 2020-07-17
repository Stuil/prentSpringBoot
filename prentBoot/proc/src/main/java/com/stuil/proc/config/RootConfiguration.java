package com.stuil.proc.config;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @title: RootConfiguration
 * @description: xml 热加载
 * @author: stuil
 * @copyright: Copyright (c) 2020
 * @version: 1.0
 */

/*

@Configuration
@ComponentScan(value = "com.stuil.proc", excludeFilters = { @ComponentScan.Filter(Controller.class),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = { RootConfiguration.class }) })
@MapperScan({"com.stuil.**.mapper"})
public class RootConfiguration extends SpringBootServletInitializer {
    @Autowired
    private SqlSession sqlSession;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.registerShutdownHook(false);
        return application.sources(RootConfiguration.class);
    }

    @PostConstruct
    public void postConstruct() throws IOException {
        //Constant.threadPool = Executors.newFixedThreadPool(20);
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/*Mapper.xml");
        new MapperRefresh(resources, sqlSession.getConfiguration()).run();
    }
}
*/
