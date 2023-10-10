package com.example.division;

import android.os.Bundle;
import android.app.Activity;
import android.widget.*;
import android.content.Intent;

import java.text.DecimalFormat;

public class SegundaActivity extends Activity{
    Bundle bdl;
    TextView jetDividendo, jetDivisor, jetCociente, jetResiduo;
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_segunda);

        jetDividendo = findViewById(R.id.textDividendo);
        jetDivisor = findViewById(R.id.textDivisor);
        jetCociente = findViewById(R.id.textCociente);
        jetResiduo = findViewById(R.id.Res1);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            String valorDividendo = extras.getString("Dividendo");
            String valorDivisor = extras.getString("Divisor");

            jetDividendo.setText(valorDividendo);
            jetDivisor.setText(valorDivisor);

            int Div,Divis;
            int Temporal, index, tomaIndex;
            float Cociente;
            String residuoAcum = "";
            String strCociente, strDiv;

            Div = Integer.parseInt(valorDividendo);
            Divis = Integer.parseInt(valorDivisor);
            index = 1;

            Cociente = (float)Div/Divis;
            strCociente = Cociente+"";


            //Obtener el numero de decimales de cociente
            int decimalIndex = strCociente.indexOf('.');
            int numDecimales = strCociente.length() - decimalIndex-1;

            strDiv = Integer.toString(Div);
            do {
                String primerosDigitosStr = strDiv.substring(0, index);
                Temporal = Integer.parseInt(primerosDigitosStr);
                index = index+1;
                strCociente = " " + strCociente;
            } while (Temporal <= Divis);
            strCociente = " " + strCociente;
            int IndexIni=index;
            String auxRes;
            int diferencia = 0;
            while(index-1<=strDiv.length()+numDecimales) {
                String Res = "" + Temporal % Divis;
                auxRes = Res;
                String tem = String.valueOf(Temporal);
                diferencia += tem.length()-auxRes.length();
                for (int i = 0; i < diferencia; i++) {
                    auxRes = "  " + auxRes;
                }
                if (index < strDiv.length()+1){
                    String sigDigito = strDiv.substring(index - 1, index++);
                    Res += sigDigito;
                    auxRes+= sigDigito;
                }
                else {
                    Res+= "0";
                    auxRes+= "0";
                    index++;
                }
                if (index == IndexIni) {
                    residuoAcum += auxRes;
                }else {
                    residuoAcum += auxRes + "\n" ;
                }
                Temporal = Integer.parseInt(Res);
            }
            jetResiduo.setText(residuoAcum);
            jetCociente.setText(strCociente);

        }
    }

    // Método para volver a la actividad principal
    private void volverAPrincipal() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Cierra esta actividad para evitar que el usuario regrese aquí desde la actividad principal
    }
}