package com.example.fragmentosejemplo2;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentListado extends Fragment {
    private GruposListener cl;
    private ListView lv;

    private Grupo[] datos = new Grupo[]{
            new Grupo("\nEstudiante 1", "Calificación 1", "\nReporte de aprovechamiento 1"),
            new Grupo("\nEstudiante 2", "Calificación 2", "\nReporte de aprovechamiento 2"),
            new Grupo("\nEstudiante 3", "Calificación 3", "\nReporte de aprovechamiento 3"),
            new Grupo("\nEstudiante 4", "Calificación 4", "\nReporte de aprovechamiento 4"),
            new Grupo("\nEstudiante 5", "Calificación 5", "\nReporte de aprovechamiento 5")
    };
    public interface GruposListener {
        void onGrupoSeleccionado(Grupo c);
    }
    public void setGruposListener(GruposListener l) {
        this.cl=l;
    }

    class AdaptadorGrupos extends ArrayAdapter<Grupo> {
        Activity a;
        TextView tv1, tv2;
        AdaptadorGrupos(Fragment f) {
            super(f.getActivity(), R.layout.listitem_grupo, datos);
            this.a = f.getActivity();
        }
        public View getView(int i, View vi, ViewGroup vg) {
            LayoutInflater li = a.getLayoutInflater();
            View v = li.inflate(R.layout.listitem_grupo, null);
            tv1 = (TextView)v.findViewById(R.id.xtvDe);
            tv1.setText(datos[i].getDe());
            tv2 = (TextView)v.findViewById(R.id.xtvAsunto);
            tv2.setText(datos[i].getAsunto());
            return(v);
        }
    }


    @Override
    public View onCreateView( LayoutInflater li,  ViewGroup vg,  Bundle savedInstanceState) {
        return li.inflate(R.layout.fragment_listado, vg, false);
    }

    @Override
    public void onActivityCreated( Bundle b) {
        super.onActivityCreated(b);
        lv = (ListView)getView().findViewById(R.id.xlvListado);
        lv.setAdapter(new AdaptadorGrupos(this));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                if (cl!=null) {
                    cl.onGrupoSeleccionado((Grupo) lv.getAdapter().getItem(pos));
                }
            }
        });
    }
}
