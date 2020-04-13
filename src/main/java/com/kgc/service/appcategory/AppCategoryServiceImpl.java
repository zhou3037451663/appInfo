package com.kgc.service.appcategory;

import com.kgc.dao.AppCategoryMapper;
import com.kgc.pojo.AppCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/9
 * @Explain:
 */
@Service("appCategoryService")
public class AppCategoryServiceImpl implements AppCategoryService {
    @Resource
    private AppCategoryMapper appCategoryMapper;
    @Override
    public List<AppCategory> getCategoryLevel1() {
        return appCategoryMapper.getCategoryLevel1();
    }

    @Override
    public List<AppCategory> getCategoryLevel2() {
        return appCategoryMapper.getCategoryLevel2();
    }

    @Override
    public List<AppCategory> getCategoryLevel3() {
        return appCategoryMapper.getCategoryLevel3();
    }

    @Override
    public List<AppCategory> getCategoryByParentId(int pid) {
        return appCategoryMapper.getCategoryByParentId(pid);
    }
}
