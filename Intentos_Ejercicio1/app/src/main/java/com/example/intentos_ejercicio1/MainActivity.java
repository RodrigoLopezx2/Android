package com.example.intentos_ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText jETA,jETB,jETC;
    Button jBT;
    Bundle bundle;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jETA = (EditText) findViewById(R.id.xETA);
        jETB = (EditText) findViewById(R.id.xETB);
        jETC = (EditText) findViewById(R.id.xETC);
        jBT = (Button) findViewById(R.id.xBTResult);
        jBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,ResultadoActivity.class);
                bundle = new Bundle();
                bundle.putString("A",jETA.getText().toString());
                bundle.putString("B",jETB.getText().toString());
                bundle.putString("C",jETC.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}