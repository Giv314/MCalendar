package com.fetin.calendarone;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OpcoesPessoas extends AppCompatActivity {
    private SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("data/data/com.fetin.calendarone/databases/bd_pessoa.db", null);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcoes);
        Button btnRenomear = findViewById(R.id.botRename);
        Button btnExcluir = findViewById(R.id.botDelete);
        TextView PessoaSelecionada = findViewById(R.id.texto_nome);
        BancoDados helper = new BancoDados(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Intent intent = getIntent();
        String NomeDigitado = intent.getStringExtra("NomeDigitado");
        int codigo = intent.getIntExtra("t", 0);
        PessoaSelecionada.setText(NomeDigitado);


        btnRenomear.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(), AtualizarPessoa.class);
            intent2.putExtra("t", codigo);
            startActivity(intent);
        });

        btnExcluir.setOnClickListener(view ->{
            ModeloPessoas pessoa = new ModeloPessoas();
            pessoa.setCodigo(codigo);

            Toast.makeText(this, "Pessoa excluida!", Toast.LENGTH_SHORT).show();
            Intent intent3 = new Intent(getApplicationContext(), ListaPessoas.class);
            startActivity(intent3);
        });
    }
}
