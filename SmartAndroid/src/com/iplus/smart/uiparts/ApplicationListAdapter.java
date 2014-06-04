
package com.iplus.smart.uiparts;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iplus.smart.R;
import com.iplus.smart.models.AppInfo;

public class ApplicationListAdapter extends ArrayAdapter<AppInfo> {
    private LayoutInflater layoutInflater_;

    public ApplicationListAdapter(Context context, int textViewResourceId, List<AppInfo> objects) {
        super(context, textViewResourceId, objects);
        layoutInflater_ = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 特定の行(position)のデータを得る
        AppInfo item = (AppInfo) getItem(position);

        // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.application_list_item, null);
        }

        // CustomDataのデータをViewの各Widgetにセットする
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
        imageView.setImageBitmap(item.appIcon);

        TextView appNameView = (TextView) convertView.findViewById(R.id.app_name);
        appNameView.setText(item.appName);

        TextView appIdView = (TextView) convertView.findViewById(R.id.app_id);
        appIdView.setText(item.appId);

        TextView appSizeView = (TextView) convertView.findViewById(R.id.app_size);
        appSizeView.setText(item.appSize);

        TextView appVerView = (TextView) convertView.findViewById(R.id.app_ver);
        appVerView.setText(item.appVer);

        return convertView;
    }
}
