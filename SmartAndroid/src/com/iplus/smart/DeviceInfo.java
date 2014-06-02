package com.iplus.smart;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DeviceInfo extends Activity {

	private ListView listView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_info);

		// fetch listview object
		listView = (ListView) findViewById(R.id.DeviceInfoListView);

		// create ArrayAdapter for listView
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		arrayAdapter.add("BOARD:" + Build.BOARD);
		arrayAdapter.add("BOOTLOADER:" + Build.BOOTLOADER); // Android 1.6未対応
		arrayAdapter.add("BRAND:" + Build.BRAND);
		arrayAdapter.add("CPU_ABI:" + Build.CPU_ABI);
		arrayAdapter.add("CPU_ABI2:" + Build.CPU_ABI2); // Android 1.6未対応
		arrayAdapter.add("DEVICE:" + Build.DEVICE);
		arrayAdapter.add("DISPLAY:" + Build.DISPLAY);
		arrayAdapter.add("FINGERPRINT:" + Build.FINGERPRINT);
		arrayAdapter.add("HARDWARE:" + Build.HARDWARE); // Android 1.6未対応
		arrayAdapter.add("HOST:" + Build.HOST);
		arrayAdapter.add("ID:" + Build.ID);
		arrayAdapter.add("MANUFACTURER:" + Build.MANUFACTURER);
		arrayAdapter.add("MODEL:" + Build.MODEL);
		arrayAdapter.add("PRODUCT:" + Build.PRODUCT);
		arrayAdapter.add("TAGS:" + Build.TAGS);
		arrayAdapter.add("TIME:" + Build.TIME);
		arrayAdapter.add("TYPE:" + Build.TYPE);
		arrayAdapter.add("UNKNOWN:" + Build.UNKNOWN); // Android 1.6未対応
		arrayAdapter.add("USER:" + Build.USER);
		arrayAdapter.add("VERSION.CODENAME:" + Build.VERSION.CODENAME);
		arrayAdapter.add("VERSION.INCREMENTAL:" + Build.VERSION.INCREMENTAL);
		arrayAdapter.add("VERSION.RELEASE:" + Build.VERSION.RELEASE);
		arrayAdapter.add("VERSION.SDK_INT:" + Build.VERSION.SDK_INT);

		listView.setAdapter(arrayAdapter);
	}

}
