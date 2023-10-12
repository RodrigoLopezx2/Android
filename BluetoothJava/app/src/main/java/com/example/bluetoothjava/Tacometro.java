package com.example.bluetoothjava;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;

public class Tacometro extends AppCompatActivity {
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    InputStream socketInputStream;
    OutputStream socketOutputStream; //SPP UUID.
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    TextView velocidadView;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_tacometro);
        Intent newint = getIntent();
        address = newint.getStringExtra(MainActivity.EXTRA_ADDRESS); // Recibe la dirección del dispositivoBluetooth.
        velocidadView = findViewById(R.id.velocidadView);  new ConnectBT().execute();
    }

    private void msg(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    private void Disconnect() {
        if (btSocket != null) { // Si el btSocket está ocupado.
            try {
                btSocket.close(); // Cierra la conexión.
            } catch (IOException e) {
                msg("Error");
            }
        }
        finish(); // Regresa a la primera plantilla.
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void> { // Hilo de la UI.
        private boolean ConnectSuccess = true;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(Tacometro.this, "Conectando...", "Por favor espere"); // Muestra el dialogo durante la conexión.
        }

        @Override
        protected Void doInBackground(Void... devices) { // Mientras se muestra información paralelamente se ejecuta la conexión.
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();// detecta el dispositivobluetooth.
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);// se conecta ala dirección del dispositivo y verifica su disponibilidad.
                    if (ActivityCompat.checkSelfPermission(Tacometro.this,
                            android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        Log.i("MainActivity", "ActivityCompat#RequestPermissions");
                    }
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);// Crea una conexión RFCOMM(SPP).
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();// inicia la conexión.
                }
            } catch (IOException e) {
                Log.d("Tacometro", "Error al conectarse: ");
                e.printStackTrace();
                ConnectSuccess = false;// Si el if fracasa, se puede verificar aquí la excepción.
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) { // Se verifica si todo está correcto.
            super.onPostExecute(result);
            if (!ConnectSuccess) {
                msg("La conexión falló, tiene Bluetooth el dispositivo?");
                finish();
            } else {
                Log.d("Tacometro", "Me conecté");
                msg("Conectado.");
                isBtConnected = true;
                try {
                    socketOutputStream = btSocket.getOutputStream();
                    socketInputStream = btSocket.getInputStream();//TODO: Mandamos un valor para que empiece a enviar el bluetooth
                    socketOutputStream.write('1');
                    socketOutputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final Handler handler = new Handler();
                final Runnable[] runnables = new Runnable[1]; // Define el código de ejecución.
                final BufferedReader br = new BufferedReader(new InputStreamReader(socketInputStream));
                runnables[0] = new Runnable() {
                    @Override
                    public void run() { // Código típico de ejecución.
                        try {
                            String readMessage = "";
                            byte[] buffer;
                            int bytes; //buffer = new byte[10];//bytes = socketInputStream.read(buffer); // Lee los bytes del buffer deentrada.
                            readMessage = br.readLine();//readMessage = new String(buffer, 0, bytes);
                            //readMessage tiene el valor del tacometro
                            velocidadView.setText("Velocidad= " + readMessage + " rpm");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        handler.postDelayed(runnables[0], 100); // Se repite cada segundo.
                    }
                };
                handler.post(runnables[0]);
            }
            progress.dismiss();
        }
    }

}
