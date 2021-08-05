package com.example.bancodedados;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
}
