package com.example.bancodedados.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bancodedados.R;
import com.example.bancodedados.domain.Database;

public class MainActivity extends AppCompatActivity {

    EditText editTextUserLogin, editTextPasswordLogin;
    Button buttonLogin, buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database db = new Database(MainActivity.this, 1);

        // References
        editTextUserLogin = findViewById(R.id.editTextUserLogin);
        editTextPasswordLogin = findViewById(R.id.editTextPasswordLogin);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editTextUserLogin.getText().toString();
                String password = editTextPasswordLogin.getText().toString();
                if(db.loginAttempt(user, password)) {
                    Toast.makeText(MainActivity.this, "Usuário válido!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Usuário e/ou senha inválido(s)!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        boolean result = db.register("Jean", "jean@gmail.com", "jecesario", "123456");
        if(result) {
            Toast.makeText(MainActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Deu ruim!", Toast.LENGTH_SHORT).show();
        }
    }

}