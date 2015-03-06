package bigbangtheory.display.yao.bigbangtheory.appinformation;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源类，负责从安卓底层获取资源信息
 * Created by JimmyandHurry on 2015/3/5.
 */
public class AppInformationResource {
    private static final String BIG_BANG_ACTION_MAIN = "android.intent.action.BIGBANGMAIN";
    private Context context;
    private PackageManager packageManager;
    @Inject
    public AppInformationResource(Context context) {
        this.context = context;
        packageManager = context.getPackageManager();
    }

    /**
     * 查询所有category是default的已安装app
     * @return 已经安装app信息列表
     */
    public List<InstalledDefaultApp> queryInstalledDefaultApps() {
        Intent i = new Intent(BIG_BANG_ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        List<ResolveInfo> infos = packageManager.queryIntentActivities(i, 0);
        return parseInfo2DefaultAppInfo(infos);
    }

    /**
     * 查询所有category是launcher的已安装app
     * @return 已经安装app信息列表
     */
    public List<InstalledDefaultApp> queryInstalledLauncherApps() {
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> infos = packageManager.queryIntentActivities(i, 0);
        return parseInfo2DefaultAppInfo(infos);
    }

    /**
     * 根据packageName生成一个可以跳转到bigbang集成app的intent
     * @param packageName 包名
     * @return            intent
     */
    public Intent createInstalledDefaultAppsIntentByPackageName(String packageName) {
        Intent intent = new Intent(BIG_BANG_ACTION_MAIN);
        intent.setPackage(packageName);
        return intent;
    }

    /**
     * 将ResolveInfo列表转换成为InstalledDefaultApp列表
     * @param infos ResolveInfo列表
     * @return      InstalledDefaultApp列表
     */
    private List<InstalledDefaultApp> parseInfo2DefaultAppInfo(List<ResolveInfo> infos) {
        List<InstalledDefaultApp> apps = new ArrayList<>();
        for(ResolveInfo info : infos) {
            InstalledDefaultApp app = new InstalledDefaultApp();
            app.setIcon(info.loadIcon(packageManager));
            app.setLable(info.loadLabel(packageManager).toString());
            app.setPackageName(info.activityInfo.packageName);
            apps.add(app);
        }

        return apps;
    }


}
