package com.kingja.miaosha.dao;

import com.kingja.miaosha.domain.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Description:TODO
 * Create Time:2018/8/6 17:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Mapper
public interface UserDao {
    @Select("select * from user where id = #{id}")
    public User getById(@Param("id")int id);

    @Insert("insert into user(id,name) value(#{id},#{name})")
    public int insert(User user);
}
