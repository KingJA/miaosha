package com.kingja.miaosha.service;

import com.kingja.miaosha.dao.OrderDao;
import com.kingja.miaosha.domain.MiaoshaUser;
import com.kingja.miaosha.domain.OrderInfo;
import com.kingja.miaosha.vo.GoodsVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:TODO
 * Create Time:2018/8/6 17:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    //开始秒杀，1.减库存，2下订单，3.写入秒杀订单，用事务
    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //1.减库存
        goodsService.reduceStock(goods);
        //2.写入订单和秒杀订单
        OrderInfo orderInfo = orderService.createOrder(user, goods);
        return orderInfo;
    }
}
