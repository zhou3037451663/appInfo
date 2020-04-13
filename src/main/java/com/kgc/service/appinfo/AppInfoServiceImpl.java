package com.kgc.service.appinfo;

import com.kgc.dao.AppInfoMapper;
import com.kgc.pojo.AppInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/9
 * @Explain:
 */
@Service("appInfoService")
public class AppInfoServiceImpl implements AppInfoService {
    @Resource
    private AppInfoMapper appInfoMapper;
    @Override
    public List<AppInfo> appInfoList(String softwareName, int flatFormId, int categoryLevel1, int categoryLevel2, int categoryLevel3,int status, int currentPageNo, int pageSize) {
        currentPageNo=(currentPageNo-1)*pageSize;
        return appInfoMapper.appInfoList(softwareName,flatFormId,categoryLevel1,categoryLevel2,categoryLevel3,status,currentPageNo,pageSize);
    }

    @Override
    public int getAppInfoCount(String softwareName, int flatFormId, int categoryLevel1, int categoryLevel2, int categoryLevel3,int status) {
        return appInfoMapper.getAppInfoCount(softwareName,flatFormId,categoryLevel1,categoryLevel2,categoryLevel3,status);
    }

    @Override
    public AppInfo getAppInfoCheck(int aid, int versionId) {
        return appInfoMapper.getAppInfoCheck(aid,versionId);
    }

    @Override
    public boolean appInfoSave(Integer status, Integer id) {
        return appInfoMapper.appInfoSave(status,id)>0?true:false;
    }

    @Override
    public AppInfo getAppInfoByAPKName(int id) {
        return appInfoMapper.getAppInfoByAPKName(id);
    }

    @Override
    public boolean updateAppInfo(int modifyBy, Date modifyDate, int versionId, int id) {
        return appInfoMapper.updateAppInfo(modifyBy,modifyDate,versionId,id)>0?true:false;
    }

    @Override
    public boolean updateLogoPath(int id) {
        return appInfoMapper.updateLogoPath(id)>0?true:false;
    }

    @Override
    public boolean updateAppInfo1(AppInfo appInfo) {
        return appInfoMapper.updateAppInfo1(appInfo)>0?true:false;
    }

    @Override
    public AppInfo getAppInfoCheck1(int aid, int versionId) {
        return appInfoMapper.getAppInfoCheck1(aid,versionId);
    }

    @Override
    public boolean delAppInfo(Integer id) {
        return appInfoMapper.delAppInfo(id)>0?true:false;
    }

    @Override
    public boolean addAppMessage(AppInfo appInfo) {
        return appInfoMapper.addAppMessage(appInfo)>0?true:false;
    }

    @Override
    public AppInfo getAPKName(String APKName) {
        return appInfoMapper.getAPKName(APKName);
    }

    @Override
    public boolean appInfoSale(Date onSaleDate,Date offSaleDate, int modifyBy, Date modifyDate,int status, int id) {
        return appInfoMapper.appInfoSale(onSaleDate,offSaleDate,modifyBy,modifyDate,status,id)>0?true:false;
    }
}
