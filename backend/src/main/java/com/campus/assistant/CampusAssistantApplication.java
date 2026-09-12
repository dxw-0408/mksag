package com.campus.assistant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** 校园服务智能助手（校园通）后端入口。 */
@SpringBootApplication
@MapperScan("com.campus.assistant.mapper")
public class CampusAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusAssistantApplication.class, args);
    }
}
