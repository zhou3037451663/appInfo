package com.kgc.dao;

import com.kgc.pojo.AppCategory;
import com.kgc.pojo.DataDictionary;

import java.util.List;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/9
 * @Explain:
 */
public interface DataDictionaryMapper {

    /**
     * app状态
     * @return
     */
    public List<DataDictionary> getStatusList();

    /**
     * 所属平台
     * @return
     */
    public List<DataDictionary> getFlatFormList();
}
