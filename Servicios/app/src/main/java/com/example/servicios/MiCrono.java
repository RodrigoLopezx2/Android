package com.example.servicios;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

public class MiCrono extends Service {
    volatile boolean pausado = false;
    private final Object lock = new Object();
    private final Object lockHandle = new Object();
    private Timer t = new Timer();

    private TimerTask timerTask;
    private static final long INTERVALO_ACTUALIZACION = 10; // En milisegundos
    public static MainActivity UPDATE_LISTENER;
    private double n = 0;
    private Handler handler = new Handler();

    public static void setUpdateListener(MainActivity sta) {
        UPDATE_LISTENER = sta;
    }

    public void pararCrono() {
        if (t != null)
            t.cancel();
    }

    public void pausarCrono() {
        pausado = true;
    }

    public void reanudarCrono() {
        pausado = false;
        synchronized (t) {
            t.notify();
             // Despierta el hilo pausado si está en espera
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        iniciarCrono();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if(!pausado){
                    UPDATE_LISTENER.refreshCrono(n);
                }
            }
        };
    }

    @Override
    public void onDestroy() {
        pararCrono();
        super.onDestroy();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return miBinder;
    }

    public void iniciarCrono() {
        t.schedule(timerTask = new TimerTask() {
            public void run() {
                // Realiza el trabajo del hilo aquí

                // Verifica si el hilo debe estar pausado
                if(pausado){
                    synchronized (t) {
                        try {
                            System.out.println("Me pause pero que hago ayudaaaaa");
                            t.wait(); // El hilo se detiene hasta que se llame a reanudar()
                            System.out.println("Ya sali del sueno oooooooooooo");
                        } catch (InterruptedException e) {
                            // Manejo de excepciones si es necesario
                        }
                    }
                }else {
                    n += 0.01;
                    System.out.println("Este es el n que cuenta    :  " + n);
                    handler.sendEmptyMessage(0);
                }
            }
        }, 0, INTERVALO_ACTUALIZACION);
    }

    public class MiServicioBinder extends Binder {
        public MiCrono getService() {
            return MiCrono.this;
        }

        // Puedes agregar métodos públicos aquí que estarán disponibles para llamadas desde otras partes de la aplicación.
        public void hacerAlgo() {
            // Realiza una acción específica dentro del servicio.
        }
    }

    private final IBinder miBinder = new MiServicioBinder();
}
