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
import com.example.monitoriadetartarugas.domain.entitys.WindCategory;
import com.example.monitoriadetartarugas.domain.entitys.WindDirection;

import java.util.ArrayList;
import java.util.List;

public class WdAdapter extends ArrayAdapter<WindDirection> {
    private List<WindDirection> windDirections = new ArrayList<>();
    private Context context;

    public WdAdapter(List<WindDirection> windDirections, Context context) {
        super(context, R.layout.item_layout, windDirections);
        this.windDirections = windDirections;
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View row = layoutInflater.inflate(R.layout.item_layout_1, parent, false);

        TextView txt_header = row.findViewById(R.id.txt_header);

        txt_header.setText(windDirections.get(position).getName());

        return row;
    }
}
