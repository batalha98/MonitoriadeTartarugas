package com.example.monitoriadetartarugas;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.monitoriadetartarugas.database.DataOpenHelper;

public class ActLogin extends AppCompatActivity {
    EditText username;
    EditText password;
    Button btnLogin;
    DataOpenHelper dataOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_login);

        username = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);

        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmptyFields()){
                    Toast.makeText(ActLogin.this, "Fill the blank fields to proceed!", Toast.LENGTH_SHORT).show();
                }else{
                    //verificar se o usuario existe na DB

                    Intent intent = new Intent(ActLogin.this, Act_Main.class);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean isEmptyFields(){
        String txtUsername, txtPassword;
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
}