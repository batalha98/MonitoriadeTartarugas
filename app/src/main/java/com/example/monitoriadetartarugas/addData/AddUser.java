package com.example.monitoriadetartarugas.addData;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.monitoriadetartarugas.ListingData;
import com.example.monitoriadetartarugas.R;
import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.UsersController;
import com.example.monitoriadetartarugas.domain.entitys.Users;

public class AddUser extends AppCompatActivity {
    private EditText email;
    private EditText fname;
    private EditText sname;
    private EditText pwd;
    private Button btnRegister;
    private String user_email;
    private int pilot;

    private UsersController usersController;
    private SQLiteDatabase connection;
    private DataOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pilot=0;

        email = findViewById(R.id.txt_email);
        fname = findViewById(R.id.txt_firstname);
        sname = findViewById(R.id.txt_surname);
        pwd = findViewById(R.id.txt_pwd);
        btnRegister = findViewById(R.id.btn_addUser);

        openHelper = new DataOpenHelper(this);
        verifyParameters();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()==false){
                    confirm();
                }
            }
        });
    }

    private void confirm(){
        String txt_email = email.getText().toString();
        String txt_fname = fname.getText().toString();
        String txt_sname = sname.getText().toString();
        String txt_pwd = pwd.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("idBtn","users");

        if(pilot==1){
            connection = openHelper.getReadableDatabase();
            usersController = new UsersController(connection);
            Users users = new Users();

            users.setEmail(txt_email);
            users.setFname(txt_fname);
            users.setSurname(txt_sname);
            users.setPassword(txt_pwd);

            if(!txt_email.equals(user_email)){
                usersController.edit(users, user_email);
            }else{
                usersController.edit(users);
            }

            Intent intent = new Intent(getApplicationContext(), ListingData.class);
            intent.putExtras(bundle);
            startActivityForResult(intent,0);
            Toast.makeText(getApplicationContext(), "User editted successfully!", Toast.LENGTH_LONG).show();
        }else{
            connection = openHelper.getWritableDatabase();
            usersController = new UsersController(connection);

            if(usersController.fetchOne(txt_email, txt_pwd)!=null){
                Toast.makeText(getApplicationContext(), "Account already exists!", Toast.LENGTH_SHORT).show();
            }else if (usersController.fetchOne(txt_email)!=null){
                Toast.makeText(getApplicationContext(), "Email already exists!", Toast.LENGTH_SHORT).show();
            }else{
                Users user = new Users();
                user.setEmail(txt_email);
                user.setPassword(txt_pwd);
                user.setFname(txt_fname);
                user.setSurname(txt_sname);

                usersController.insert(user);

                Intent intent = new Intent(getApplicationContext(), ListingData.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,0);

                Toast.makeText(getApplicationContext(), "User registered succesfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void verifyParameters(){
        Users users;
        Bundle bundle = getIntent().getExtras();

        connection = openHelper.getReadableDatabase();
        usersController = new UsersController(connection);

        if(bundle!=null && bundle.containsKey("user")){
            user_email = bundle.getString("user");
            users = usersController.fetchOne(user_email);

            email.setText(users.getEmail());
            fname.setText(users.getFname());
            sname.setText(users.getSurname());
            pwd.setText(users.getPassword());

            pilot = 1;
            btnRegister.setText("Edit");
        }
    }

    public boolean validateFields(){
        boolean res = false;

        String txt_email = email.getText().toString();
        String txt_fname = fname.getText().toString();
        String txt_sname = sname.getText().toString();
        String txt_pwd = pwd.getText().toString();

        if(res = isEmptyField(txt_email)){
            email.requestFocus();
        }else
        if(res = isEmptyField(txt_fname)){
            fname.requestFocus();
        }else
        if(res = isEmptyField(txt_sname)){
            sname.requestFocus();
        }else
        if(res = isEmptyField(txt_pwd)){
            pwd.requestFocus();
        }


        if(res){
            Toast.makeText(getApplicationContext(), "Fill the blank fields!", Toast.LENGTH_SHORT).show();
        }

        return res;
    }

    private boolean isEmptyField(String value){
        boolean result = (TextUtils.isEmpty(value) || value.trim().isEmpty());

        return result;
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}