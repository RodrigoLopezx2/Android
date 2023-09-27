package com.example.intentos;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SegundaActivity extends Activity {
    EditText jet;
    Bundle bdl;
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_segunda);
        jet = (EditText) findViewById(R.id.xet1);
        bdl = getIntent().getExtras();
        jet.append("Hola " + bdl.getString("NOMBRE"));
    }

}
