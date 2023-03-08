package com.greatwqs.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * 启动总入口
 * http://127.0.0.1:8080/
 *
 * @author wang.qingsong
 * Create on 2023/03/08
 */
@EnableAsync
@EnableSwagger2
@EnableScheduling
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
