package com.example.tarea2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    EditText jET1;
    TextView jTV1;
    Button jBT1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jET1 = (EditText)findViewById(R.id.xETNumber);
        jBT1 = (Button) findViewById(R.id.xBTCalcular);
        jTV1 = (TextView) findViewById(R.id.xTVResultado);
        jBT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jTV1.setText("" + ejercicio1(Integer.parseInt(String.valueOf(jET1.getText()))));
            }
        });

    }

    public static int ejercicio1(int numero) {
        int[] numerosMenorMayor = new int[4];
        int[] numerosMayorMenor = new int[4];
        int x = 0, y = 0, resta = 0, saveNumber = 0;
        boolean seguir = true;
        do {
            numerosMenorMayor[0] = numero % 10;
            numerosMenorMayor[1] = (numero / 10) % 10;
            numerosMenorMayor[2] = (numero / 100) % 10;
            numerosMenorMayor[3] = (numero / 1000) % 10;

            Arrays.sort(numerosMenorMayor);

            int j = 0;
            for (int i = numerosMenorMayor.length - 1; i >= 0; i--) {
                numerosMayorMenor[j] = numerosMenorMayor[i];
                j++;
            }

            x = 0;
            y = 0;
            int aux = 1000;

            for (int i = 0; i < numerosMayorMenor.length; i++) {
                y += numerosMayorMenor[i] * aux;
                x += numerosMenorMayor[i] * aux;
                aux /= 10;
            }

            resta = y - x;

            if (numero != resta) {
                numero = resta;
            } else {
                seguir = false;
            }

        } while (seguir);
        return resta;
    }
}