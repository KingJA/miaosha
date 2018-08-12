package com.kingja.miaosha.redis;

/**
 * Description:TODO
 * Create Time:2018/8/6 20:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface KeyPrefix {
    public int expireSeconds();
    public String getPrefix();
}
