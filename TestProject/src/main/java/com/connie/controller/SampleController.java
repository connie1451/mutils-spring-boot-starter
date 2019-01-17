package com.connie.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhength
 * @version 1.0.0
 * @email connie1451@163.com
 * @date 2018/12/29 10:12
 * @since 1.8
 */
@Controller
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    public  String home(){
        return "Hello world! Hello connie!--------------Hello SpringBoot";
    }


    public static void main(String[] args) {
        SpringApplication.run(SampleController.class, args);
    }

}
