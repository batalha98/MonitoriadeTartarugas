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
import com.example.monitoriadetartarugas.domain.entitys.Users;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends ArrayAdapter<Users> {
    private List<Users> usersList = new ArrayList<>();
    private Context context;

    public UsersAdapter(List<Users> usersList, Context context) {
        super(context, R.layout.item_layout, usersList);
        this.usersList = usersList;
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View row = layoutInflater.inflate(R.layout.item_layout, parent, false);

        TextView txt_header = row.findViewById(R.id.txt_header);
        TextView txt_subheader = row.findViewById(R.id.txt_subheader);
        TextView txt_content = row.findViewById(R.id.txt_content);

        txt_header.setText(usersList.get(position).getEmail());
        txt_subheader.setText(usersList.get(position).getSurname());
        txt_content.setText(usersList.get(position).getFname());

        return row;
    }
}
