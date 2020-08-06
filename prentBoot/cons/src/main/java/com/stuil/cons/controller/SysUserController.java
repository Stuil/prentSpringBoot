package com.stuil.cons.controller;


import com.alibaba.fastjson.JSON;
import com.stuil.cons.entity.SysUser;
import com.stuil.cons.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author stuil
 * @since 2020-07-27
 */
@Controller
public class SysUserController {
    @Autowired
    SysUserService sysUserService;

    @RequestMapping("/index")
    public String index(){
        List<SysUser> sysUser=sysUserService.list();
        System.out.println(JSON.toJSONString(sysUser));
        return "login2";
    }
    @RequestMapping("/index2")
    public String index2(){
        List<SysUser> sysUser=sysUserService.list();
        System.out.println(JSON.toJSONString(sysUser));
        return "login3";
    }
}
