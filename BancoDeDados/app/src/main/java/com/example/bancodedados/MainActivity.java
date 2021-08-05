package com.example.bancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database db = new Database(MainActivity.this, 1);
        boolean result = db.register("Jean", "jean@gmail.com", "jecesario", "123456");
        if(result) {
            Toast.makeText(MainActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Deu ruim!", Toast.LENGTH_SHORT).show();
        }
    }

}