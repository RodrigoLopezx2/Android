package com.example.figurasexamen;

import java.util.ArrayList;
import java.util.List;

public class ObjetoEsfera {    // Posee los datos del objeto 3D
    float f12d, objSize;
    float phi = 1.3f, rho, theta = 0.3f;
    float v11, v12, v13, v21, v22, v23, v32, v33, v43;
    List<Point2D> listPoints2D = new ArrayList<>();
    List<Point3D> listPoints3D = new ArrayList<>();

    ObjetoEsfera() {    // CAMBIAR LAS COORDENADAS X,Y,Z CON 0,1 PARA CONSTRUIR PRISMA, CILINDRO, PIRAMIDE, CONO Y ESFERA.
        listPoints3D = new ArrayList<>();
        listPoints2D = new ArrayList<>();

        //Cubo
        listPoints3D.add(new Point3D(1, -1, -1));
        listPoints3D.add(new Point3D(1, 1, -1));
        listPoints3D.add(new Point3D(-1, 1, -1));
        listPoints3D.add(new Point3D(-1, -1, -1));
        listPoints3D.add(new Point3D(1, -1, 1));
        listPoints3D.add(new Point3D(1, 1, 1));
        listPoints3D.add(new Point3D(-1, 1, 1));
        listPoints3D.add(new Point3D(-1, -1, 1));
        generarEsfera(listPoints3D, 2, 1, 50);

        objSize = (float) Math.sqrt(12F); // = sqrt(2*2 + 2*2 + 2*2) es la distancia entre dos vertices opuestos
        rho = 5 * objSize;        // para cambiar la perspectiva
    }

    public void generarEsfera(List<Point3D> listPoints3D, double altura, double radioBase, int resolucion) {
        // Genera puntos en la base del cono
        for (int i = 0; i < resolucion; i++) {
            double phi = 2 * Math.PI * i / resolucion; // Ángulo phi (0 a 2pi)
            for (int j = 0; j < resolucion; j++) {
                double theta = Math.PI * j / (resolucion - 1); // Ángulo theta (0 a pi)

                double x = radioBase * Math.sin(theta) * Math.cos(phi);
                double y = radioBase * Math.sin(theta) * Math.sin(phi);
                double z = radioBase * Math.cos(theta);

                Point3D punto = new Point3D(x, y, z);
                listPoints3D.add(punto);
            }
        }
    }

    void initPersp() {
        float costh = (float) Math.cos(theta), sinth = (float) Math.sin(theta), cosph = (float) Math.cos(phi), sinph = (float) Math.sin(phi);
        v11 = -sinth;
        v12 = -cosph * costh;
        v13 = sinph * costh;
        v21 = costh;
        v22 = -cosph * sinth;
        v23 = sinph * sinth;
        v32 = sinph;
        v33 = cosph;
        v43 = -rho;
    }


    void eyeAndScreen() {
        initPersp();
        listPoints2D.clear();
        for (int i = 0; i < listPoints3D.size(); i++) {
            Point3D paint = listPoints3D.get(i);
            float z = (((this.v13 * paint.f18x) + (this.v23 * paint.f19y)) + (this.v33 *
                    paint.f20z)) + this.v43;
            listPoints2D.add(new Point2D(((-this.f12d) * ((this.v11 * paint.f18x) + (this.v21 *
                    paint.f19y))) / z, ((-this.f12d) * (((this.v12 * paint.f18x) + (this.v22 * paint.f19y)) + (this.v32
                    * paint.f20z))) / z));
        }
    }

}