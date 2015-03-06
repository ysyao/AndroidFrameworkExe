package bigbangtheory.display.yao.bigbangtheory.ui.appinformation;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.inject.Inject;

import java.util.List;

import bigbangtheory.display.yao.bigbangtheory.R;
import bigbangtheory.display.yao.bigbangtheory.appinformation.AppInformationResource;
import bigbangtheory.display.yao.bigbangtheory.appinformation.AppInformationUtils;
import bigbangtheory.display.yao.bigbangtheory.appinformation.InstalledDefaultApp;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.InjectView;

public class ILifeAppListActivity extends RoboActionBarActivity {
    @Inject
    private AppInformationResource appResource;
    @InjectView(R.id.app_list)
    private ListView mLV;
    private DefaultAppAdapter mAdapter;

    private static final String PACKAGE_NAME = "sccl.platfrom.app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilife_app_list);

        //查询所有已安装的DEFAULT应用
        List<InstalledDefaultApp> installedDefaultApps = appResource.queryInstalledDefaultApps();
        if(installedDefaultApps == null || installedDefaultApps.size() == 0) {
            return;
        }

        //筛选应用，与给定数据匹配，判定哪些是安装的哪些是未安装的
        InstalledDefaultApp app = AppInformationUtils.selectAppsByPackageName(installedDefaultApps, PACKAGE_NAME);
        AppInformationUtils.setIfAppInstalled(app, true);

        //展示所有数据
        displayDefaultApps(installedDefaultApps);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ilife_app_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 展示已安装Default应用
     * @param installedDefaultApps 已经安装DEFUALT应用列表
     */
    private void displayDefaultApps(List<InstalledDefaultApp> installedDefaultApps) {
        if(mAdapter == null) {
            mAdapter = new DefaultAppAdapter(this, installedDefaultApps);
            mLV.setAdapter(mAdapter);
            mLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    InstalledDefaultApp app = mAdapter.getItem(i);
                    if(app.getState() == InstalledDefaultApp.NOT_INSTALLED_APP) {
                        return;
                    }

                    Intent intent = appResource.createInstalledDefaultAppsIntentByPackageName(PACKAGE_NAME);
                    intent.putExtra("token", "let us rock and roll!");
                    startActivity(intent);
                }
            });
        } else {
            mAdapter.updateAdapter(installedDefaultApps);
        }
    }
}
