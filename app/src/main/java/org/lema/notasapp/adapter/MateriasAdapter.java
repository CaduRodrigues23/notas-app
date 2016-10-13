package org.lema.notasapp.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.lema.notasapp.R;
import org.lema.notasapp.modelo.Materia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leonardocordeiro on 18/07/15.
 */
public class MateriasAdapter extends BaseAdapter {

    private List<Materia> materias;
    private Activity activity;

    public MateriasAdapter(Activity activity, List<Materia> materias) {
        this.materias = materias;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return materias.size();
    }

    @Override
    public Materia getItem(int i) {
        return materias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View linha = activity.getLayoutInflater().inflate(R.layout.linha_materia, viewGroup, false);

        TextView nomeDaMateria = (TextView) linha.findViewById(R.id.materia_nome);
        TextView notas = (TextView) linha.findViewById(R.id.estado);

        Materia materia = getItem(i);

        nomeDaMateria.setText(materia.getNome());
        notas.setText("AV1: " + materia.getNotaDaAv1() + " AV2: " + materia.getNotaDaAv2() + " AV3: " + materia.getNotaDaAv3());

        View status = linha.findViewById(R.id.status);
        status.setBackgroundResource(materia.getStatus().getColor());

        return linha;
    }
}
