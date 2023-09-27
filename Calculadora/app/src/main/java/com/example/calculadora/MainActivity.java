package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button jBT0,jBT1,jBT2,jBT3,jBT4,jBT5,jBT6,jBT7,jBT8,jBT9,jBTMaravilloso,jBTFactorial,jBLimpiar;
    TextView jTV1;
    String tolalNumero = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jBT0 = (Button) findViewById(R.id.button0);
        jBT1 = (Button) findViewById(R.id.button1);
        jBT2 = (Button) findViewById(R.id.button2);
        jBT3 = (Button) findViewById(R.id.button3);
        jBT4 = (Button) findViewById(R.id.button4);
        jBT5 = (Button) findViewById(R.id.button5);
        jBT6 = (Button) findViewById(R.id.button6);
        jBT7 = (Button) findViewById(R.id.button7);
        jBT8 = (Button) findViewById(R.id.button8);
        jBT9 = (Button) findViewById(R.id.button9);

        jBTFactorial = (Button) findViewById(R.id.buttonFactorial);
        jBTMaravilloso = (Button) findViewById(R.id.buttonNM);
        jBLimpiar = (Button) findViewById(R.id.buttonLimpiar);

        jTV1 = (TextView) findViewById(R.id.textViewNumeros);

        jBT0.setOnClickListener(this);
        jBT1.setOnClickListener(this);
        jBT2.setOnClickListener(this);
        jBT3.setOnClickListener(this);
        jBT4.setOnClickListener(this);
        jBT5.setOnClickListener(this);
        jBT6.setOnClickListener(this);
        jBT7.setOnClickListener(this);
        jBT8.setOnClickListener(this);
        jBT9.setOnClickListener(this);
        jBTFactorial.setOnClickListener(this);
        jBTMaravilloso.setOnClickListener(this);
        jBLimpiar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String numero = "";
        if(jTV1.getText().equals("Escribe un numero")){
           jTV1.setText("");
        }
        if(v.getId() == R.id.button0){
            numero += "0";
            jTV1.append(numero);
        }
        if(v.getId() == R.id.button1){
            numero += "1";
            jTV1.append(numero);
        }
        if(v.getId() == R.id.button2){
            numero += "2";
            jTV1.append(numero);
        }
        if(v.getId() == R.id.button3){
            numero += "3";
            jTV1.append(numero);
        }
        if(v.getId() == R.id.button4){
            numero += "4";
            jTV1.append(numero);
        }
        if(v.getId() == R.id.button5){
            numero += "5";
            jTV1.append(numero);
        }
        if(v.getId() == R.id.button6){
            numero += "6";
            jTV1.append(numero);
        }
        if(v.getId() == R.id.button7){
            numero += "7";
            jTV1.append(numero);
        }
        if(v.getId() == R.id.button8){
            numero += "8";
            jTV1.append(numero);
        }
        if(v.getId() == R.id.button9){
            numero += "9";
            jTV1.append(numero);
        }
        if(v.getId() == R.id.buttonFactorial){
            int n = Integer.parseInt(tolalNumero);
            System.out.println("Numero convertido : " + n);
            tolalNumero += "!=";
            int factorial = calcularFactorial(n);
            tolalNumero += factorial;
            jTV1.setText(tolalNumero);
        }
        if(v.getId() == R.id.buttonNM){
            jTV1.setText("");
            int n = Integer.parseInt(tolalNumero);
            int m = n;
            if (n > 0) {
                while (n != 1) {
                    if (n % 2 == 0) {
                        n = n / 2;
                    } else {
                        n = n * 3 + 1;
                    }
                    jTV1.append(n + ",");
                }
                jTV1.append("\n"+m + " es un N.M");
            }else{
                jTV1.append("\n"+m + "  No es un N.M");
            }
        }
        if(v.getId() == R.id.buttonLimpiar){
            jTV1.setText("");
            tolalNumero="";
        }

        tolalNumero += numero;
    }
    public static int calcularFactorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * calcularFactorial(n - 1);
        }
    }
}
