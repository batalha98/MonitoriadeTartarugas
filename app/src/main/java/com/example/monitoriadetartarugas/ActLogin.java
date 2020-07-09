package com.example.monitoriadetartarugas;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.UsersController;

public class ActLogin extends AppCompatActivity {
    EditText username;
    EditText password;
    String txtUsername, txtPassword;
    Button btnLogin;
    DataOpenHelper dataOpenHelper;
    SQLiteDatabase conn;
    SharedPreferences sp;
    UsersController usersController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_login);

        sp = getSharedPreferences("user_details",MODE_PRIVATE);
        username = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btn_login);

        dataOpenHelper = new DataOpenHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(isEmptyFields()){
                Toast.makeText(ActLogin.this, "Cannot proceed with blank fields!", Toast.LENGTH_SHORT).show();
            }else{
                conn = dataOpenHelper.getReadableDatabase();
                usersController = new UsersController(conn);
                String uname = username.getText().toString();
                String pwd = password.getText().toString();

                if(usersController.fetchOne(uname, pwd)!=null){
                    Intent intent = new Intent(ActLogin.this, Act_Main.class);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("uname",txtUsername);
                    editor.putString("pwd",txtPassword);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Logged in Successfully!",Toast.LENGTH_SHORT).show();

                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(), "You are not registered! Please contact the owner.",Toast.LENGTH_SHORT).show();
                }
            }
            }
        });
    }

    public boolean isEmptyFields(){
        int control = 0;

        txtUsername = username.getText().toString();
        txtPassword = password.getText().toString();

        if(txtUsername.isEmpty()){
            username.requestFocus();
            control++;
        }else
            if(txtPassword.isEmpty()){
                password.requestFocus();
                control++;
            }

        if(control > 0) return true;
        else return false;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}