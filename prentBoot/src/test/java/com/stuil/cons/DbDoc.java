package com.stuil.cons;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import org.junit.jupiter.api.Test;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @title: DbDoc
 * @description:
 * @date: 2020/12/24
 * @author: stuil
 * @copyright: Copyright (c) 2020
 * @version: 1.0
 */
@SpringBootTest
public class DbDoc {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        DataSource dataSourceMysql = applicationContext.getBean(DataSource.class);
        // 生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径，自己mac本地的地址，这里需要自己更换下路径
                .fileOutputDir("D:/")
                // 打开目录
                .openOutputDir(false)
                // 文件类型
                .fileType(EngineFileType.WORD)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker).build();
        // 生成文档配置（包含以下自定义版本号、描述等配置连接）
        Configuration config = Configuration.builder()
                .version("1.0.0")
                .description("权限中心数据库表及表字段文档")
                .dataSource(dataSourceMysql)
                .engineConfig(engineConfig)
                .produceConfig(getProcessConfig())
                .build();
        // 执行生成
        new DocumentationExecute(config).execute();
    }

    /**
     * 配置想要生成的表+ 配置想要忽略的表
     *
     * @return 生成表配置
     */
    public static ProcessConfig getProcessConfig() {
        // 忽略表名
        List<String> ignoreTableName = Arrays.asList("aaaa", "aaaaaa");
        // 忽略表前缀，如忽略a开头的数据库表
        List<String> ignorePrefix = Arrays.asList("aaaaa", "aaaaa");
        // 忽略表后缀
        List<String> ignoreSuffix = new ArrayList<>();
        int a=20;
        for (int i = 0; i <= a; i++) {
            ignoreSuffix.add("_20"+ Strings.alignRight(i,2,'0'));
        }
        int b=12;
        for (int i = 0; i <= b; i++) {
            ignoreSuffix.add("_2019"+ Strings.alignRight(i,2,'0'));
            ignoreSuffix.add("_2020"+ Strings.alignRight(i,2,'0'));
        }
        ignoreSuffix.add("_0109");
        return ProcessConfig.builder()
                //.designatedTableName(Arrays.asList("gas_book","gas_book_no"))
                //根据表前缀生成
                .designatedTablePrefix(new ArrayList<String>())
                //根据表后缀生成
                .designatedTableSuffix(new ArrayList<String>())
                //忽略表名
                .ignoreTableName(ignoreTableName)
                //忽略表前缀
                .ignoreTablePrefix(ignorePrefix)
                //忽略表后缀
                .ignoreTableSuffix(ignoreSuffix).build();
    }
}
