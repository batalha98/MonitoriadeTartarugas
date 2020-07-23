package com.example.monitoriadetartarugas.listViewAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.monitoriadetartarugas.R;
import com.example.monitoriadetartarugas.domain.entitys.Habitat;
import com.example.monitoriadetartarugas.domain.entitys.Specie;

import java.util.ArrayList;
import java.util.List;

public class HabitatAdapter extends ArrayAdapter<Habitat> {
    private List<Habitat> habitats = new ArrayList<>();
    private Context context;

    public HabitatAdapter(List<Habitat> habitats, Context context) {
        super(context, R.layout.item_layout, habitats);
        this.habitats = habitats;
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View row = layoutInflater.inflate(R.layout.item_layout_1, parent, false);

        TextView txt_header = row.findViewById(R.id.txt_header);

        txt_header.setText(habitats.get(position).getHabitat());

        return row;
    }
}