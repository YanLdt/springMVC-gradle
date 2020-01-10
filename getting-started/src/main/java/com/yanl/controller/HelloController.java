package com.yanl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: YanL
 * @Time: 15:43 2020/1/4
 * @Email: imyanl.dt@gmail.com
 * @Description:
 */
@Controller
public class HelloController {
    /**
     * 接收请求
     * @return
     */
    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println("去找刘炎");
        return "success";
    }
}
