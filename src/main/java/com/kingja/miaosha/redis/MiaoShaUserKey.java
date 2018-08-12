package com.kingja.miaosha.redis;

/**
 * Description:TODO
 * Create Time:2018/8/6 22:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MiaoShaUserKey extends BasePrefix {
    public static final String COOKIE_TOKEN_NAME = "token";
    public static final int COOKIE_EXPIRE_SECONDS = 60 * 60 * 24 * 7;//7天

    public static MiaoShaUserKey TOKEN = new MiaoShaUserKey("tk:");

    public MiaoShaUserKey(String prefix) {
        super(COOKIE_EXPIRE_SECONDS, prefix);
    }
}
