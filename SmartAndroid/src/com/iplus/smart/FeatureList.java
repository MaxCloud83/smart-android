
package com.iplus.smart;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FeatureList extends Activity {

    private static String TAG = "FeatureListActivity";
    private ListView featureListView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_list);

        this.featureListView = (ListView) findViewById(R.id.FeatureListView);

        // Create an ArrayAdapter that will contain all list items
        String[] featuresArray = getResources().getStringArray(R.array.featureList);

        /*
         * Assign the name array to that adapter and also choose a simple layout
         * for the list items
         */
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, featuresArray);

        // Assign the adapter to featureListView
        this.featureListView.setAdapter(arrayAdapter);
        this.featureListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        this.featureListView.setScrollingCacheEnabled(false);

        // handle the item click event int featureListView
        this.featureListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
                Log.v(TAG,
                        "position:" + String.valueOf(position) + "  " + "id:" + String.valueOf(id));
            }
        });
    }
}
