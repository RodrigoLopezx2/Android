package com.example.division;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.*;
import android.content.Intent;
import android.widget.*;
public class MainActivity extends Activity{
    EditText jetDividendo, jetDivisor;
    Button jbn;
    Bundle bdl;
    Intent itn;
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        jetDividendo = (EditText) findViewById(R.id.xetDividendo);
        jetDivisor = (EditText) findViewById(R.id.xetDivisor);
        jbn = (Button) findViewById(R.id.xbn);

        jbn.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                // Crear un solo Bundle para pasar datos a la actividad SegundaActivity
                Bundle bdl = new Bundle();

                // Agregar los datos de los EditText al Bundle
                bdl.putString("Dividendo", jetDividendo.getText().toString());
                bdl.putString("Divisor", jetDivisor.getText().toString());

                // Adjuntar el Bundle al Intent
                itn = new Intent(MainActivity.this, SegundaActivity.class);
                itn.putExtras(bdl);

                // Iniciar la actividad SegundaActivity
                startActivity(itn);
            }
        });
    }
}