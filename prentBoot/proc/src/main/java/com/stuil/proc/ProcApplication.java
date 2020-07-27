package com.stuil.proc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.stuil.proc.mapper")
@Configuration
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ProcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcApplication.class, args);
    }

}
