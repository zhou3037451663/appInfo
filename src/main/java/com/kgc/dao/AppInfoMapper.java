package com.kgc.dao;

import com.kgc.pojo.AppInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/9
 * @Explain:
 */
public interface AppInfoMapper {
    /**
     * 查询数据和分页展示
     *
     * @param softwareName
     * @param flatFormId
     * @param categoryLevel1
     * @param categoryLevel2
     * @param categoryLevel3
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    public List<AppInfo> appInfoList(@Param("softwareName") String softwareName,
                                     @Param("flatFormId") Integer flatFormId,
                                     @Param("categoryLevel1") Integer categoryLevel1,
                                     @Param("categoryLevel2") Integer categoryLevel2,
                                     @Param("categoryLevel3") Integer categoryLevel3,
                                     @Param("status")Integer status,
                                     @Param("currentPageNo") Integer currentPageNo,
                                     @Param("pageSize") Integer pageSize);

    /**
     * 查询总数据条数
     *
     * @param softwareName
     * @param flatFormId
     * @param categoryLevel1
     * @param categoryLevel2
     * @param categoryLevel3
     * @return
     */
    public int getAppInfoCount(@Param("softwareName") String softwareName,
                               @Param("flatFormId") Integer flatFormId,
                               @Param("categoryLevel1") Integer categoryLevel1,
                               @Param("categoryLevel2") Integer categoryLevel2,
                               @Param("categoryLevel3") Integer categoryLevel3,
                               @Param("status") Integer status);

    /**
     * 查询详细信息
     *
     * @param aid
     * @param versionId
     * @return
     */
    public AppInfo getAppInfoCheck(@Param("aid") Integer aid,
                                   @Param("versionId") Integer versionId);

    /**
     * 审核
     *
     * @param status
     * @param id
     * @return
     */
    public int appInfoSave(@Param("status") Integer status, @Param("id") Integer id);

    public AppInfo getAppInfoByAPKName(@Param("id")Integer id);

    public int updateAppInfo(@Param("modifyBy")Integer modifyBy,
                             @Param("modifyDate")Date modifyDate,
                             @Param("versionId")Integer versionId,
                             @Param("id")Integer id);

    public int updateLogoPath(Integer id);

    public int updateAppInfo1(AppInfo appInfo);
    public AppInfo getAppInfoCheck1(@Param("aid") Integer aid,
                                    @Param("versionId") Integer versionId);
    public int delAppInfo(Integer id);

    /**
     * 添加app信息
     * @param appInfo
     * @return
     */
    public int addAppMessage(AppInfo appInfo);
    public AppInfo getAPKName(String APKName);

    public int appInfoSale(@Param("onSaleDate")Date onSaleDate,
                           @Param("offSaleDate")Date offSaleDate,
                           @Param("modifyBy")Integer modifyBy,
                           @Param("modifyDate")Date modifyDate,
                           @Param("status")Integer status,
                           @Param("id")Integer id);
}
