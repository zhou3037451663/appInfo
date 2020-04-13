import com.kgc.pojo.AppCategory;
import com.kgc.pojo.AppInfo;
import com.kgc.service.appcategory.AppCategoryService;
import com.kgc.service.appinfo.AppInfoService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Author: Mr.Zhou
 * @Date 2020/4/9
 * @Explain:
 */
public class AppCategoryTest {
//    @Test
//    public void test(){
//        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
//        AppInfoService appInfoService=(AppInfoService) context.getBean("appInfoService");
//       List<AppInfo> list=appInfoService.appInfoList("",0,0,0,0,0,1,20);
//        for (AppInfo appInfo : list) {
//            System.out.println(appInfo.getSoftwareName()+"\t"+appInfo.getAPKName()+"\t"+appInfo.getAppStatus().getValueName()
//            +"\t"+appInfo.getAppFlatform().getValueName()+"\t"+appInfo.getCategoryLevel11().getCategoryName()
//            +"\t"+appInfo.getCategoryLevel22().getCategoryName()+"\t"+appInfo.getCategoryLevel33().getCategoryName()+"\t"+appInfo.getVersionId());
//        }
//        int count=appInfoService.getAppInfoCount("",0,0,0,0,0);
//        System.out.println(count);
//    }
    @Test
    public void text(){
        String str="com.bithack.apparatus-V1.1.2.apk";
        int s=str.indexOf("-");
        System.out.println(s);
        int s1=str.lastIndexOf(".");
        System.out.println(s1);
        String u=str.substring(s+1,s1);
        System.out.println(u);
        String str1=str.substring(str.indexOf("-"),str.indexOf("-")+1);
        System.out.println(str1);
    }

}
