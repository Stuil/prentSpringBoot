package com.stuil.proc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.stuil.proc.mapper")
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ProcApplication {

    public static void main(String[] args) {
        System.out.println("测试提交");
        SpringApplication.run(ProcApplication.class, args);
    }

}
