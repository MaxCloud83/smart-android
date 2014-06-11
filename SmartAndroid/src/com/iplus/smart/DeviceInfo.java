
package com.iplus.smart;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.widget.ListView;

import com.iplus.smart.uiparts.NormalListAdapter;

public class DeviceInfo extends Activity {

    private ListView deviceInfoListView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_info);

        // fetch ListView object
        this.deviceInfoListView = (ListView) findViewById(R.id.DeviceInfoListView);

        // set itemList
        List<Pair<String, String>> itemList = new ArrayList<Pair<String, String>>();

        itemList.add(new Pair<String, String>("BOARD", Build.BOARD));
        itemList.add(new Pair<String, String>("BOOTLOADER", Build.BOOTLOADER)); // Android
                                                                                // 1.6
                                                                                // later
        itemList.add(new Pair<String, String>("BRAND", Build.BRAND));
        itemList.add(new Pair<String, String>("CPU_ABI", Build.CPU_ABI));
        itemList.add(new Pair<String, String>("CPU_ABI2", Build.CPU_ABI2)); // Android
                                                                            // 1.6
                                                                            // later
        itemList.add(new Pair<String, String>("DEVICE", Build.DEVICE));
        itemList.add(new Pair<String, String>("DISPLAY", Build.DISPLAY));
        itemList.add(new Pair<String, String>("FINGERPRINT", Build.FINGERPRINT));
        itemList.add(new Pair<String, String>("HARDWARE", Build.HARDWARE)); // Android
                                                                            // 1.6
                                                                            // later
        itemList.add(new Pair<String, String>("HOST", Build.HOST));
        itemList.add(new Pair<String, String>("ID", Build.ID));
        itemList.add(new Pair<String, String>("MANUFACTURER", Build.MANUFACTURER));
        itemList.add(new Pair<String, String>("MODEL", Build.MODEL));
        itemList.add(new Pair<String, String>("PRODUCT", Build.PRODUCT));
        itemList.add(new Pair<String, String>("TAGS", Build.TAGS));
        itemList.add(new Pair<String, String>("TIME", String.valueOf(Build.TIME)));
        itemList.add(new Pair<String, String>("TYPE", Build.TYPE));
        itemList.add(new Pair<String, String>("UNKNOWN", Build.UNKNOWN)); // Android
                                                                          // 1.6
                                                                          // later
        itemList.add(new Pair<String, String>("USER", Build.USER));
        itemList.add(new Pair<String, String>("VERSION.CODENAME", Build.VERSION.CODENAME));
        itemList.add(new Pair<String, String>("VERSION.INCREMENTAL", Build.VERSION.INCREMENTAL));
        itemList.add(new Pair<String, String>("VERSION.RELEASE", Build.VERSION.RELEASE));
        itemList.add(new Pair<String, String>("VERSION.SDK_INT", String
                .valueOf(Build.VERSION.SDK_INT)));

        // create ArrayAdapter for listView
        NormalListAdapter arrayAdapter = new NormalListAdapter(this, 0, itemList);

        this.deviceInfoListView.setAdapter(arrayAdapter);
        this.deviceInfoListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        this.deviceInfoListView.setScrollingCacheEnabled(false);
    }

}
