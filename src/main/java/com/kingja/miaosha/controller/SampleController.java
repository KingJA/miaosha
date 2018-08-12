package com.kingja.miaosha.controller;

import com.kingja.miaosha.domain.User;
import com.kingja.miaosha.redis.RedisService;
import com.kingja.miaosha.redis.UserKey;
import com.kingja.miaosha.result.Result;
import com.kingja.miaosha.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:TODO
 * Create Time:2018/8/6 16:10
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Controller
@RequestMapping("/miaosha")
public class SampleController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/html")
    public String testThyemleaf(Model model) {
        model.addAttribute("name","KingJA");
        return "test";
    }
    @RequestMapping("/db/get")
    @ResponseBody
    public Result dbGet() {
        User user = userService.getById(1);
        System.out.println("name:"+user.getName());
        return Result.success(user);
    }
    @RequestMapping("/db/tx")
    @ResponseBody
    public Result dbTx() {
        userService.tx();
        return Result.success(true);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result redisGet() {
        User user = redisService.get(UserKey.ID,"1", User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result redisSet() {
        User user = new User();
        user.setId(27);
        user.setName("Jordan");
        boolean result = redisService.set(UserKey.ID,"1", user);
        return Result.success(result);
    }
}
