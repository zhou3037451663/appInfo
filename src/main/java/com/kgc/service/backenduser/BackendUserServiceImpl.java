package com.kgc.service.backenduser;

import com.kgc.dao.BackendUserMapper;
import com.kgc.pojo.BackendUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/7
 * @Explain:
 */
@Service("backendUserService")
public class BackendUserServiceImpl implements BackendUserService {
    @Resource
    private BackendUserMapper backendUserMapper;
    @Override
    public BackendUser login(String userCode, String password) {
        BackendUser backendUser=backendUserMapper.login(userCode);
        if (backendUser!=null){
            if (backendUser.getUserPassword().equals(password)){
                return backendUser;
            }
        }
        return null;
    }
}
