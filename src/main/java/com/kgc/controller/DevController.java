package com.kgc.controller;

import com.alibaba.fastjson.JSONArray;
import com.kgc.pojo.*;
import com.kgc.service.appcategory.AppCategoryService;
import com.kgc.service.appinfo.AppInfoService;
import com.kgc.service.appversion.AppVersionService;
import com.kgc.service.datadictionary.DataDictionaryService;
import com.kgc.service.devuser.DevUserService;
import com.kgc.tools.Constants;
import com.kgc.tools.PageSupport;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/8
 * @Explain:
 */
@Controller
public class DevController {

    @Resource
    private DevUserService devUserService;
    @Resource
    private DataDictionaryService dataDictionaryService;
    @Resource
    private AppCategoryService appCategoryService;
    @Resource
    private AppInfoService appInfoService;
    @Resource
    private AppVersionService appVersionService;
    private Logger log = Logger.getLogger(DevController.class);

    @RequestMapping(value = "/dev/login")
    public String devLogin() {
        return "devlogin";
    }

    /**
     * 用户登录
     *
     * @param devCode
     * @param devPassword
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String devDoLogin(@RequestParam(value = "devCode") String devCode,
                             @RequestParam(value = "devPassword") String devPassword,
                             HttpSession session, HttpServletRequest request) {
        log.info("devCode=============================" + devCode);
        log.info("devPassword=============================" + devPassword);
        if (devCode == null) {
            request.setAttribute(Constants.SYS_ERROR, "用户名不能为空！");
        } else if (devPassword == null) {
            request.setAttribute(Constants.SYS_ERROR, "密码不能为空！");
        } else {
            DevUser devUser = devUserService.devLogin(devCode, devPassword);
            if (devUser != null) {
                session.setAttribute(Constants.DEV_USER_SESSION, devUser);
                return "redirect:/sys/devmain.html";
            } else {
                request.setAttribute(Constants.SYS_ERROR, "没有此用户");
            }
        }
        return "devlogin";
    }

    @RequestMapping(value = "/sys/devmain.html")
    public String devMain(HttpSession session) {
        if (session.getAttribute(Constants.DEV_USER_SESSION) == null) {
            return "redirect:/dev/login";
        }
        return "developer/main";
    }

    /**
     * 用户注销
     */
    @RequestMapping(value = "/dev/logout")
    public String devLogout(HttpSession session) {
        session.removeAttribute(Constants.DEV_USER_SESSION);
        return "devlogin";
    }

    @RequestMapping(value = "/dev/flatform/app/list")
    public String flatFormAppList(Model model, @RequestParam(value = "querySoftwareName", required = false) String querySoftwareName,
                                  @RequestParam(value = "queryStatus", required = false) String queryStatus,
                                  @RequestParam(value = "queryFlatformId", required = false) String queryFlatformId,
                                  @RequestParam(value = "queryCategoryLevel1", required = false) String queryCategoryLevel1,
                                  @RequestParam(value = "queryCategoryLevel2", required = false) String queryCategoryLevel2,
                                  @RequestParam(value = "queryCategoryLevel3", required = false) String queryCategoryLevel3,
                                  @RequestParam(value = "pageIndex", required = false) String pageIndex) {
        log.info("querySoftwareName============================" + querySoftwareName);
        log.info("queryStatus==================================" + queryStatus);
        log.info("queryFlatformId==================================" + queryFlatformId);
        log.info("queryCategoryLevel1==================================" + queryCategoryLevel1);
        log.info("queryCategoryLevel2==================================" + queryCategoryLevel2);
        log.info("queryCategoryLevel3==================================" + queryCategoryLevel3);
        log.info("pageIndex==================================" + pageIndex);
        //app状态
        List<DataDictionary> statusList = null;
        //所属平台
        List<DataDictionary> flatFormList = null;
        //一级分类
        List<AppCategory> categoryLevel1List = null;
        //二级分类
        List<AppCategory> categoryLevel2List = null;
        //三级分类
        List<AppCategory> categoryLevel3List = null;
        //查询总数据
        List<AppInfo> appInfoList = null;
        //设置页面大小
        int pageSize = Constants.pageSize;
        int currentPageNo = 1;
        if (querySoftwareName == null) {
            querySoftwareName = "";
        }
        if (queryStatus == null || "".equals(queryStatus)) {
            queryStatus = "0";
        }
        if (queryFlatformId == null || "".equals(queryFlatformId)) {
            queryFlatformId = "0";
        }
        if (queryCategoryLevel1 == null || "".equals(queryCategoryLevel1)) {
            queryCategoryLevel1 = "0";
        }
        if (queryCategoryLevel2 == null || "".equals(queryCategoryLevel2)) {
            queryCategoryLevel2 = "0";
        }
        if (queryCategoryLevel3 == null || "".equals(queryCategoryLevel3)) {
            queryCategoryLevel3 = "0";
        }
        if (pageIndex != null) {
            currentPageNo = Integer.parseInt(pageIndex);
        }
        //获取总数据条数
        int totalCount = appInfoService.getAppInfoCount(querySoftwareName, Integer.parseInt(queryFlatformId), Integer.parseInt(queryCategoryLevel1), Integer.parseInt(queryCategoryLevel2), Integer.parseInt(queryCategoryLevel3), Integer.parseInt(queryStatus));
        log.info("totalCount===========================" + totalCount);
        PageSupport pageSupport = new PageSupport();
        pageSupport.setPageSize(pageSize);
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setTotalCount(totalCount);
        //查询数据
        appInfoList = appInfoService.appInfoList(querySoftwareName, Integer.parseInt(queryFlatformId), Integer.parseInt(queryCategoryLevel1), Integer.parseInt(queryCategoryLevel2), Integer.parseInt(queryCategoryLevel3), Integer.parseInt(queryStatus), currentPageNo, pageSize);
        statusList = dataDictionaryService.getStatusList();
        flatFormList = dataDictionaryService.getFlatFormList();
        categoryLevel1List = appCategoryService.getCategoryLevel1();
        categoryLevel2List = appCategoryService.getCategoryLevel2();
        categoryLevel3List = appCategoryService.getCategoryLevel3();

        model.addAttribute("statusList", statusList);
        model.addAttribute("flatFormList", flatFormList);
        model.addAttribute("categoryLevel1List", categoryLevel1List);
        model.addAttribute("categoryLevel2List", categoryLevel2List);
        model.addAttribute("categoryLevel3List", categoryLevel3List);
        model.addAttribute("pages", pageSupport);
        model.addAttribute("appInfoList", appInfoList);
        model.addAttribute("querySoftwareName", querySoftwareName);
        model.addAttribute("queryStatus", queryStatus);
        model.addAttribute("queryFlatformId", queryFlatformId);
        model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
        model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
        model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
        return "developer/appinfolist";
    }

    @RequestMapping(value = "/dev/flatform/app/categorylevellist.json", method = RequestMethod.GET)
    @ResponseBody
    public Object categoryLevelList(@RequestParam String pid) {
        log.info("pid=====================" + pid);
        List<AppCategory> appCategories = null;
        if (pid == null || "".equalsIgnoreCase(pid)) {
            appCategories = appCategoryService.getCategoryLevel1();
        } else {
            appCategories = appCategoryService.getCategoryByParentId(Integer.parseInt(pid));
        }

        return JSONArray.toJSONString(appCategories);
    }

    @RequestMapping(value = "/dev/flatform/app/appview/{appinfoid}")
    public String appView(@PathVariable(value = "appinfoid") String appinfoid, Model model) {
        log.info("appinfoid===============================" + appinfoid);
        AppInfo appInfo = appInfoService.getAppInfoCheck(Integer.parseInt(appinfoid), 0);
        List<AppVersion> version = appVersionService.getAppVersionList(Integer.parseInt(appinfoid));

        if (appInfo == null && version == null) {
            return "redirect:/dev/flatform/app/list";
        }
        model.addAttribute("appInfo", appInfo);
        model.addAttribute("appVersionList", version);
        return "developer/appinfoview";
    }

    @RequestMapping(value = "/dev/flatform/app/appversionadd")
    public String appVersionAdd(@RequestParam String id, Model model) {
        log.info("id=====================" + id);

        List<AppVersion> appVersionList = appVersionService.getAppVersionList(Integer.parseInt(id));
        model.addAttribute("appVersionList", appVersionList);
        model.addAttribute("appVersion", id);
        return "developer/appversionadd";
    }

    @RequestMapping(value = "/dev/flatform/app/addversionsave", method = RequestMethod.POST)
    public String addVersionSave(HttpServletRequest request, AppVersion version, @RequestParam(value = "a_downloadLink", required = false) MultipartFile downloadLink,
                                 HttpSession session) {
        log.info("version===========================" + version);
        //生成新的文件名
        String fileName = null;
        boolean flag = true;
        //获取输入版本号
        String versionNo = version.getVersionNo();
        //设置文件路径
        String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
        if (downloadLink != null) {
            //获取原文件名
            String oldFileName = downloadLink.getOriginalFilename();
            //设置文件大小
            int fileSize = 500000;
            if (downloadLink.getSize() > fileSize) {
                request.setAttribute(Constants.FILE_ERROR, "文件不能大于500kb");
                flag = false;
            } else {
                AppInfo appInfo = appInfoService.getAppInfoByAPKName(version.getAppId());
                String APKName = appInfo.getAPKName();
                log.info("APKName========================" + APKName);
                if (versionNo.equals(oldFileName.substring(oldFileName.indexOf("-") + 1, oldFileName.lastIndexOf(".")))) {
                    fileName = oldFileName;
                } else {
                    StringBuffer sb = new StringBuffer(APKName);
                    sb.append(oldFileName.substring(oldFileName.indexOf("-"), oldFileName.indexOf("-") + 1));
                    sb.append(versionNo);
                    sb.append(oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length()));
                    fileName = sb.toString();
                    log.info("fileName======================" + fileName);
                    File file = new File(path, fileName);
                    if (!file.exists()) {
                        file.mkdirs();
                    }

                    //保存
                    try {
                        downloadLink.transferTo(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                        request.setAttribute(Constants.FILE_ERROR, "文件保存失败!");
                        flag = false;
                    }
                }
            }

            List<AppVersion> appVersionList = appVersionService.getAppVersionList(version.getAppId());
            if (appVersionList != null) {
                for (int i = 0; i < appVersionList.size(); i++) {
                    AppVersion appVersion = appVersionList.get(i);
                    if (appVersion.getVersionNo().equals(versionNo)) {
                        request.setAttribute(Constants.FILE_ERROR, "已有此版本,无法再次添加,请进入版本修改页面进行修改!");
                        flag = false;
                    }
                }
                if (flag) {
                    version.setModifyBy(((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId());
                    version.setModifyDate(new Date());
                    version.setApkFileName(fileName);
                    version.setDownloadLink("/statics/uploadfiles/" + fileName);
                    version.setApkLocPath(path + File.separator + fileName);
                    int appVersionSaveId = appVersionService.addVersionSave(version);
                    int versionId = version.getId();
                    log.info("appVersionSaveId================================" + versionId);
                    if (appVersionSaveId > 0) {
                        boolean f = appInfoService.updateAppInfo(((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId(), new Date(), versionId, version.getAppId());
                        if (f) {
                            return "redirect:/dev/flatform/app/list";
                        }
                    }
                }
            }
        } else {
            request.setAttribute(Constants.FILE_ERROR, "上传版本不能为空!");
        }
        return "redirect:/dev/flatform/app/appversionadd?id=" + version.getAppId();
    }

    @RequestMapping(value = "/dev/flatform/app/appversionmodify")
    public String appVersionModify(Model model, @RequestParam(value = "vid") String vid, @RequestParam(value = "aid") String aid) {
        log.info("vid===================================" + vid);
        log.info("aid===================================" + aid);
        List<AppVersion> appVersionList = null;
        AppVersion appVersion = null;
        appVersionList = appVersionService.getAppVersionList(Integer.parseInt(aid));
        appVersion = appVersionService.getAppVersion(Integer.parseInt(vid), Integer.parseInt(aid));
        model.addAttribute("appVersionList", appVersionList);
        model.addAttribute("appVersion", appVersion);
        return "developer/appversionmodify";
    }

    @RequestMapping(value = "/dev/flatform/app/appversionmodifysave", method = RequestMethod.POST)
    public String addVersionModifySave(AppVersion appVersion, HttpServletRequest request, HttpSession session, @RequestParam(value = "attach", required = false) MultipartFile attach) {
        log.info("appVersion=======================" + appVersion);
        //设置文件路径
        String downloadLink = null;
        String apkLocPath = null;
        String apkFileName = null;
        boolean flag = true;
        //生成新的文件名
        String fileName = null;
        String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
        if (attach != null) {
            //获取原文件名
            String oldFileName = attach.getOriginalFilename();
            String prefix = FilenameUtils.getExtension(oldFileName);
            int fileSize = 500000;
            if (attach.getSize() > fileSize) {
                request.setAttribute(Constants.FILE_ERROR, "文件太大!");
                flag = false;
            } else if (prefix.equalsIgnoreCase("apk")) {
                AppInfo appinfo = appInfoService.getAppInfoByAPKName(appVersion.getAppId());
                String APKName = appinfo.getAPKName();
                log.info("APKName================" + APKName);
                fileName = APKName + "-" + appVersion.getVersionNo() + "." + prefix;
                log.info("fileName=====================" + fileName);
                downloadLink = "/statics/uploadfiles/" + fileName;
                apkFileName = fileName;
                apkLocPath = path + File.separator + fileName;
            } else {
                request.setAttribute(Constants.FILE_ERROR, "文件类型错误!");
                flag = false;
            }
        }
        if (flag) {
            appVersion.setModifyBy(((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId());
            appVersion.setModifyDate(new Date());
            if (attach != null) {
                appVersion.setDownloadLink(downloadLink);
                appVersion.setApkLocPath(apkLocPath);
                appVersion.setApkFileName(apkFileName);
            }
            if (appVersionService.updateAppVersion(appVersion)) {
                File file = new File(path, fileName);
                log.info("file=======================" + file);
                if (file.exists()) {
                    file.mkdirs();
                }
                //保存
                try {
                    attach.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                    request.setAttribute(Constants.FILE_ERROR, "保存失败!");
                    flag = false;
                }
                return "redirect:/dev/flatform/app/list";
            } else {
                request.setAttribute(Constants.FILE_ERROR, "修改失败!");
            }
        }
        return "redirect:/dev/flatform/app/appversionmodify?vid=" + appVersion.getId() + "&aid=" + appVersion.getAppId();
    }


    @RequestMapping(value = "dev/flatform/app/delfile.json", method = RequestMethod.GET)
    @ResponseBody
    public Object delFile(@RequestParam String id, @RequestParam String flag) {
        log.info("id============================" + id);
        log.info("flag============================" + flag);
        Map<String, String> map = new HashMap<>();
        if (flag.equals("apk")) {
            if (appVersionService.updateDelFile(Integer.parseInt(id))) {
                map.put("result", "success");
            } else {
                map.put("result", "failed");
            }
        } else if (flag.equals("logo")) {
            AppInfo appInfo = appInfoService.getAppInfoByAPKName(Integer.parseInt(id));
            String logoLocPath = appInfo.getLogoLocPath();
            if (appInfoService.updateLogoPath(Integer.parseInt(id))) {
                File file = new File(logoLocPath);
                if (file.exists()) {
                    file.delete();
                }
                map.put("result", "success");
            } else {
                map.put("result", "failed");
            }
        }

        return JSONArray.toJSONString(map);
    }

    @RequestMapping(value = "/dev/flatform/app/appinfomodify")
    public String appInfoModify(@RequestParam(value = "id") String id, Model model) {
        log.info("id=============================" + id);
        AppInfo appInfo = appInfoService.getAppInfoCheck1(Integer.parseInt(id), 0);
        model.addAttribute("appInfo", appInfo);
        return "developer/appinfomodify";
    }

    @RequestMapping(value = "/dev/flatform/app/datadictionarylist.json")
    @ResponseBody
    public Object dataDictionaryList(@RequestParam(value = "tcode") String tcode) {
        List<DataDictionary> dictionaries = dataDictionaryService.getFlatFormList();
        return JSONArray.toJSONString(dictionaries);
    }

    @RequestMapping(value = "/dev/flatform/app/appinfomodifysave", method = RequestMethod.POST)
    public String appInfoModifySave(AppInfo appInfo, HttpSession session, HttpServletRequest request, @RequestParam(value = "attach", required = false) MultipartFile attach) {
        log.info("appInfo==============================" + appInfo);
        log.info("appInfo==============================" + appInfo.getStatus());
        log.info("appInfo==============================" + appInfo.getStatusName());
        String logoPicPath = null;
        String logoLocPath = null;
        boolean flag = true;
        String fileName = null;
        //生成路径
        String path = session.getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
        if (!attach.isEmpty()) {
            String oldFileName = attach.getOriginalFilename();//获取原文件名
            log.info("oldFileName================================" + oldFileName);
            String prefix = FilenameUtils.getExtension(oldFileName);//获取文件后缀
            log.info("prefix===============================" + prefix);
            int fileSize = 500000;
            if (attach.getSize() > fileSize) {
                request.setAttribute(Constants.FILE_ERROR, "文件不能大于500kb");
                flag = false;
            } else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {
                AppInfo appInfo1 = appInfoService.getAppInfoByAPKName(appInfo.getId());
                fileName = appInfo1.getAPKName() + ".jpg";
                logoLocPath = path + File.separator + fileName;
                logoPicPath = "/statics/uploadfiles/" + fileName;
            } else {
                request.setAttribute(Constants.FILE_ERROR, "文件格式错误!");
                flag = false;
            }
        }
        if (flag) {
            appInfo.setModifyBy(((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId());
            appInfo.setModifyDate(new Date());
            if (!attach.isEmpty()) {
                appInfo.setLogoLocPath(logoLocPath);
                appInfo.setLogoPicPath(logoPicPath);
            }
            if (appInfoService.updateAppInfo1(appInfo)) {
                if (!attach.isEmpty()) {
                    File file = new File(path, fileName);
                    if (file.exists()) {
                        file.mkdirs();
                    }
                    try {
                        attach.transferTo(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                        request.setAttribute(Constants.FILE_ERROR, "图片保存失败!");
                        flag = false;
                    }
                }
                return "redirect:/dev/flatform/app/list";
            } else {
                request.setAttribute(Constants.FILE_ERROR, "修改失败!");
            }
        }
        return "developer/appinfomodify";
    }

    @RequestMapping(value = "/dev/flatform/app/delapp.json", method = RequestMethod.GET)
    @ResponseBody
    public Object delApp(@RequestParam(value = "id") String id) {
        Map<String, String> map = new HashMap<>();
        AppInfo appInfo = appInfoService.getAppInfoByAPKName(Integer.parseInt(id));
        if (appInfo != null) {
            if (appInfoService.delAppInfo(Integer.parseInt(id))) {
                int s = appVersionService.delAppVersion(Integer.parseInt(id));
                log.info("版本表删除数据为:" + s);
                map.put("delResult", "true");
            } else {
                map.put("delResult", "false");
            }
        } else {
            map.put("delResult", "notexist");
        }
        return JSONArray.toJSONString(map);
    }

    /**
     * 上架下架
     *
     * @param appId
     * @return
     */
    @RequestMapping(value = "/dev/flatform/app/{appId}/sale.json")
    @ResponseBody
    public Object sale(@PathVariable("appId") String appId, HttpSession session) {
        Map<String, String> map = new HashMap<>();
        if (!"".equals(appId) && appId != null) {
            map.put("errorCode", "0");
            AppInfo appInfo = appInfoService.getAppInfoByAPKName(Integer.parseInt(appId));
            if (appInfo.getStatus() == 2 || appInfo.getStatus() == 5) {
                if (appInfoService.appInfoSale(new Date(), null, ((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId(), new Date(), 4, Integer.parseInt(appId))) {
                    map.put("resultMsg", "success");
                } else {
                    map.put("resultMsg", "failed");
                }
            } else if (appInfo.getStatus() == 4) {
                if (appInfoService.appInfoSale(null, new Date(), ((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId(), new Date(), 5, Integer.parseInt(appId))) {
                    map.put("resultMsg", "success");
                } else {
                    map.put("resultMsg", "failed");
                }
            } else {
                map.put("errorCode", "param000001");
            }
        } else {
            map.put("errorCode", "exception000001");
        }

        return JSONArray.toJSONString(map);
    }

    @RequestMapping(value = "/dev/flatform/app/appinfoadd")
    public String appInfoAdd() {
        return "developer/appinfoadd";
    }

    @RequestMapping(value = "/dev/flatform/app/appinfoaddsave", method = RequestMethod.POST)
    public String appInfoAddSave(AppInfo appInfo, HttpSession session, HttpServletRequest request,
                                 @RequestParam("a_logoPicPath") MultipartFile a_logoPicPath) {
        //文件路径
        String logoPicPath = null;
        String logoLocPath = null;
        boolean flag = true;
        String fileName = null;
        String path = session.getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
        if (!a_logoPicPath.isEmpty()) {
            String oldFileName = a_logoPicPath.getOriginalFilename();//获取源文件名称
            String prefix = FilenameUtils.getExtension(oldFileName);//获取文件后缀
            int fileSize = 500000;
            if (a_logoPicPath.getSize() > fileSize) {
                request.setAttribute(Constants.FILE_ERROR, "文件太大");
                flag = false;
            } else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {
                fileName = appInfo.getAPKName() + "." + prefix;
                logoLocPath = path + File.separator + fileName;
                logoPicPath = "/statics/uploadfiles/" + fileName;
            } else {
                request.setAttribute(Constants.FILE_ERROR, "图片格式错误");
                flag = false;
            }
        } else {
            request.setAttribute(Constants.FILE_ERROR, "Logo不能为空!");
            flag = false;
        }
        if (flag) {
            appInfo.setCreatedBy(((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId());
            appInfo.setCreationDate(new Date());
            appInfo.setDevId(((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId());
            appInfo.setLogoPicPath(logoPicPath);
            appInfo.setLogoLocPath(logoLocPath);
            if (appInfoService.addAppMessage(appInfo)) {
                File file = new File(path, fileName);
                if (file.exists()) {
                    file.mkdirs();
                }
                try {
                    a_logoPicPath.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                    request.setAttribute(Constants.FILE_ERROR, "保存Logo失败");
                }
                return "redirect:/dev/flatform/app/list";
            } else {
                request.setAttribute(Constants.FILE_ERROR, "添加App信息失败!");
            }
        }
        return "developer/appinfoadd";
    }

    @RequestMapping(value = "/dev/flatform/app/apkexist.json", method = RequestMethod.GET)
    @ResponseBody
    public Object apkExist(@RequestParam String APKName) {
        log.info("APKName===================" + APKName);
        Map<String, String> map = new HashMap<>();
        AppInfo appInfo = null;
        if (APKName != null) {
            appInfo = appInfoService.getAPKName(APKName);
            if (appInfo != null) {
                map.put("APKName", "exist");
            } else {
                map.put("APKName", "noexist");
            }
        } else {
            map.put("APKName", "empty");
        }
        return JSONArray.toJSONString(map);
    }

}
