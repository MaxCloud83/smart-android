
package com.iplus.smart;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SensorsInfo extends Activity implements SensorEventListener {
    private static String TAG = "SensorsInfoActivity";
    private SensorManager mSensorManager;
    private Sensor mAccelerometerSensor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensors_info);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            StringBuilder builder = new StringBuilder();

            // 数値の単位はm/s^2
            // X軸
            float x = event.values[0];
            // Y軸
            float y = event.values[1];
            // Z軸
            float z = event.values[2];

            builder.append("X : " + (x) + "\n");
            builder.append("Y : " + (y) + "\n");
            builder.append("Z : " + (z) + "\n");

            // Logに出力
            Log.d(TAG, builder.toString());
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        String str = "実装されているセンサー一覧:\n";
        for (Sensor s : sensors) {
            str += s.getName() + "\n";
        }
        Log.v(TAG, str);
        TextView textView = (TextView) findViewById(R.id.textview);
        textView.setText(str);

        // 200msに一度SensorEventを観測するリスナを登録
        mSensorManager.registerListener(this, mAccelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // 非アクティブ時にSensorEventをとらないようにリスナの登録解除
        mSensorManager.unregisterListener(this);
    }
}
