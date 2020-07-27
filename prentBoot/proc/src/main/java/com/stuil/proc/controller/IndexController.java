package com.stuil.proc.controller;

import com.stuil.proc.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @title: IndexController
 * @description: 纯测试提交包
 * @author: stuil
 * @copyright: Copyright (c) 2020
 * @version: 1.0
 */

@Controller
public class IndexController {
    @Autowired
    ISysUserService sysUserService;
    
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
