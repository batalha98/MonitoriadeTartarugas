package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.Users;

import java.util.ArrayList;
import java.util.List;

public class UsersController {
    private SQLiteDatabase connection;

    public UsersController(SQLiteDatabase connection){
        this.connection = connection;
    }

    public void insert(Users users){
        ContentValues contentValues = new ContentValues();

        contentValues.put("email", users.getEmail());
        contentValues.put("fname", users.getFname());
        contentValues.put("surname", users.getSurname());
        contentValues.put("password", users.getPassword());

        connection.insertOrThrow("users",null, contentValues);
    }

    public void remove(String email){
        String[] parameters = new String[1];
        parameters[0] = email;

        connection.delete("users","email = ?",parameters);
    }

    public void edit(Users users){
        ContentValues contentValues = new ContentValues();

        String[] parameters = new String[1];
        parameters[0] = users.getEmail();

        connection.update("users", contentValues,"email = ?", parameters);
    }

    public List<Users> fetchAll(){
        List<Users> usersList = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM users;");

        try {
            Cursor result = connection.rawQuery(sql.toString(), null);

            if(result.getCount() > 0){
                result.moveToFirst();

                do{
                    Users users = new Users();

                    users.setEmail(result.getString(result.getColumnIndexOrThrow("email")));
                    users.setFname(result.getString(result.getColumnIndexOrThrow("fname")));
                    users.setSurname(result.getString(result.getColumnIndexOrThrow("surname")));
                    users.setPassword(result.getString(result.getColumnIndexOrThrow("password")));

                    usersList.add(users);
                }while(result.moveToNext());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return usersList;
    }

    public Users fetchOne(String email, String password){
        Users users = new Users();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM users");
        sql.append("  WHERE email = ? AND password = ?;");

        String[] parameters = new String[2];
        parameters[0] = email;
        parameters[1] = password;

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            users.setEmail(result.getString(result.getColumnIndexOrThrow("email")));
            users.setFname(result.getString(result.getColumnIndexOrThrow("fname")));
            users.setSurname(result.getString(result.getColumnIndexOrThrow("surname")));
            users.setPassword(result.getString(result.getColumnIndexOrThrow("password")));

            return users;
        }

        return null;
    }

    public Users fetchOne(String email){
        Users users = new Users();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM users");
        sql.append("  WHERE email = ?;");

        String[] parameters = new String[1];
        parameters[0] = email;

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            users.setEmail(result.getString(result.getColumnIndexOrThrow("email")));
            users.setFname(result.getString(result.getColumnIndexOrThrow("fname")));
            users.setSurname(result.getString(result.getColumnIndexOrThrow("surname")));
            users.setPassword(result.getString(result.getColumnIndexOrThrow("password")));

            return users;
        }

        return null;
    }
}
