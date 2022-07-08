package com.fetin.calendarone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CriarPessoas extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criarpessoas);
        FloatingActionButton botCheck = findViewById(R.id.ChequeBot);
        EditText CampoPessoa = findViewById(R.id.CampoPessoa);
        CampoPessoa.setText("");
        botCheck.setOnClickListener(view -> {
            String NomeDigitado = CampoPessoa.getText().toString();
            if(NomeDigitado.isEmpty()) {
                Toast.makeText(CriarPessoas.this, "Insira o nome da pessoa!", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent2 = new Intent(getApplicationContext(), ListaPessoas.class);
                intent2.putExtra("NomeDigitado", NomeDigitado);
                startActivity(intent2);
            }
        });
    }
}
