package com.kgc.pojo;

import java.util.Date;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/9
 * @Explain:
 */
public class AppInfo {
    private Integer id;//主键id
    private String softwareName;//软件名称
    private String APKName;//APK名称（唯一）
    private String supportROM;//支持ROM
    private String interfaceLanguage;//界面语言
    private double softwareSize;//软件大小（单位：M）
    private Date updateDate;//更新日期
    private Integer devId;//开发者id（来源于：dev_user表的开发者id）
    private String appInfo;//应用简介
    private Integer status;//状态（来源于：data_dictionary，1 待审核 2 审核通过 3 审核不通过 4 已上架 5 已下架）
    private Date onSaleDate;//上架时间
    private Date offSaleDate;//下架时间
    private Integer flatformId;//所属平台（来源于：data_dictionary，1 手机 2 平板 3 通用）
    private Integer categoryLevel3;//所属三级分类（来源于：data_dictionary）
    private Integer downloads;//下载量（单位：次）
    private Integer createdBy;//创建者（来源于dev_user开发者信息表的用户id）
    private Date creationDate;//创建时间
    private Integer modifyBy;//更新者（来源于dev_user开发者信息表的用户id）
    private Date modifyDate;//最新更新时间
    private Integer categoryLevel1;//所属一级分类（来源于：data_dictionary）
    private Integer categoryLevel2;//所属二级分类（来源于：data_dictionary）
    private String logoPicPath;//LOGO图片url路径
    private String logoLocPath;//LOGO图片的服务器存储路径
    private Integer versionId;//最新的版本id
    private DataDictionary appStatus;//查看当前状态
    private DataDictionary appFlatform;//查看当前所属平台
    private AppCategory categoryLevel11;//保存1级分类
    private AppCategory categoryLevel22;//保存2级分类
    private AppCategory categoryLevel33;//保存3级分类
    private AppVersion appVersion;//版本名
    private String statusName;
    private String flatformName;
    private String categoryLevel111;
    private String categoryLevel222;
    private String categoryLevel333;
    private String versionNo;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getFlatformName() {
        return flatformName;
    }

    public void setFlatformName(String flatformName) {
        this.flatformName = flatformName;
    }

    public String getCategoryLevel111() {
        return categoryLevel111;
    }

    public void setCategoryLevel111(String categoryLevel111) {
        this.categoryLevel111 = categoryLevel111;
    }

    public String getCategoryLevel222() {
        return categoryLevel222;
    }

    public void setCategoryLevel222(String categoryLevel222) {
        this.categoryLevel222 = categoryLevel222;
    }

    public String getCategoryLevel333() {
        return categoryLevel333;
    }

    public void setCategoryLevel333(String categoryLevel333) {
        this.categoryLevel333 = categoryLevel333;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }



    public AppVersion getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(AppVersion appVersion) {
        this.appVersion = appVersion;
    }

    public DataDictionary getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(DataDictionary appStatus) {
        this.appStatus = appStatus;
    }

    public DataDictionary getAppFlatform() {
        return appFlatform;
    }

    public void setAppFlatform(DataDictionary appFlatform) {
        this.appFlatform = appFlatform;
    }

    public AppCategory getCategoryLevel11() {
        return categoryLevel11;
    }

    public void setCategoryLevel11(AppCategory categoryLevel11) {
        this.categoryLevel11 = categoryLevel11;
    }

    public AppCategory getCategoryLevel22() {
        return categoryLevel22;
    }

    public void setCategoryLevel22(AppCategory categoryLevel22) {
        this.categoryLevel22 = categoryLevel22;
    }

    public AppCategory getCategoryLevel33() {
        return categoryLevel33;
    }

    public void setCategoryLevel33(AppCategory categoryLevel33) {
        this.categoryLevel33 = categoryLevel33;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getAPKName() {
        return APKName;
    }

    public void setAPKName(String APKName) {
        this.APKName = APKName;
    }

    public String getSupportROM() {
        return supportROM;
    }

    public void setSupportROM(String supportROM) {
        this.supportROM = supportROM;
    }

    public String getInterfaceLanguage() {
        return interfaceLanguage;
    }

    public void setInterfaceLanguage(String interfaceLanguage) {
        this.interfaceLanguage = interfaceLanguage;
    }

    public double getSoftwareSize() {
        return softwareSize;
    }

    public void setSoftwareSize(double softwareSize) {
        this.softwareSize = softwareSize;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public String getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(String appInfo) {
        this.appInfo = appInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOnSaleDate() {
        return onSaleDate;
    }

    public void setOnSaleDate(Date onSaleDate) {
        this.onSaleDate = onSaleDate;
    }

    public Date getOffSaleDate() {
        return offSaleDate;
    }

    public void setOffSaleDate(Date offSaleDate) {
        this.offSaleDate = offSaleDate;
    }

    public Integer getFlatformId() {
        return flatformId;
    }

    public void setFlatformId(Integer flatformId) {
        this.flatformId = flatformId;
    }

    public Integer getCategoryLevel3() {
        return categoryLevel3;
    }

    public void setCategoryLevel3(Integer categoryLevel3) {
        this.categoryLevel3 = categoryLevel3;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getCategoryLevel1() {
        return categoryLevel1;
    }

    public void setCategoryLevel1(Integer categoryLevel1) {
        this.categoryLevel1 = categoryLevel1;
    }

    public Integer getCategoryLevel2() {
        return categoryLevel2;
    }

    public void setCategoryLevel2(Integer categoryLevel2) {
        this.categoryLevel2 = categoryLevel2;
    }

    public String getLogoPicPath() {
        return logoPicPath;
    }

    public void setLogoPicPath(String logoPicPath) {
        this.logoPicPath = logoPicPath;
    }

    public String getLogoLocPath() {
        return logoLocPath;
    }

    public void setLogoLocPath(String logoLocPath) {
        this.logoLocPath = logoLocPath;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }


}
