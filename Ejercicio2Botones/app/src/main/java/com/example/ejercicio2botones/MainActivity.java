package com.example.ejercicio2botones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton jfab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        jfab = (FloatingActionButton) findViewById(R.id.xfab);
//        jfab.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Soy un botón Flotante!",
//                        Toast.LENGTH_LONG).show();
//            }
//        });
    }
}