package com.ruoci.springbootinit.controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 帖子接口
 * @author linruoci
 */
@RestController
@RequestMapping("/thread")
@Slf4j
@Profile({"dev", "local"})
public class ThreadPoolTestController {

   @Resource
   private ThreadPoolExecutor threadPoolExecutor;


   @GetMapping("/add")
   public void add(String message){
       CompletableFuture.runAsync(() -> {
            log.info("任务执行中 : " + message + " 执行人: "  + Thread.currentThread().getName());
           try {
               Thread.sleep(60000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }, threadPoolExecutor);
   }


   @GetMapping("/get")
   public String getDetail(){
       Map<String, Object> map = new HashMap<>();
       map.put("队列长度:", threadPoolExecutor.getQueue().size());
       map.put("总任务数: ", threadPoolExecutor.getTaskCount());
       map.put("正在工作线程数: " , threadPoolExecutor.getActiveCount());
       map.put("已完成任务数: ", threadPoolExecutor.getCompletedTaskCount());
       return JSONUtil.toJsonStr(map);

   }




}
