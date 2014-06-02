
package com.iplus.smart;

import java.util.List;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    private ListView listView = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // リストビューのオブジェクトを取得
        listView = (ListView) findViewById(R.id.MyListView);

        // リストビュー用のArrayAdapterを作成
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);

        // PackageManagerのオブジェクトを取得
        PackageManager pm = this.getPackageManager();

        // インストール済パッケージ情報を取得する
        List<ApplicationInfo> list = pm.getInstalledApplications(0);

        // パッケージ情報をリストビューに追記
        for (ApplicationInfo ai : list) {
            arrayAdapter.add(ai.packageName);
        }
        listView.setAdapter(arrayAdapter);
    }

}
