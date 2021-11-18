package com.jepusi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @class: com.jepusi.PostgresqlMain
 * @description:
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/11/18 9:35
 */


@SpringBootApplication
@MapperScan(value = "com.jepusi.mapper")
public class PostgresqlMain {
    public static void main(String[] args) {
        SpringApplication.run(PostgresqlMain.class,args);
    }
}
