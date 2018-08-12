package com.kingja.miaosha.service;

import com.kingja.miaosha.dao.MiaoshaUserDao;
import com.kingja.miaosha.domain.MiaoshaUser;
import com.kingja.miaosha.exception.ResultException;
import com.kingja.miaosha.redis.MiaoShaUserKey;
import com.kingja.miaosha.redis.RedisService;
import com.kingja.miaosha.result.CodeMsg;
import com.kingja.miaosha.util.MD5Util;
import com.kingja.miaosha.util.UUIDUtil;
import com.kingja.miaosha.vo.LoginVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.kingja.miaosha.redis.MiaoShaUserKey.COOKIE_TOKEN_NAME;

/**
 * Description:TODO
 * Create Time:2018/8/11 19:16
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Service
public class MiaoshaUserService {

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    @Autowired
    RedisService redisService;

    public MiaoshaUser getUserById(long id) {
        return miaoshaUserDao.getUserById(id);
    }


    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new ResultException(CodeMsg.ERROR_SERVER);
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        MiaoshaUser user = miaoshaUserDao.getUserById(Long.valueOf(mobile));
        if (user == null) {
            throw new ResultException(CodeMsg.ERROR_MOBILE_NOEXIST);
        }
        String dbPassword = user.getPassword();
        String dbSalt = user.getSalt();

        if (!MD5Util.saltMd5(password, dbSalt).equals(dbPassword)) {
            throw new ResultException(CodeMsg.ERROR_PASSWORD);
        }
        addCookie(response, user, UUIDUtil.uuid());
        return true;
    }

    public MiaoshaUser getUserByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoShaUserKey.TOKEN, token, MiaoshaUser.class);
//TODO redis如果失效
        if (user != null) {
            addCookie(response, user, token);
        }
        return user;
    }

    private void addCookie(HttpServletResponse response, MiaoshaUser user, String token) {
        //在缓存里保存user
        redisService.set(MiaoShaUserKey.TOKEN, token, user);
        //添加cookie
        Cookie cookie = new Cookie(COOKIE_TOKEN_NAME, token);
        cookie.setMaxAge(MiaoShaUserKey.TOKEN.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
