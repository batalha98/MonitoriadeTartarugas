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
import com.example.monitoriadetartarugas.domain.entitys.Turtle;
import com.example.monitoriadetartarugas.domain.entitys.TurtleNest;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TurtleNestAdapter extends ArrayAdapter<TurtleNest> {
    private List<TurtleNest> turtleNests = new ArrayList<>();
    private Context context;

    public TurtleNestAdapter(List<TurtleNest> turtleNests, Context context) {
        super(context, R.layout.item_layout, turtleNests);
        this.turtleNests = turtleNests;
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View row = layoutInflater.inflate(R.layout.item_layout, parent, false);

        TextView txt_header = row.findViewById(R.id.txt_header);
        TextView txt_subheader = row.findViewById(R.id.txt_subheader);
        TextView txt_content = row.findViewById(R.id.txt_content);

        txt_header.setText("Specie: "+turtleNests.get(position).getIdturtle().getIdspecie().getSpecie());
        txt_subheader.setText("Turtle: "+turtleNests.get(position).getIdturtle().getIdturtle());
        txt_content.setText(turtleNests.get(position).getIdnest().toString());

        return row;
    }

    public void removeItems(List<TurtleNest> turtleNestList){

        for(TurtleNest tnest: turtleNestList){
            turtleNests.remove(tnest);
        }

        notifyDataSetChanged();
    }
}
