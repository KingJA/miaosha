package com.kingja.miaosha.dao;

import com.kingja.miaosha.domain.MiaoshaUser;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Description:TODO
 * Create Time:2018/8/11 19:13
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Mapper
public interface MiaoshaUserDao {

    @Select("select * from miaosha_user where id=#{id}")
    public MiaoshaUser getUserById(@Param("id") long id);
}
