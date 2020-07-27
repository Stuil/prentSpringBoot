package com.stuil.cons;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.stuil.cons.mapper")
public class ConsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsApplication.class, args);
    }

}
