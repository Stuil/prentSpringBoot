package com.stuil.cons.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stuil.cons.entity.SysUser;
import com.stuil.cons.mapper.SysUserMapper;
import com.stuil.cons.service.SysUserService;
import com.stuil.cons.utils.EncUtil;
import com.stuil.cons.utils.LayuiResp;
import com.stuil.cons.utils.ResultAjax;
import com.stuil.cons.utils.email.EmailUtil;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

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

    @Autowired
    EmailUtil emailUtil;

    @Value("${webParme.emailEnable}")
    boolean emailEnable;



    @RequestMapping("/home")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(Model model,HttpServletRequest request){
        SysUser sysUser= (SysUser) request.getSession().getAttribute("userInfo");
        model.addAttribute("sysUser",sysUser);
        return "/index";
    }
    @RequestMapping("/login")
    @ResponseBody
    public ResultAjax login(SysUser sysUser, HttpServletRequest request){
        SysUser sysUser1=sysUserService.getOne(new QueryWrapper<SysUser>().eq("user_login_account",sysUser.getUserLoginAccount()));
        if(sysUser1==null){
            return ResultAjax.fail(-1,"账号密码错误");
        }
        if(!sysUser1.getUserPwd().equals(encUtil.MD5(sysUser.getUserPwd()))){
            return ResultAjax.fail(-1,"账号密码错误");
        }
        request.getSession().setAttribute("userInfo",sysUser1);
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

    /**
     * @description: 邮箱验证码
     * @date: 2021/2/1
     */
    @RequestMapping("/cesCode")
    @ResponseBody
    public ResultAjax emailCode(String email){
        int r=new Random().nextInt(99999);
        String ram= Strings.alignRight(r,5,'0');
        // 发送
        boolean email1 = emailUtil.sendEmail("1634829010@qq.com", email, "邮箱验证", ram);
        if (email1){
            return ResultAjax.success(ram);
        }else {
            return ResultAjax.fail(1,"fail");
        }
    }


    /**
     * @description: 退出登录
     */
    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }
}
