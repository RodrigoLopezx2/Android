package com.example.bluetoothjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    Button jOnButton, jOffButton, jDiscoverButton, jPairedButton;
    BluetoothAdapter jBlueAdapter;
    ListView devicelist;
    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;
    private Set<BluetoothDevice> pairedDevices;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        jOnButton = (Button) findViewById(R.id.idBtnOnBT);
//        jOffButton = (Button) findViewById(R.id.idBtnOffBT);
//        jOffButton = (Button) findViewById(R.id.idBtnConect);
//        jDiscoverButton = (Button) findViewById(R.id.idBtnDispBT);
        jPairedButton = (Button) findViewById(R.id.button);
        devicelist = findViewById(R.id.listView);

        jBlueAdapter = BluetoothAdapter.getDefaultAdapter();

        //Adapter
        BluetoothManager bluetoothManager = getSystemService(BluetoothManager.class);
        bluetoothAdapter = bluetoothManager.getAdapter();
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            Toast.makeText(getApplicationContext(), "El dispositivo no tiene Bluetooth", Toast.LENGTH_LONG).show();

            finish();
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                Log.i("MainActivity", "ActivityCompat#RequestPermissions");
            }
            startActivityForResult(enableBtIntent, 1);
        }

        jPairedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pairedDevicesList();
            }
        });

    }

    private void pairedDevicesList() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT)
                != PackageManager.PERMISSION_GRANTED) {
            Log.i("MainActivity", "ActivityCompat#RequestPermissions");
        }
        pairedDevices = bluetoothAdapter.getBondedDevices();
        ArrayList list = new ArrayList();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice bt : pairedDevices) {
                list.add(bt.getName() + "\n" + bt.getAddress()); // Obtiene el nombre y dirección deldispositivo.
            }
        } else {
            Toast.makeText(getApplicationContext(), "No se encontraron dispositivos Bluetooth a los cual conectarse", Toast.LENGTH_LONG).show();
        }
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        devicelist.setAdapter(adapter);
        devicelist.setOnItemClickListener(myListClickListener); // Método invocado cuando se selecciona un dispositivo de la lista.
    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
//            String info = ((TextView) v).getText().toString();
//            String address = info.substring(info.length() - 17);
//            Intent i = new Intent(MainActivity.this, Tacometro.class);
//            i.putExtra(EXTRA_ADDRESS, address); //this will be received at ledControl (class) Activity
//            startActivity(i);
            Toast.makeText(getApplicationContext(), "Tratando de conectar", Toast.LENGTH_LONG).show();
        }
    };

}
