package com.kgc.service.datadictionary;

import com.kgc.dao.DataDictionaryMapper;
import com.kgc.pojo.DataDictionary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/9
 * @Explain:
 */
@Service("dataDictionaryService")
public class DataDictionaryServiceImpl implements DataDictionaryService {
    @Resource
    private DataDictionaryMapper dataDictionaryMapper;
    @Override
    public List<DataDictionary> getFlatFormList() {
        return dataDictionaryMapper.getFlatFormList();
    }

    @Override
    public List<DataDictionary> getStatusList() {
        return dataDictionaryMapper.getStatusList();
    }
}
