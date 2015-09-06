package com.iplus.smart;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;

public class VibratorActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vibrator);

		Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		long[] pattern = { 3000, 1000, 2000, 5000, 3000, 1000 }; // OFF/ON/OFF/ON...
		vibrator.vibrate(pattern, -1);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(10);
		}
		return super.onTouchEvent(event);
	}

}
