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
import com.example.monitoriadetartarugas.domain.entitys.Activities;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesAdapter extends ArrayAdapter<Activities> {
    private List<Activities> activities = new ArrayList<>();
    private Context context;

    public ActivitiesAdapter(List<Activities> activitiesList, Context context) {
        super(context, R.layout.item_layout, activitiesList);
        this.activities = activitiesList;
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View row = layoutInflater.inflate(R.layout.item_layout_1, parent, false);

        TextView txt_header = row.findViewById(R.id.txt_header);

        txt_header.setText(activities.get(position).getActivity());

        return row;
    }
}