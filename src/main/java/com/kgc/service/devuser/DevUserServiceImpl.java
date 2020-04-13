package com.kgc.service.devuser;

import com.kgc.dao.DevUserMapper;
import com.kgc.pojo.DevUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/8
 * @Explain:
 */
@Service("devUserService")
public class DevUserServiceImpl implements DevUserService {
    @Resource
    private DevUserMapper devUserMapper;
    @Override
    public DevUser devLogin(String devCode, String devPassword) {
        DevUser devUser=devUserMapper.devLogin(devCode);
        if (devUser!=null){
            if (devUser.getDevPassword().equals(devPassword)){
                return devUser;
            }
        }
        return null;
    }
}
