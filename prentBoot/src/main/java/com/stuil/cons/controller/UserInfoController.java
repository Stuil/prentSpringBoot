package com.stuil.cons.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stuil.cons.entity.SysUser;
import com.stuil.cons.entity.UserInfo;
import com.stuil.cons.mapper.SysUserMapper;
import com.stuil.cons.service.SysUserService;
import com.stuil.cons.service.UserInfoService;
import com.stuil.cons.utils.LayuiResp;
import com.stuil.cons.utils.ResultAjax;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author stuil
 * @since 2021-02-02
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/listInfo")
    public String listInfo(){
        return "/views/listInfo";
    }


    @RequestMapping("/userList")
    public String userList(){
        return "/views/userList";
    }


    /**
     * @description: 数据列表
     * @date: 2021/2/2
     */
    @RequestMapping("/page")
    @ResponseBody
    public LayuiResp page(Integer page, Integer limit,String keyLike){
        QueryWrapper<UserInfo> wrapper=new QueryWrapper<UserInfo>();
        if(Strings.isNotBlank(keyLike)){
           wrapper.like("id", keyLike).or().like("name", keyLike).or().like("account", keyLike);
        }
        wrapper.orderByDesc("id,create_data");
        IPage<UserInfo> userIPage = userInfoService.page(new Page<UserInfo>(page, limit), wrapper);
        return LayuiResp.createBySuccess(userIPage.getTotal(),userIPage.getRecords());
    }



    /**
     * @description: 新增页面
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "/views/add";
    }

    /**
     * @description: 新增
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResultAjax add(@RequestBody UserInfo userInfo){
        return ResultAjax.success();
    }

    /**
     * @description: 详情
     */
    @RequestMapping("/detail")
    public String detail(Model model, HttpServletRequest request){
        SysUser sysUser= (SysUser) request.getSession().getAttribute("userInfo");
        model.addAttribute("sysUser",sysUser);
        return "/views/detail";
    }
}
