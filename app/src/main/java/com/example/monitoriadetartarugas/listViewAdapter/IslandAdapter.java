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
import com.example.monitoriadetartarugas.domain.entitys.Beach;
import com.example.monitoriadetartarugas.domain.entitys.Island;

import java.util.ArrayList;
import java.util.List;

public class IslandAdapter extends ArrayAdapter<Island> {
    private List<Island> islands = new ArrayList<>();
    private Context context;

    public IslandAdapter(List<Island> islands, Context context) {
        super(context, R.layout.item_layout, islands);
        this.islands = islands;
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View row = layoutInflater.inflate(R.layout.item_layout_1, parent, false);

        TextView txt_header = row.findViewById(R.id.txt_header);

        txt_header.setText(islands.get(position).getIsland());

        return row;
    }
}
