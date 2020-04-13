package com.kgc.service.appversion;

import com.kgc.dao.AppVersionMapper;
import com.kgc.pojo.AppVersion;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/10
 * @Explain:
 */
@Service("appVersionService")
public class AppVersionServiceImpl implements AppVersionService {
    @Resource
    private AppVersionMapper appVersionMapper;

    @Override
    public AppVersion getAppVersion(int versionId, int aid) {
        return appVersionMapper.getAppVersion(versionId,aid);
    }

    @Override
    public List<AppVersion> getAppVersionList(int aid) {
        return appVersionMapper.getAppVersionList(aid);
    }

    @Override
    public int addVersionSave(AppVersion appVersion) {
        return appVersionMapper.addVersionSave(appVersion);
    }

    @Override
    public boolean updateDelFile(Integer vid) {
        return appVersionMapper.updateDelFile(vid)>0?true:false;
    }

    @Override
    public boolean updateAppVersion(AppVersion appVersion) {
        return appVersionMapper.updateAppVersion(appVersion)>0?true:false;
    }

    @Override
    public int delAppVersion(int id) {
        return appVersionMapper.delAppVersion(id);
    }
}
