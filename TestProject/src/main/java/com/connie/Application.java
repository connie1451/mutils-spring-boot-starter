package com.connie;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author zhength
 * @version 1.0.0
 * @email connie1451@163.com
 * @date 2018/12/29 10:02
 * @since 1.8
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//排除数据源，不连接数据库不会报错
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        System.out.println("this is my first testProject");
        SpringApplication.run(Application.class,args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
