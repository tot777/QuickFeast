package com.xx.quickfeast;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.xx.quickfeast.mapper")
@EnableTransactionManagement
public class QFApplication {

    public static void main(String[] args) {
        SpringApplication.run(QFApplication.class, args);
        log.info("hello world");
    }

}
