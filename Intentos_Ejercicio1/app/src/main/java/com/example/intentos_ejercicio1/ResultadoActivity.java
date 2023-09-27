package com.example.intentos_ejercicio1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ResultadoActivity extends Activity {
    Bundle bundle;
    TextView jTVa, jTVb, jTVc,jTVx1,jTVx2;

    int a, b, c;

    @Override
    protected void onCreate(Bundle bn) {
        super.onCreate(bn);
        setContentView(R.layout.activity_resultado);
        jTVa = (TextView) findViewById(R.id.xTVa);
        jTVb = (TextView) findViewById(R.id.xTVb);
        jTVc = (TextView) findViewById(R.id.xTVc);
        jTVx1 = (TextView) findViewById(R.id.xTVx1);
        jTVx2 = (TextView) findViewById(R.id.xTVx2);
        bundle = getIntent().getExtras();

        jTVa.setText(bundle.getString("A"));
        jTVb.setText(bundle.getString("B"));
        jTVc.setText(bundle.getString("C"));
        double a = Double.parseDouble(bundle.getString("A"));
        double b = Double.parseDouble(bundle.getString("B"));
        double c = Double.parseDouble(bundle.getString("C"));

        double discriminante = b * b - 4 * a * c;

        if (discriminante > 0) {
            double x1 = (-b + Math.sqrt(discriminante)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminante)) / (2 * a);
            System.out.println("x1 : " + x1);
            System.out.println("x2 : " + x2);
            jTVx1.setText(String.valueOf(x1));
            jTVx2.setText(String.valueOf(x2));
        } else if (discriminante == 0) {
            double x = -b / (2 * a);
            jTVx1.setText(String.valueOf(x));
        } else {
            jTVx1.setText("Sin solucion real");
            jTVx2.setText("Sin solucion real");
        }
        System.out.println("Fin");

    }

}
