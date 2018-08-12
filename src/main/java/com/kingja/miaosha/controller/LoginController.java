package com.kingja.miaosha.controller;

import com.kingja.miaosha.result.Result;
import com.kingja.miaosha.service.MiaoshaUserService;
import com.kingja.miaosha.vo.LoginVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Description:TODO
 * Create Time:2018/8/11 18:11
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    MiaoshaUserService userService;
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }
    @PostMapping("/do_login")
    @ResponseBody
    public Result doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        userService.login(response,loginVo);
        return Result.success();
    }
}
