
package com.iplus.smart;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;

import com.iplus.smart.models.AppInfo;
import com.iplus.smart.uiparts.ApplicationListAdapter;
import com.iplus.smart.utilities.Converters;

public class ApplicationList extends Activity {

    private ListView listView = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_list);

        // fetch ListView object
        listView = (ListView) findViewById(R.id.MyListView);

        // fetch PackageManager object
        PackageManager pm = this.getPackageManager();

        // fetch the list of application installed
        List<ApplicationInfo> list = pm.getInstalledApplications(0);

        List<AppInfo> itemList = new ArrayList<AppInfo>();

        Resources r = getResources();

        // add package information into ListView's arrayAdapter
        Drawable appIcon = null;
        for (ApplicationInfo ai : list) {
            if ((ai.flags & ApplicationInfo.FLAG_SYSTEM) != 1) {
                AppInfo model = new AppInfo();
                model.appName = ai.loadLabel(pm).toString();
                model.appId = ai.packageName;

                try {
                    appIcon = pm.getApplicationIcon(ai.packageName);
                    model.appIcon = ((BitmapDrawable) appIcon).getBitmap();
                } catch (NameNotFoundException e) {
                    // use SmartAndroid ICON
                    Bitmap smartAndroidIcon = BitmapFactory.decodeResource(r,
                            R.mipmap.ic_launcher);
                    model.appIcon = smartAndroidIcon;
                }

                try {
                    model.appVer = pm.getPackageInfo(ai.packageName, 0).versionName;
                } catch (NameNotFoundException e) {
                    model.appVer = "0.0.0";
                }

                File file = new File(ai.publicSourceDir);
                model.appSize = Converters.convertToStringRepresentation(file.length());

                itemList.add(model);
            }
        }

        // create ArrayAdapter for listView
        ApplicationListAdapter arrayAdapter = new ApplicationListAdapter(this, 0, itemList);
        listView.setAdapter(arrayAdapter);
    }
}
