package com.kgc.service.datadictionary;

import com.kgc.pojo.DataDictionary;

import java.util.List;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/9
 * @Explain:
 */
public interface DataDictionaryService {
    /**
     * 所属平台
     * @return
     */
    public List<DataDictionary> getFlatFormList();
    /**
     * app状态
     * @return
     */
    public List<DataDictionary> getStatusList();
}
