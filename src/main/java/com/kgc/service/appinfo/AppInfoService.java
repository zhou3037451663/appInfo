package com.kgc.service.appinfo;

import com.kgc.pojo.AppInfo;

import java.util.Date;
import java.util.List;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/9
 * @Explain:
 */
public interface AppInfoService {
    /**
     * 查询数据和分页展示
     * @param softwareName
     * @param flatFormId
     * @param categoryLevel1
     * @param categoryLevel2
     * @param categoryLevel3
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    public List<AppInfo> appInfoList(String softwareName, int flatFormId, int categoryLevel1,
                                     int categoryLevel2, int categoryLevel3,int status,int currentPageNo,
                                     int pageSize);

    /**
     * 查询总数据条数
     * @param softwareName
     * @param flatFormId
     * @param categoryLevel1
     * @param categoryLevel2
     * @param categoryLevel3
     * @return
     */
    public int getAppInfoCount(String softwareName, int flatFormId, int categoryLevel1,
                                         int categoryLevel2, int categoryLevel3,int status);
    /**
     * 查询详细信息
     * @param aid
     * @param versionId
     * @return
     */
    public AppInfo getAppInfoCheck(int aid,int versionId);

    /**
     * 审核
     * @param flatformId
     * @param id
     * @return
     */
    public boolean appInfoSave(Integer flatformId,Integer id);

    public AppInfo getAppInfoByAPKName(int id);

    public boolean updateAppInfo(int modifyBy, Date modifyDate,int versionId,int id);

    public boolean updateLogoPath(int id);
    public boolean updateAppInfo1(AppInfo appInfo);
    public AppInfo getAppInfoCheck1(int aid, int versionId);
    public boolean delAppInfo(Integer id);
    /**
     * 添加app信息
     * @param appInfo
     * @return
     */
    public boolean addAppMessage(AppInfo appInfo);
    public AppInfo getAPKName(String APKName);
    public boolean appInfoSale(Date onSaleDate,Date offSaleDate,int modifyBy,Date modifyDate,int status,int id);
}
