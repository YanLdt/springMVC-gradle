package com.yanl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: YanL
 * @Time: 11:38 2020/1/6
 * @Email: imyanl.dt@gmail.com
 * @Description:
 */

@Controller
@RequestMapping("/hello")
public class HelloController {

    /**
     * 返回字符串
     * @return success
     */
    @RequestMapping("/string")
    public String sayHello(){
        System.out.println("Hello MVC");
        return "success";
    }
}
