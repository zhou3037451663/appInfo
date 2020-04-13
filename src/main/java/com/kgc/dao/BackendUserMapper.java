package com.kgc.dao;

import com.kgc.pojo.BackendUser;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/7
 * @Explain:
 */
public interface BackendUserMapper {
    /**
     * 用户登录查询
     * @param userCode
     * @return
     */
    public BackendUser login(@Param("userCode")String userCode);
}
