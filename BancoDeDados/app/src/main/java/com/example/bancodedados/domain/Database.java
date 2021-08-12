package com.example.bancodedados.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private final String CREATE_TABLE_USERS = "CREATE TABLE users (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name VARCHAR(60)," +
            "email VARCHAR(80)," +
            "user VARCHAR(40) UNIQUE," +
            "password VARCHAR(70))";

    public Database(@Nullable Context context, int version) {
        super(context, "DB_APPLICATION", null, version);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            db.setForeignKeyConstraintsEnabled(true);
        } else {
            db.execSQL("PRAGMA foreign_keys=ON");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean register(String name, String email, String user, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("user", user);
        values.put("password", password);
        if(db.insert("users", null, values) != -1) {
            return true;
        }
        return false;
    }

    public boolean loginAttempt(String user, String password) {
        SQLiteDatabase db = getWritableDatabase();

        // Cursor class store sql query result
        Cursor data = db.query(
                "users",
                null,
                "user = ?", // WHERE clausule
                new String[]{user}, // Bind param
                null,
                null,
                null,
                null
        );

        if(data.moveToFirst()) {
            if(password.equals(data.getString(4))) {
                return true;
            }
        }

        return false;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor data = db.query(
                "users",
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if(data.moveToFirst()) {
            do {
                int _id = data.getInt(0);
                String name = data.getString(1);
                String email = data.getString(2);
                String user = data.getString(3);
                String password = data.getString(4);
                User userObj = new User(_id, name, email, user, password);
                users.add(userObj);
            } while (data.moveToNext());
        }

        return users;
    }
}
