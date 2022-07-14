package com.fetin.calendarone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AtualizarPessoa extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_pessoas);
        EditText CampoPessoa = findViewById(R.id.CampoPessoa2);
        Button Cheque = findViewById(R.id.ChequeBot2);
        BancoDados db = new BancoDados(getApplication());
        Intent intent = getIntent();
        int codigo = intent.getIntExtra("t", 0);

        Cheque.setOnClickListener(view ->{
           String NovoNome = CampoPessoa.toString();
           db.atualizarPessoas(new ModeloPessoas(codigo, NovoNome));
            Toast.makeText(this, "Pessoa renomeada com sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(getApplicationContext(), ListaPessoas.class);
            startActivity(intent2);
        });
    }
}
