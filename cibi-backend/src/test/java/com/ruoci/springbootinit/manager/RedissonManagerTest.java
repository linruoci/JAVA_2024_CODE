package com.ruoci.springbootinit.manager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: ruoci
 **/
@SpringBootTest
class RedissonManagerTest {

    @Resource
    private RedissonManager redissonManager;

    @Test
    void doRateLimit() {
        for (int i = 0; i < 5; i++){
            redissonManager.doRateLimit("1");
            System.out.println("成功");
        }
    }
}