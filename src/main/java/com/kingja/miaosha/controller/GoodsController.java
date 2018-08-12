package com.kingja.miaosha.controller;

import com.kingja.miaosha.domain.MiaoshaUser;
import com.kingja.miaosha.service.GoodsService;
import com.kingja.miaosha.service.MiaoshaUserService;
import com.kingja.miaosha.vo.GoodsVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/8/11 18:11
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    MiaoshaUserService userService;
    @Autowired
    GoodsService goodsService;
    private static Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @RequestMapping("/to_list")
    public String list(Model model, MiaoshaUser user) {
        model.addAttribute("user",user);
        List<GoodsVo> goodsList = goodsService.getGoodVos();
        model.addAttribute("goodsList",goodsList);
        return "goods_list";
    }
    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model, MiaoshaUser user, @PathVariable("goodsId")long goodsId) {
        model.addAttribute("user",user);
        GoodsVo goodsVo = goodsService.getGoodVoById(goodsId);
        long startTime = goodsVo.getStartDate().getTime();
        long endTime = goodsVo.getEndDate().getTime();
        long nowTime = System.currentTimeMillis();

        int miaoshaStatus=0;
        int remainSeconds=0;

        if (nowTime < startTime) {
            //还没开始
            miaoshaStatus=0;
            remainSeconds= (int) ((startTime-nowTime)/1000);
        }else if (nowTime>endTime){
            //已经结束
            miaoshaStatus=2;
            remainSeconds= -1;
        }else{
            //进行中
            miaoshaStatus=1;
            remainSeconds= 0;
        }
        model.addAttribute("goods",goodsVo);
        model.addAttribute("miaoshaStatus",miaoshaStatus);
        model.addAttribute("remainSeconds",remainSeconds);

        return "goods_detail";
    }
}
