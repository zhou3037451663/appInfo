package com.kgc.service.devuser;

import com.kgc.pojo.DevUser;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/8
 * @Explain:
 */
public interface DevUserService {
    public DevUser devLogin(String devCode,String devPassword);
}
