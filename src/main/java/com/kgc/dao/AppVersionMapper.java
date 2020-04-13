package com.kgc.dao;

import com.kgc.pojo.AppVersion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/10
 * @Explain:
 */
public interface AppVersionMapper {
    public AppVersion getAppVersion(@Param("versionId")Integer versionId,
                                    @Param("aid")Integer aid);
    public List<AppVersion> getAppVersionList(@Param("aid")Integer aid);

    public int addVersionSave(AppVersion appVersion);

    public int updateDelFile(Integer vid);

    public int updateAppVersion(AppVersion appVersion);

    public int delAppVersion(Integer id);
}
