package com.connie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhength
 * @version 1.0.0
 * @email connie1451@163.com
 * @date 2019/1/21 15:43
 * @since 1.8
 */
@RestController
public class RController {

    @RequestMapping("/getMsg")
    public R getMsg(){

        return R.ok();
    }
}
