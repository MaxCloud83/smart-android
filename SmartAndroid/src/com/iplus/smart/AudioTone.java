
package com.iplus.smart;

import android.app.Activity;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;

public class AudioTone extends Activity {
    private final int SPLASH_DISPLAY_LENGHT = 2000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ToneGenerator toneGenerator = new ToneGenerator(
                AudioManager.STREAM_SYSTEM,
                ToneGenerator.MAX_VOLUME);
        toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP);

        /*
         * New Handler to start the Menu-Activity and close this Splash-Screen
         * after some seconds.
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toneGenerator.stopTone();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}
