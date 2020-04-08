package com.rj.springboot.service;

import com.rj.springboot.domain.UserDomain;

import java.util.List;

/**
 * @Author: rj
 * @Date: 2020-04-07 21:52
 * @Version: 1.0
 */
public interface UserService {
    public List<UserDomain> selectAll();

    public UserDomain getUserDomainById();

    public List<UserDomain> getUserDomainByExample(UserDomain userDomain);
}
