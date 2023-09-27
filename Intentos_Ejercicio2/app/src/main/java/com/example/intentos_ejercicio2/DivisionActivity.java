package com.example.intentos_ejercicio2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DivisionActivity extends Activity {
    TableLayout tableLayout;
    Bundle bdl;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_division);
        tableLayout = (TableLayout) findViewById(R.id.xTL);

        ArrayList<TableRow> arrayRows = new ArrayList<>();
        ArrayList<TextView> arrayColumns = new ArrayList<>();
        
        TextView tvDivisor = new TextView(this);
//        do{
//
//        }while ();
        
        
//        TableRow tableRow = new TableRow(this);
//        TextView textView = new TextView(this);
//        bdl = getIntent().getExtras();
//        textView.setText(bdl.getString("divisor"));
//        String dividendo = bdl.getString("dividendo");
////        printArray(divideByNumber(dividendo));
//        tableRow.addView(textView);
//        int[] arrayNumber = divideByNumber(dividendo);
//        TextView[] arrayTextViews = new TextView[arrayNumber.length];
//        for (int i = 0; i < arrayNumber.length; i++) {
//            arrayTextViews[i] = new TextView(this);
//            arrayTextViews[i].setText("" + arrayNumber[i]);
//            tableRow.addView(arrayTextViews[i]);
//        }
//        tableLayout.addView(tableRow);
    }

    public static int[] divideByNumber(String numberString) {
        int n = numberString.length();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(String.valueOf(numberString.charAt(i)));
        }
        return numbers;
    }

    public static void printArray(int[] array) {
        for (int x :
                array) {
            System.out.println("Array : " + x);
        }
    }
}
