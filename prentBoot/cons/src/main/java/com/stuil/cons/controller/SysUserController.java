package com.stuil.cons.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stuil.cons.entity.SysUser;
import com.stuil.cons.service.SysUserService;
import com.stuil.cons.utils.ResultAjax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
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

    @RequestMapping("/login")
    @ResponseBody
    public ResultAjax login(SysUser sysUser){
        SysUser sysUser1=sysUserService.getOne(new QueryWrapper<SysUser>().eq("user_login_account",sysUser.getUserLoginAccount()));
        if(sysUser1==null){
            return ResultAjax.fail(-1,"账号错误");
        }
        return ResultAjax.success(sysUser1);
    }
}
