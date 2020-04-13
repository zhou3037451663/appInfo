package com.kgc.dao;

import com.kgc.pojo.AppCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/9
 * @Explain:
 */
public interface AppCategoryMapper {
    /**
     * 查询一级分类
     * @return
     */
    public List<AppCategory> getCategoryLevel1();
    /**
     * 查询二级分类
     * @return
     */
    public List<AppCategory> getCategoryLevel2();
    /**
     * 查询三级分类
     * @return
     */
    public List<AppCategory> getCategoryLevel3();

    /**
     * 异步刷新查询
     * @param pid
     * @return
     */
    public List<AppCategory> getCategoryByParentId(@Param("pid")Integer pid);

}
