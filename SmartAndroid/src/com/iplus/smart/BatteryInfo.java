
package com.iplus.smart;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.widget.ListView;

import com.iplus.smart.uiparts.NormalListAdapter;

public class BatteryInfo extends Activity {
    private ListView batteryInfoListView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battery_info);

        // fetch ListView object
        this.batteryInfoListView = (ListView) findViewById(R.id.BatteryInfoListView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter();

        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBroadcastReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mBroadcastReceiver);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        BatteryInfo self = BatteryInfo.this;

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int status = intent.getIntExtra("status", 0);
                int health = intent.getIntExtra("health", 0);
                boolean present = intent.getBooleanExtra("present", false);
                int level = intent.getIntExtra("level", 0);
                int scale = intent.getIntExtra("scale", 0);
                int icon_small = intent.getIntExtra("icon-small", 0);
                int plugged = intent.getIntExtra("plugged", 0);
                int voltage = intent.getIntExtra("voltage", 0);
                int temperature = intent.getIntExtra("temperature", 0);
                String technology = intent.getStringExtra("technology");

                String statusString = "";

                switch (status) {
                    case BatteryManager.BATTERY_STATUS_UNKNOWN:
                        statusString = "unknown";
                        break;
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        statusString = "charging";
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        statusString = "discharging";
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        statusString = "not charging";
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        statusString = "full";
                        break;
                }

                String healthString = "";

                switch (health) {
                    case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                        healthString = "unknown";
                        break;
                    case BatteryManager.BATTERY_HEALTH_GOOD:
                        healthString = "good";
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                        healthString = "overheat";
                        break;
                    case BatteryManager.BATTERY_HEALTH_DEAD:
                        healthString = "dead";
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                        healthString = "voltage";
                        break;
                    case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                        healthString = "unspecified failure";
                        break;
                }

                String acString = "";

                switch (plugged) {
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        acString = "plugged ac";
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        acString = "plugged usb";
                        break;
                }

                Log.v("status", statusString);
                Log.v("health", healthString);
                Log.v("present", String.valueOf(present));
                Log.v("level", String.valueOf(level));
                Log.v("scale", String.valueOf(scale));
                Log.v("icon_small", String.valueOf(icon_small));
                Log.v("plugged", acString);
                Log.v("voltage", String.valueOf(voltage));
                Log.v("temperature", String.valueOf(temperature));
                Log.v("technology", technology);

                // set itemList
                List<Pair<String, String>> itemList = new ArrayList<Pair<String, String>>();

                itemList.add(new Pair<String, String>("STATUS", statusString));
                itemList.add(new Pair<String, String>("HEALTH", healthString));
                itemList.add(new Pair<String, String>("PRESENT", String.valueOf(present)));
                itemList.add(new Pair<String, String>("LEVEL", String.valueOf(level)));
                itemList.add(new Pair<String, String>("SCALE", String.valueOf(scale)));
                itemList.add(new Pair<String, String>("ICON_SMALL", String.valueOf(icon_small)));
                itemList.add(new Pair<String, String>("PLUGGED", acString));
                itemList.add(new Pair<String, String>("VOLTAGE", String.valueOf(voltage)));
                itemList.add(new Pair<String, String>("TEMPERATURE", String.valueOf(temperature)));
                itemList.add(new Pair<String, String>("TECHNOLOGY", technology));

                // create ArrayAdapter for listView
                NormalListAdapter arrayAdapter = new NormalListAdapter(self, 0, itemList);

                self.batteryInfoListView.setAdapter(arrayAdapter);
                self.batteryInfoListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                self.batteryInfoListView.setScrollingCacheEnabled(false);
            }
        }
    };
}
