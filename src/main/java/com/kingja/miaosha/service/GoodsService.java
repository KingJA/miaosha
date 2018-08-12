package com.kingja.miaosha.service;

import com.kingja.miaosha.dao.GoodsDao;
import com.kingja.miaosha.domain.Goods;
import com.kingja.miaosha.domain.MiaoshaGoods;
import com.kingja.miaosha.vo.GoodsVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/8/6 17:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVo> getGoodVos() {
        return goodsDao.getGoodVos();
    }
    public GoodsVo getGoodVoById(long goodsId) {
        return goodsDao.getGoodVoById(goodsId);
    }

    public void reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        goodsDao.reduceStock(goods.getId());
    }
}
