
package com.iplus.smart.uiparts;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.iplus.smart.R;

public class NormalListAdapter extends ArrayAdapter<Pair<String, String>> {
    private LayoutInflater layoutInflater_;

    public NormalListAdapter(Context context, int textViewResourceId,
            List<Pair<String, String>> objects) {
        super(context, textViewResourceId, objects);
        layoutInflater_ = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 特定の行(position)のデータを得る
        Pair<String, String> item = (Pair<String, String>) getItem(position);

        // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.normal_list_item, null);
        }

        // CustomDataのデータをViewの各Widgetにセットする
        TextView keyView = (TextView) convertView.findViewById(R.id.item_key);
        keyView.setTypeface(Typeface.create(Typeface.SERIF, Typeface.ITALIC));
        keyView.setText(item.first + " :");

        TextView valView = (TextView) convertView.findViewById(R.id.item_val);
        valView.setText(item.second);

        return convertView;
    }
}
