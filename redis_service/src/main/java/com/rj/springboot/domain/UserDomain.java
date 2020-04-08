package com.rj.springboot.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: rj
 * @Date: 2020-04-07 20:49
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_info")
public class UserDomain implements Serializable {
    @TableId("id")
    private String id;
    private String username;
    private String password;
    private String realName;

}
