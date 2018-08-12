package com.kingja.miaosha.redis;

/**
 * Description:TODO
 * Create Time:2018/8/6 20:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BasePrefix implements KeyPrefix {
    private int expireSeconds;
    private String prefix;

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    public BasePrefix(String prefix) {
        //0代表永不过期
        this(0,prefix);
    }

    @Override
    public int expireSeconds() {
        //0代表永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {

        return   getClass().getSimpleName()+"|"+ prefix;
    }
}
