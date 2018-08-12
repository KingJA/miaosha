package com.kingja.miaosha.controller;

import com.kingja.miaosha.domain.MiaoshaOrder;
import com.kingja.miaosha.domain.MiaoshaUser;
import com.kingja.miaosha.domain.OrderInfo;
import com.kingja.miaosha.result.CodeMsg;
import com.kingja.miaosha.service.GoodsService;
import com.kingja.miaosha.service.MiaoshaService;
import com.kingja.miaosha.service.MiaoshaUserService;
import com.kingja.miaosha.service.OrderService;
import com.kingja.miaosha.vo.GoodsVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:TODO
 * Create Time:2018/8/11 18:11
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoShaController {

    @Autowired
    MiaoshaUserService userService;
    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;
    @Autowired
    MiaoshaService miaoshaService;
    private static Logger logger = LoggerFactory.getLogger(MiaoShaController.class);

    @RequestMapping("/do_miaosha")
    public String list(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId) {
        if (user == null) {
            return "login";
        }
        GoodsVo goods = goodsService.getGoodVoById(goodsId);
        //判断有没库存
        Integer stockCount = goods.getStockCount();
        if (stockCount <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAOSHA_OVER.getMsg());
            return "miaosha_fail";
        }
        // 判断有没秒杀过了
        MiaoshaOrder miaoshaOrder = orderService.getMiaoShaOrderByUserIdAndGoodsId(user.getId(),
                goodsId);
        if (miaoshaOrder != null) {
            logger.error("重复秒杀:"+miaoshaOrder.getOrderId());
            model.addAttribute("errmsg", CodeMsg.NO_REPEAT_MIAOSHA.getMsg());
            return "miaosha_fail";
        }
        //开始秒杀，1.减库存，2下订单,写入秒杀订单，用事务

        OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";
    }
}
