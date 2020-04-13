package com.kgc.pojo;

import java.util.Date;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/9
 * @Explain:
 */
public class AppVersion {
    private Integer id;//主键id
    private Integer appId;//appId（来源于：app_info表的主键id）
    private String versionNo;//版本号
    private String versionInfo;//版本介绍
    private Integer publishStatus;//发布状态（来源于：data_dictionary，1 不发布 2 已发布 3 预发布）
    private String downloadLink;//下载链接
    private double versionSize;//版本大小（单位：M）
    private Integer createdBy;//创建者（来源于dev_user开发者信息表的用户id）
    private Date creationDate;//创建时间
    private Integer modifyBy;//更新者（来源于dev_user开发者信息表的用户id）
    private Date modifyDate;//最新更新时间
    private String apkLocPath;//apk文件的服务器存储路径
    private String apkFileName;//上传的apk文件名称
    private AppInfo appInfo;
    private DataDictionary dataDictionary;
    private String softwareName;
    private String valueName;

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public DataDictionary getDataDictionary() {
        return dataDictionary;
    }

    public void setDataDictionary(DataDictionary dataDictionary) {
        this.dataDictionary = dataDictionary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(String versionInfo) {
        this.versionInfo = versionInfo;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public double getVersionSize() {
        return versionSize;
    }

    public void setVersionSize(double versionSize) {
        this.versionSize = versionSize;
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

    public String getApkLocPath() {
        return apkLocPath;
    }

    public void setApkLocPath(String apkLocPath) {
        this.apkLocPath = apkLocPath;
    }

    public String getApkFileName() {
        return apkFileName;
    }

    public void setApkFileName(String apkFileName) {
        this.apkFileName = apkFileName;
    }

}
