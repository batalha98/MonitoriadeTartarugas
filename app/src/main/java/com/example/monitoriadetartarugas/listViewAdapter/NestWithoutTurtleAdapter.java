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
import com.example.monitoriadetartarugas.domain.entitys.NestWithoutTurtle;

import java.util.ArrayList;
import java.util.List;

public class NestWithoutTurtleAdapter extends ArrayAdapter<NestWithoutTurtle> {
    private List<NestWithoutTurtle> withoutTurtles = new ArrayList<>();
    private Context context;

    public NestWithoutTurtleAdapter(List<NestWithoutTurtle> withoutTurtles, Context context) {
        super(context, R.layout.item_layout, withoutTurtles);
        this.withoutTurtles = withoutTurtles;
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View row = layoutInflater.inflate(R.layout.item_layout, parent, false);

        TextView txt_header = row.findViewById(R.id.txt_header);
        TextView txt_subheader = row.findViewById(R.id.txt_subheader);
        TextView txt_content = row.findViewById(R.id.txt_content);

        txt_header.setText("Specie: "+withoutTurtles.get(position).getIdspecie().getSpecie());
        txt_subheader.setText("IdNest: "+withoutTurtles.get(position).getIdnest().getIdnest());
        txt_content.setText(withoutTurtles.get(position).getIdnest().toString());

        return row;
    }

    public void removeItems(List<NestWithoutTurtle> nestWithoutTurtles){
        for(NestWithoutTurtle wturtle: nestWithoutTurtles){
            withoutTurtles.remove(wturtle);
        }

        notifyDataSetChanged();
    }
}
