package com.rj.springboot.controller;

import com.rj.springboot.domain.CommonResult;
import com.rj.springboot.domain.StatusCode;
import com.rj.springboot.domain.UserDomain;
import com.rj.springboot.service.UserService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: rj
 * @Date: 2020-04-07 21:58
 * @Version: 1.0
 * redis 客户端手动添加一个 num = 50
 * 启动两个服务，使用nginx进行反向代理
 * 使用jmeter模拟高并发操作 num 的值，观察是否数据错乱
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private Redisson redisson;

    @RequestMapping("/redis")
    public String redisTest(){
        // 定义一个分布式锁的key,value
        String key = "rj";
//        String value = UUID.randomUUID().toString();
        RLock lock = redisson.getLock(key);
        try{
            // 分布式加锁  10秒后锁过期，如果key存在，则下一个线程会存储失败
//            Boolean result = redisTemplate.opsForValue().setIfAbsent(key, value, 30,TimeUnit.SECONDS);
//            if(!result){
//                return "当前系统繁忙，请稍后再试";
//            }

            // 每个30秒检查一次锁还在不在，锁在的话，重新设置锁的超时时间
            lock.tryLock(30,TimeUnit.SECONDS);

            int num = Integer.parseInt(redisTemplate.opsForValue().get("num"));
            if(num > 0){
                int temp = num - 1;
                redisTemplate.opsForValue().set("num",temp+"");
                System.out.println("****** 操作成功，  剩余数量为：******"+temp);
            }else{
                System.out.println("*****  操作失败，库存不足*****");
            }
        }catch(Exception e){
        }finally {
            // 释放锁,自己的线程自己释放
//            if(value.equals(redisTemplate.opsForValue().get(key))){
//                redisTemplate.delete(key);
//            }
            lock.unlock();
        }




        return "end";
    }

    @RequestMapping("/selectAll")
    public CommonResult<List<UserDomain>> selectAll(){
        List<UserDomain> userDomains = userService.selectAll();
        return new CommonResult<List<UserDomain>>(true, StatusCode.OK,"success",userDomains);
    }
}
