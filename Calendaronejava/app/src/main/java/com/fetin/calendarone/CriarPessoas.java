package com.fetin.calendarone;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CriarPessoas extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criarpessoas);
        FloatingActionButton botCheck = findViewById(R.id.ChequeBot);
        botCheck.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(), ListaPessoas.class);
            startActivity(intent2);
        });
    }
}
