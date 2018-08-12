package com.kingja.miaosha.redis;

/**
 * Description:TODO
 * Create Time:2018/8/6 22:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UserKey extends BasePrefix {

    public static UserKey ID=new UserKey("id:");
    public static UserKey NAME=new UserKey("name:");

    public UserKey(String prefix) {
        super(prefix);
    }
}
