package com.example.monitoriadetartarugas;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.UsersController;
import com.example.monitoriadetartarugas.listViewAdapter.UsersAdapter;

public class ModalBottomSheet extends BottomSheetDialogFragment {
    private DataOpenHelper dataOpenHelper;
    private SQLiteDatabase connection;
    private UsersController usersController;
    private UsersAdapter usersAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_content, container, false);
        Button btn_remove = view.findViewById(R.id.btn_remove);
        Button btn_edit = view.findViewById(R.id.btn_edit);
        final Bundle bundle = getArguments();
        String str;
        final String[] strings;

        str = bundle.getString("ToModify");
        strings = str.split("!");

        dataOpenHelper = new DataOpenHelper(getContext());

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(strings[0].equals("users")){
                    connection = dataOpenHelper.getWritableDatabase();
                    usersController = new UsersController(connection);
                    usersAdapter = new UsersAdapter(usersController.fetchAll(),getContext());

                    usersAdapter.remove(usersController.fetchOne(strings[1]));
                    usersController.remove(strings[1]);

                    Bundle bundle = new Bundle();
                    bundle.putString("idBtn","users");

                    Intent intent = new Intent(getContext(), ListingData.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,0);
                }
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = new Bundle();
                bundle1.putString("user",strings[1]);

                Intent intent = new Intent(getContext(), AddUser.class);
                intent.putExtras(bundle1);
                startActivityForResult(intent,0);
            }
        });

        return view;
    }
}
