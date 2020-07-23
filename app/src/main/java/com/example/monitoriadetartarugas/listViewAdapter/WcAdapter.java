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

import java.util.ArrayList;
import java.util.List;

public class WcAdapter extends ArrayAdapter<WindCategory> {
    private List<WindCategory> windCategories = new ArrayList<>();
    private Context context;

    public WcAdapter(List<WindCategory> windCategories, Context context) {
        super(context, R.layout.item_layout, windCategories);
        this.windCategories = windCategories;
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View row = layoutInflater.inflate(R.layout.item_layout_1, parent, false);

        TextView txt_header = row.findViewById(R.id.txt_header);

        txt_header.setText(windCategories.get(position).getName());

        return row;
    }
}
