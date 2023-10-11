package com.example.figurasexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.*;
import android.hardware.*;
import android.util.Log;
import android.view.View;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    Perspectiva perspectiva;

    public class Dimension {
        public int height, width;

        public Dimension(int w, int h) {
            this.width = w;
            this.height = h;
        }

        public final void set(Dimension d) {
            this.width = d.width;
            this.height = d.height;
        }
    }


    public class Perspectiva extends View implements SensorEventListener {
        int centerX, centerY;
        Dimension dim;
        Sensor gyro;
        int maxX, maxY, minMaxXY;
        Obj obj = new Obj();
        ObjectoCilindro objectoCilindro = new ObjectoCilindro();
        ObjetoEsfera objetoEsfera = new ObjetoEsfera();
        Paint paint = new Paint();
        SensorManager sensorManager;
        int f14x = 180, f15y = 180; /* renamed from: x, renamed from: y */

        @SuppressLint("WrongConstant")
        public Perspectiva(Context c) {
            super(c);
            this.sensorManager = (SensorManager) MainActivity.this.getSystemService("sensor");
            this.gyro = this.sensorManager.getDefaultSensor(11);
            this.sensorManager.registerListener(this, this.gyro, 0);
            this.paint.setAntiAlias(true);
        }

        protected void onDraw(Canvas c) {
            super.onDraw(c);
            this.paint.setColor(Color.parseColor("#ffffff"));
            c.drawPaint(this.paint);
            int x = c.getWidth();
            int y = c.getHeight();
            this.paint.setColor(Color.BLACK);
            this.paint.setTextSize(20);
            c.drawText("x0=" + x / 2 + ", y0=" + y / 2, x / 2 + 20, y / 2-20, this.paint);
            c.drawText("Cono x = " + x / 4 + " y = " + y / 4,10,40, this.paint);
            c.drawText("Cubo x = " + x * 3 / 4 + " y = " + y / 4,x / 2 + 10,40, this.paint);
            c.drawText("Cilindro x = " + x / 4 + " y = " + y * 3 / 4,0 + 10,y / 2 + 40, this.paint);
            c.drawText("Esfera x = " + x * 3 / 4 + " y = " + y * 3 / 4,x / 2 + 10, y / 2 + 40, this.paint);
            this.paint.setColor(Color.rgb(0, 0, 255));
            c.drawLine(x/2, 0, x/2, y, this.paint);
            c.drawLine(0, y/2, x, y/2, this.paint);
            this.paint.setTextAlign(Paint.Align.RIGHT);
            this.paint.setTypeface(Typeface.SANS_SERIF);
            c.drawText("Eje X", x-5, y/2-10, this.paint);
            this.paint.setTextAlign(Paint.Align.LEFT);
            this.paint.setTypeface(Typeface.MONOSPACE);
            c.drawText("Eje Y", x/2+30, 20, this.paint);
            this.dim = new Dimension(getWidth() / 2, getHeight()/2);
            this.maxX = this.dim.width - 1;
            this.maxY = this.dim.height - 1;
            this.minMaxXY = Math.min(this.maxX, this.maxY);
            this.centerX = this.maxX / 2;
            this.centerY = this.maxY / 2;
            this.obj.f12d = (this.obj.rho * ((float) this.minMaxXY)) / this.obj.objSize;
            this.obj.eyeAndScreen();
            line(c, 0, 1); line(c, 1, 2); line(c, 2, 3); line(c, 3, 0); // aristas horizontales inferiores
            line(c, 4, 5); line(c, 5, 6); line(c, 6, 7); line(c, 7, 4); // aristas horizontales superiores
            line(c, 0, 4); line(c, 1, 5); line(c, 2, 6); line(c, 3, 7); // aristas verticales


            this.dim = new Dimension(getWidth() / 2, getHeight()/2);
            this.maxX = this.dim.width - 1;
            this.maxY = this.dim.height - 1;
            this.minMaxXY = Math.min(this.maxX, this.maxY);
            this.centerX = (this.maxX / 2)*3;
            this.centerY = (this.maxY / 2);
            this.obj.f12d = (this.obj.rho * ((float) this.minMaxXY)) / this.obj.objSize;
            this.obj.eyeAndScreen();
            line(c, 0, 1); line(c, 1, 2); line(c, 2, 3); line(c, 3, 0); // aristas horizontales inferiores
            line(c, 4, 5); line(c, 5, 6); line(c, 6, 7); line(c, 7, 4); // aristas horizontales superiores
            line(c, 0, 4); line(c, 1, 5); line(c, 2, 6); line(c, 3, 7); // aristas verticales
            lineCono(c);

            this.dim = new Dimension(getWidth() / 2, getHeight()/2);
            this.maxX = this.dim.width - 1;
            this.maxY = this.dim.height - 1;
            this.minMaxXY = Math.min(this.maxX, this.maxY);
            this.centerX = (this.maxX / 2);
            this.centerY = (this.maxY / 2)*3;
            this.objectoCilindro.f12d = (this.objectoCilindro.rho * ((float) this.minMaxXY)) / this.objectoCilindro.objSize;
            this.objectoCilindro.eyeAndScreen();
            lineC(c, 0, 1); lineC(c, 1, 2); lineC(c, 2, 3); lineC(c, 3, 0); // aristas horizontales inferiores
            lineC(c, 4, 5); lineC(c, 5, 6); lineC(c, 6, 7); lineC(c, 7, 4); // aristas horizontales superiores
            lineC(c, 0, 4); lineC(c, 1, 5); lineC(c, 2, 6); lineC(c, 3, 7); // aristas verticales
            lineCilindro(c);

            this.dim = new Dimension(getWidth() / 2, getHeight()/2);
            this.maxX = this.dim.width - 1;
            this.maxY = this.dim.height - 1;
            this.minMaxXY = Math.min(this.maxX, this.maxY);
            this.centerX = (this.maxX / 2)*3;
            this.centerY = (this.maxY / 2)*3;
            this.objetoEsfera.f12d = (this.objetoEsfera.rho * ((float) this.minMaxXY)) / this.objetoEsfera.objSize;
            this.objetoEsfera.eyeAndScreen();
            lineC(c, 0, 1); lineC(c, 1, 2); lineC(c, 2, 3); lineC(c, 3, 0); // aristas horizontales inferiores
            lineC(c, 4, 5); lineC(c, 5, 6); lineC(c, 6, 7); lineC(c, 7, 4); // aristas horizontales superiores
            lineC(c, 0, 4); lineC(c, 1, 5); lineC(c, 2, 6); lineC(c, 3, 7); // aristas verticales
            lineEsfera(c);

        }

        int iX(float x) {
            return (int) Math.floor((double) (((float) this.centerX) + x));
        }

        int iY(float y) {
            return (int) Math.floor((double) (((float) this.centerY) - y));
        }

        void line(Canvas g, int i, int j){
            this.paint.setColor(Color.parseColor("#2196F3"));//ColorRojo ff0000
            Point2D p = this.obj.listPoints2D.get(i), q = this.obj.listPoints2D.get(j);
            g.drawLine(iX(p.f16x), iY(p.f17y), iX(q.f16x), iY(q.f17y),this.paint);
        }
        void lineCono(Canvas g) {
            Point2D point1 = this.obj.listPoints2D.get(this.obj.listPoints2D.size()-1);
            for (int k = 8; k < this.obj.listPoints2D.size()-2; k++) {
                Point2D point2 = this.obj.listPoints2D.get(k);
                g.drawLine(iX(point1.f16x), iY(point1.f17y), iX(point2.f16x), iY(point2.f17y),this.paint);
            }

        }
        void lineC(Canvas g, int i, int j){
            this.paint.setColor(Color.parseColor("#2196F3"));//ColorRojo ff0000
            Point2D p = this.objectoCilindro.listPoints2D.get(i), q = this.objectoCilindro.listPoints2D.get(j);
            g.drawLine(iX(p.f16x), iY(p.f17y), iX(q.f16x), iY(q.f17y),this.paint);
        }
        void lineCilindro(Canvas g) {
            for (int u = 0; u < 10; u++) {
                for (int k = 8; k < this.objectoCilindro.listPoints2D.size()-1; k++) {
                    Point2D point1 = this.objectoCilindro.listPoints2D.get(k);
                    Point2D point2 = this.objectoCilindro.listPoints2D.get(k+1);
                    g.drawLine(iX(point1.f16x), iY(point1.f17y), iX(point2.f16x), iY(point2.f17y),this.paint);
                }
            }
        }

        void lineEsfera(Canvas g) {
            for (int u = 0; u < 50; u++) {
                for (int k = 8; k < this.objetoEsfera.listPoints2D.size()-1; k++) {
                    Point2D point1 = this.objetoEsfera.listPoints2D.get(k);
                    Point2D point2 = this.objetoEsfera.listPoints2D.get(k+1);
                    g.drawLine(iX(point1.f16x), iY(point1.f17y), iX(point2.f16x), iY(point2.f17y),this.paint);
                }
            }
        }


        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] matrizDeRotacion = new float[16];
            SensorManager.getRotationMatrixFromVector(matrizDeRotacion, sensorEvent.values);
            float[] remapeoMatrizDeRotacion = new float[16];
            SensorManager.remapCoordinateSystem(matrizDeRotacion, 1, 3, remapeoMatrizDeRotacion);
            float[] orientations = new float[3];
            SensorManager.getOrientation(remapeoMatrizDeRotacion, orientations);
            for (int i = 0; i < 3; i++) {
                orientations[i] = (float) Math.toDegrees((double) orientations[i]);
            }
            this.obj.theta = ((float) (getWidth() / 2)) / (((float) this.f14x) + orientations[0]);
            this.obj.phi = ((float) getHeight()) / (((float) this.f15y) + orientations[1]);
            this.obj.rho = (this.obj.phi / this.obj.theta) * ((float) getHeight());
            this.objectoCilindro.theta = ((float) (getWidth() / 2)) / (((float) this.f14x) + orientations[0]);
            this.objectoCilindro.phi = ((float) getHeight()) / (((float) this.f15y) + orientations[1]);
            this.objectoCilindro.rho = (this.obj.phi / this.obj.theta) * ((float) getHeight());
            this.objetoEsfera.theta = ((float) (getWidth() / 2)) / (((float) this.f14x) + orientations[0]);
            this.objetoEsfera.phi = ((float) getHeight()) / (((float) this.f15y) + orientations[1]);
            this.objetoEsfera.rho = (this.obj.phi / this.obj.theta) * ((float) getHeight());
            this.centerX = (int) (((float) this.f14x) + orientations[0]);
            this.centerY = (int) (((float) this.f15y) + orientations[1]);
            invalidate();
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    }



    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setRequestedOrientation(0);
        this.perspectiva = new Perspectiva(this);
        setContentView(this.perspectiva);

    }
}