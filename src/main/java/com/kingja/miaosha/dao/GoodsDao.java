package com.kingja.miaosha.dao;

import com.kingja.miaosha.domain.Goods;
import com.kingja.miaosha.domain.MiaoshaGoods;
import com.kingja.miaosha.vo.GoodsVo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/8/6 17:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Mapper
public interface GoodsDao {
    @Select("select g.*,mg.miaosha_price,mg.stock_count,mg.start_date,mg.end_date from miaosha_goods mg left join " +
            "goods g on mg.goods_id=g.id")
    public List<GoodsVo> getGoodVos();

    @Select("select g.*,mg.miaosha_price,mg.stock_count,mg.start_date,mg.end_date from miaosha_goods mg left join " +
            "goods g on mg.goods_id=g.id where g.id=#{goodsId}")
    public GoodsVo getGoodVoById(@PathVariable("goodsId") long goodsId);

    @Update("update miaosha_goods set stock_count=stock_count - 1 where goods_id=#{goodsId}")
    public void reduceStock(long goodsId);
}
