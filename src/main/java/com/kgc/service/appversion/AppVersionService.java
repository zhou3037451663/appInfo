package com.kgc.service.appversion;

import com.kgc.pojo.AppVersion;

import java.util.List;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/10
 * @Explain:
 */
public interface AppVersionService {
    public AppVersion getAppVersion(int versionId, int aid);

    public List<AppVersion> getAppVersionList(int aid);

    public int addVersionSave(AppVersion appVersion);

    public boolean updateDelFile(Integer vid);
    public boolean updateAppVersion(AppVersion appVersion);
    public int delAppVersion(int id);
}
