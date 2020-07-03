package com.stuil.proc;

import com.stuil.proc.config.RootConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProcApplication {

    public static void main(String[] args) {
        SpringApplication.run(RootConfiguration.class, args);
    }

}
