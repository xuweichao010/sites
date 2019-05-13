package com.xwc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/30  17:47
 * 业务：
 * 功能：
 */
@Component
@SuppressWarnings("unused")
public class CacheService {

    public static final String AUT_HOME_SERCRT = "aut:secret:";
    public static final String AUT_HOME_IP = "aut:ip:";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 设置字符串缓存
     */
    public void setValue(String key, String value, Long... second) {
        stringRedisTemplate.opsForValue().set(key, value);
        if (second.length == 1) {
            stringRedisTemplate.expire(key, second[0], TimeUnit.SECONDS);
        }
    }

    /**
     * 获取字符串缓存
     */
    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }


    /**
     * 设置对象缓存
     */
    public void setObject(String key, Object value, Long... second) {
        redisTemplate.opsForValue().set(key, value);
        if (second.length == 1) {
            stringRedisTemplate.expire(key, second[0], TimeUnit.SECONDS);
        }
    }

    public boolean setIfAbsent(String key, String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 获取对象缓存
     */
    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置缓存有效期
     */
    public void expire(String key, Long seconds, TimeUnit type) {
        redisTemplate.expire(key, seconds, type);
    }


    public void delKey(String key) {
        stringRedisTemplate.delete(key);
    }
}
