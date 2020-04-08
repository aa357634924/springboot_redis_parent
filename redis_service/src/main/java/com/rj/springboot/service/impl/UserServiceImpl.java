package com.rj.springboot.service.impl;

import com.rj.springboot.domain.UserDomain;
import com.rj.springboot.mapper.UserMapper;
import com.rj.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: rj
 * @Date: 2020-04-07 21:53
 * @Version: 1.0
 * redis 客户端手动添加一个 k1 = 50
 * 使用jmeter模拟高并发操作k1的值，观察是否数据错乱
 *
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public List<UserDomain> selectAll() {
        String k1 = redisTemplate.opsForValue().get("k1");
        System.out.println("****** k1 ******"+k1);
        return userMapper.selectList(null);
    }

    @Override
    public UserDomain getUserDomainById() {
        return null;
    }

    @Override
    public List<UserDomain> getUserDomainByExample(UserDomain userDomain) {
        return null;
    }
}
