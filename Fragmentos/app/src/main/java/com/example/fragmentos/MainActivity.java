package com.example.fragmentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.fragmentos.MiFragmento.FragmentoListener;


public class MainActivity extends AppCompatActivity implements FragmentoListener {
    TextView xtv;
    Button jbn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xtv = (TextView) findViewById(R.id.xtv);
        jbn = (Button) findViewById(R.id.xbn);
        jbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiFragmento miFragmento = new MiFragmento();

                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment f = fragmentManager.findFragmentByTag("editor");
                if(f == null) {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.xfl, miFragmento, "editor");
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                xtv.setText("");
                Toast.makeText(MainActivity.this, "Utilizando Fragment",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void digitado(int r, String s) {
        TextView jtv = (TextView) findViewById(R.id.xtv);
        if (r == MiFragmento.OK) {
            jtv.setText(s);
        }
        FragmentManager fm = getSupportFragmentManager();
        Fragment fe = fm.findFragmentByTag("editor");
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fe);
        ft.commit();

    }
}