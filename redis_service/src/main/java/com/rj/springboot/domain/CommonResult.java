package com.rj.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: rj
 * @Date: 2020-04-07 21:11
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>{
    private boolean flag;//是否成功
    private Integer code;//返回码
    private String message;//返回消息

    private T data;//返回数据

    public CommonResult(boolean flag, Integer code, String message) {
        this(flag,code,message,null);
    }
}
