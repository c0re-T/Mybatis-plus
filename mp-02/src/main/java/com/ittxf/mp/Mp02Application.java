package com.ittxf.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ittxf.mp.mapper")
public class Mp02Application {

    public static void main(String[] args) {
        SpringApplication.run(Mp02Application.class, args);
    }

}
