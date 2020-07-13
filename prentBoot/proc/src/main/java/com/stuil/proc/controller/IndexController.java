package com.stuil.proc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @title: IndexController
 * @description: 纯测试提交包
 * @date: 2020/7/3
 * @author: zwh
 * @copyright: Copyright (c) 2020
 * @version: 1.0
 */

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
