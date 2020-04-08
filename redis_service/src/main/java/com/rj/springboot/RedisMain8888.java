package com.rj.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Author: rj
 * @Date: 2020-04-07 20:44
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.rj.springboot.mapper") // 设置mapper接口的扫描路径
public class RedisMain8888 {

    @Bean
    public Redisson redisson(){
        Config config = new Config();
        // 单机模式
        config.useSingleServer().setAddress("redis://192.168.1.101:6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }

    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisMain8888.class,args);
    }
}
