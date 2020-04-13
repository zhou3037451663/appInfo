package com.kgc.service.backenduser;

import com.kgc.pojo.AppCategory;
import com.kgc.pojo.BackendUser;

import java.util.List;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/7
 * @Explain:
 */
public interface BackendUserService {
    /**
     * 用户登录查询
     * @param userCode
     * @return
     */
    public BackendUser login(String userCode,String password);

}
