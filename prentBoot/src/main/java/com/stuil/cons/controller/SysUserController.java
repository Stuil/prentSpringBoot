package com.stuil.cons.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.EncryptUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stuil.cons.entity.SysUser;
import com.stuil.cons.mapper.SysUserMapper;
import com.stuil.cons.service.SysUserService;
import com.stuil.cons.utils.EncUtil;
import com.stuil.cons.utils.LayuiResp;
import com.stuil.cons.utils.ResultAjax;
import org.nutz.ioc.loader.annotation.Inject;
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
    @Autowired
    SysUserMapper mapper;

    @Autowired
    EncUtil encUtil;

    @RequestMapping("/")
    public String homeLogin(){
        return "login";
    }

    @RequestMapping("/p1")
    public String p1(){
        return "/views/p1";
    }

    @RequestMapping("/p2")
    public String p2(){
        return "/views/p2";
    }

    @RequestMapping("/home")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "/index";
    }
    @RequestMapping("/login")
    @ResponseBody
    public ResultAjax login(SysUser sysUser){
        SysUser sysUser1=sysUserService.getOne(new QueryWrapper<SysUser>().eq("user_login_account",sysUser.getUserLoginAccount()));
        if(sysUser1==null){
            return ResultAjax.fail(-1,"账号密码错误");
        }
        if(!sysUser1.getUserPwd().equals(encUtil.MD5(sysUser.getUserPwd()))){
            return ResultAjax.fail(-1,"账号密码错误");
        }
        return ResultAjax.success();
    }


    @RequestMapping("/register")
    @ResponseBody
    public ResultAjax register(SysUser sysUser){
        int count=mapper.selectCount(new QueryWrapper<SysUser>().eq("user_login_account",sysUser.getUserLoginAccount()));
        if(count>0){
            return ResultAjax.fail(-1,"账号已存在");
        }
        sysUser.setUserPwd(encUtil.MD5(sysUser.getUserPwd()));
        mapper.insert(sysUser);
        return ResultAjax.success();
    }

    @RequestMapping("/page")
    @ResponseBody
    public LayuiResp page(Integer page,Integer limit){
        IPage<SysUser> userIPage = new Page<>();
        userIPage = mapper.selectPage(new Page<>(page, limit), null);
        return LayuiResp.createBySuccess(userIPage.getTotal(),userIPage.getRecords());
    }
}