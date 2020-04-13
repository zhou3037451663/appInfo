package com.kgc.dao;

import com.kgc.pojo.DevUser;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/8
 * @Explain:
 */
public interface DevUserMapper {
    public DevUser devLogin(@Param("devCode")String devCode);
}
