
package com.iplus.smart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LaunchSplashScreen extends Activity {
    private final int SPLASH_DISPLAY_LENGHT = 1000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lanuch_splash_screen);

        /*
         * New Handler to start the Menu-Activity and close this Splash-Screen
         * after some seconds.
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(LaunchSplashScreen.this, MainActivity.class);
                LaunchSplashScreen.this.startActivity(mainIntent);
                LaunchSplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }

}
