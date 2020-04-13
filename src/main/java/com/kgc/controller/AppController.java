package com.kgc.controller;

import com.alibaba.fastjson.JSONArray;
import com.kgc.pojo.*;
import com.kgc.service.appcategory.AppCategoryService;
import com.kgc.service.appinfo.AppInfoService;
import com.kgc.service.appversion.AppVersionService;
import com.kgc.service.backenduser.BackendUserService;
import com.kgc.service.datadictionary.DataDictionaryService;
import com.kgc.tools.Constants;
import com.kgc.tools.PageSupport;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/7
 * @Explain:
 */
@Controller
public class AppController {

    @Resource
    private BackendUserService backendUserService;
    @Resource
    private AppCategoryService appCategoryService;
    @Resource
    private DataDictionaryService dataDictionaryService;
    @Resource
    private AppInfoService appInfoService;
    @Resource
    private AppVersionService appVersionService;
    private Logger log = Logger.getLogger(AppController.class);

    @RequestMapping(value = "/login.html")
    public String addList() {
        return "backendlogin";
    }

    @RequestMapping(value = "/dologin.html", method = RequestMethod.POST)
    public String doLogin(@RequestParam(value = "userCode") String userCode,
                          @RequestParam(value = "userPassword") String userPassword,
                          HttpSession session, HttpServletRequest request) {
        log.info("userCode==========================" + userCode);
        log.info("userPassword==========================" + userPassword);
        if (userCode == null) {
            request.setAttribute(Constants.SYS_ERROR, "请输入用户名");
        } else if (userPassword == null) {
            request.setAttribute(Constants.SYS_ERROR, "请输入密码");
        } else {
            BackendUser backendUser = backendUserService.login(userCode, userPassword);
            if (backendUser != null) {
                session.setAttribute(Constants.USER_SESSION, backendUser);
                return "redirect:/sys/main.html";
            } else {
                request.setAttribute(Constants.SYS_ERROR, "用户名或密码输入错误");
            }
        }
        return "backendlogin";
    }

    @RequestMapping(value = "/sys/main.html")
    public String main(HttpSession session) {
        if ((session.getAttribute(Constants.USER_SESSION)) == null) {
            return "redirect:/login.html";
        }
        return "backend/main";
    }

    @RequestMapping(value = "/logout.html")
    public String logout(HttpSession session) {
        session.removeAttribute(Constants.USER_SESSION);
        return "backendlogin";
    }

    @RequestMapping(value = "/backend/app/list")
    public String appList(@RequestParam(value = "querySoftwareName", required = false) String querySoftwareName,
                          @RequestParam(value = "queryFlatformId", required = false) String queryFlatformId,
                          @RequestParam(value = "queryCategoryLevel1", required = false) String queryCategoryLevel1,
                          @RequestParam(value = "queryCategoryLevel2", required = false) String queryCategoryLevel2,
                          @RequestParam(value = "queryCategoryLevel3", required = false) String queryCategoryLevel3,
                          @RequestParam(value = "pageIndex", required = false) String pageIndex, Model model) {
        log.info("querySoftwareName==================================" + querySoftwareName);
        log.info("queryFlatformId==================================" + queryFlatformId);
        log.info("queryCategoryLevel1==================================" + queryCategoryLevel1);
        log.info("queryCategoryLevel2==================================" + queryCategoryLevel2);
        log.info("queryCategoryLevel3==================================" + queryCategoryLevel3);
        log.info("pageIndex==================================" + pageIndex);
        int status = 0;
        //显示分类
        List<AppCategory> categoryLevel1List = null;
        List<AppCategory> categoryLevel2List = null;
        List<AppCategory> categoryLevel3List = null;
        //显示所属平台
        List<DataDictionary> flatFormList = null;
        //查询数据
        List<AppInfo> appInfoList = null;
        //显示1级分类
        categoryLevel1List = appCategoryService.getCategoryLevel1();
        //显示2级分类
        categoryLevel2List = appCategoryService.getCategoryLevel2();
        //显示3级分类
        categoryLevel3List = appCategoryService.getCategoryLevel3();
        //显示所属平台
        flatFormList = dataDictionaryService.getFlatFormList();
        //设置页面大小
        int pageSize = Constants.pageSize;
        //设置页码
        int currentPageNo = 1;
        int _queryFlatformId = 0;
        if (querySoftwareName == null) {
            querySoftwareName = "";
        }
        if (queryFlatformId != null && !"".equals(queryFlatformId)) {
            _queryFlatformId = Integer.parseInt(queryFlatformId);
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
        int totalCount = appInfoService.getAppInfoCount(querySoftwareName, _queryFlatformId, Integer.parseInt(queryCategoryLevel1), Integer.parseInt(queryCategoryLevel2), Integer.parseInt(queryCategoryLevel3), status);
        log.info("totalCount=================================" + totalCount);
        PageSupport pageSupport = new PageSupport();
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(totalCount);
        pageSupport.setCurrentPageNo(currentPageNo);

        appInfoList = appInfoService.appInfoList(querySoftwareName, _queryFlatformId, Integer.parseInt(queryCategoryLevel1), Integer.parseInt(queryCategoryLevel2), Integer.parseInt(queryCategoryLevel3), status, currentPageNo, pageSize);
        model.addAttribute("categoryLevel1List", categoryLevel1List);
        model.addAttribute("categoryLevel2List", categoryLevel2List);
        model.addAttribute("categoryLevel3List", categoryLevel3List);
        model.addAttribute("flatFormList", flatFormList);
        model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
        model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
        model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
        model.addAttribute("querySoftwareName", querySoftwareName);
        model.addAttribute("queryFlatformId", _queryFlatformId);
        model.addAttribute("appInfoList", appInfoList);
        model.addAttribute("pages", pageSupport);
        return "backend/applist";
    }

    @RequestMapping(value = "/backend/app/categorylevellist.json", method = RequestMethod.GET)
    @ResponseBody
    public Object categoryLevelList(@RequestParam(value = "pid") String pid) {
        log.info("pid=================================" + pid);
        List<AppCategory> list = null;
        list = appCategoryService.getCategoryByParentId(Integer.parseInt(pid));
        log.info("list=============================" + list);
        return JSONArray.toJSONString(list);
    }

    @RequestMapping(value = "/backend/app/check")
    public String check(@RequestParam String aid, @RequestParam String vid, Model model) {
        log.info("aid===========================" + aid);
        log.info("vid===========================" + vid);
        AppInfo appInfo = appInfoService.getAppInfoCheck(Integer.parseInt(aid), Integer.parseInt(vid));
        AppVersion appVersion = appVersionService.getAppVersion(Integer.parseInt(vid), Integer.parseInt(aid));
        if (appInfo == null || appVersion == null) {
            return "redirect:/backend/app/list";
        }
        model.addAttribute("appInfo", appInfo);
        model.addAttribute("appVersion", appVersion);
        return "backend/appcheck";
    }

    @RequestMapping(value = "/backend/app/checksave", method = RequestMethod.POST)
    public String checkSave(@RequestParam String id, @RequestParam String status) {
        log.info("id===========================" + id);
        log.info("status===========================" + status);
        if (id != null && status != null) {
            if (appInfoService.appInfoSave(Integer.parseInt(status), Integer.parseInt(id))) {
                return "redirect:/backend/app/list";
            }
        }
        return "backend/appcheck";
    }
}
