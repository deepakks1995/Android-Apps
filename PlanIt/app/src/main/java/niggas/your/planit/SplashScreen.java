package niggas.your.planit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SplashScreen extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    private Button goButton;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash_screen);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
//        goButton = (Button) findViewById(R.id.splGoButton);
//        goButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               Intent myIntent = new Intent(SplashScreen.this, MainActivity.class);
//                startActivity(myIntent);
//            }
//        });

    }

    public void a(View v){

        Intent myIntent = new Intent(SplashScreen.this, MainActivity.class);

        startActivity(myIntent);
    }
}