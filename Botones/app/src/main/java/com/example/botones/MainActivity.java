package com.example.botones;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
public class MainActivity extends Activity {
    MediaPlayer mp;
    Button btn;
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        btn= (Button) findViewById(R.id.btnSonido);
        mp = MediaPlayer.create(MainActivity.this, R.raw.sonido);
        btn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mp.start();
            }
        });
    }
    public void onDestroy(){
        mp.stop();
        super.onDestroy();
    }
}

