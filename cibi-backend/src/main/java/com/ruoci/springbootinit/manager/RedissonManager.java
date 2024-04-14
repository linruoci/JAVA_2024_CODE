package com.ruoci.springbootinit.manager;

import com.ruoci.springbootinit.common.ErrorCode;
import com.ruoci.springbootinit.exception.BusinessException;
import com.ruoci.springbootinit.exception.ThrowUtils;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 限流通用类
 * @Author: ruoci
 **/
@Service
public class RedissonManager {

    @Resource
    private RedissonClient redissonClient;

    public void doRateLimit(String key){
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        rateLimiter.trySetRate(RateType.OVERALL, 2, 1, RateIntervalUnit.SECONDS);
        boolean flag = rateLimiter.tryAcquire(1);
        System.out.println(flag);
        if (!flag){
            throw new BusinessException(ErrorCode.TOO_MANY_REQUEST, "请求频繁");
        }


    }





}
