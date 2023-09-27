package com.example.servicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView jtv;
    private Button jbn,stopButton,jbPause,jbResume;
    MiCrono miCrono;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jtv = (TextView) findViewById(R.id.xtvT);
        jbn = (Button) findViewById(R.id.xbnI);
        jbn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initCrono();
            }
        });
        jbPause = (Button) findViewById(R.id.xbnP);
        jbPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseCrono();
            }
        });
        jbResume = (Button) findViewById(R.id.xbnC);
        jbResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resumeCrono();
            }
        });

        stopButton = (Button) findViewById(R.id.xbnT);
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                stopCrono();
            }
        });
        MiCrono.setUpdateListener(this);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MiCrono.MiServicioBinder binder = (MiCrono.MiServicioBinder) service;
            miCrono = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            miCrono = null;
            isBound = false;
        }
    };

    @Override
    protected void onDestroy() {
        stopCrono();
        super.onDestroy();
    }

    private void initCrono() {
        Intent intent = new Intent(this, MiCrono.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        startService(intent);
    }

    private void pauseCrono(){
        if (isBound && miCrono != null) {
            miCrono.pausarCrono();   // Llama al método público del servicio.
        }
    }

    private void resumeCrono(){
        if (isBound && miCrono != null) {
            miCrono.reanudarCrono();   // Llama al método público del servicio.
        }
    }

    private void stopCrono() {
        Intent in = new Intent(this, MiCrono.class);
        if (isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
        jtv.setText(String.format("%.2f", 0.0) + " segs");
        stopService(in);

    }

    public void refreshCrono(double t) {
        jtv.setText(String.format("%.2f", t) + " segs");
    }
}