package com.example.intentos_ejercicio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText jETdivisor,jETdividendo;
    Button jBT;
    Bundle bundle;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jETdividendo = (EditText) findViewById(R.id.xETdividendo);
        jETdivisor = (EditText) findViewById(R.id.xETdivisor);

        jBT = (Button) findViewById(R.id.xBTresult);
        jBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,DivisionActivity.class);
                bundle = new Bundle();
                bundle.putString("dividendo",jETdividendo.getText().toString());
                bundle.putString("divisor", jETdivisor.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}