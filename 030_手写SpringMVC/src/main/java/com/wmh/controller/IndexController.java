package com.wmh.controller;

import com.wmh.annotation.Controller;
import com.wmh.annotation.RequestMapping;

/**
 * @author: create by wangmh
 * @projectName: Mayikt
 * @packageName: com.wmh.controller
 * @description:
 * @date: 2019/7/25
 **/
@Controller
public class IndexController {

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/")
    public String index1(){
        System.out.println("==================================");

        return "index";
    }
}
