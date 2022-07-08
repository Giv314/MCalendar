package com.fetin.calendarone;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


public class ListaPessoas extends AppCompatActivity {
    ListView listarPessoas;
    Button btnAdicionar, btnSalvar, btnExcluir;
    BancoDados db = new BancoDados(this);
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listapessoas);
        Intent intent = getIntent(); //obter o Intent enviado
        String NomeDigitado = intent.getStringExtra("NomeDigitado");
        listarPessoas = findViewById(R.id.ListaPessoas);
        btnAdicionar = findViewById(R.id.Botclear);
        btnSalvar = findViewById(R.id.BotSave);
        btnExcluir = findViewById(R.id.BotDelete);
        int t = 0;

        db.adicionarPessoa(new ModeloPessoas(t, NomeDigitado));
        Toast.makeText(ListaPessoas.this, "Pessoa adicionada!", Toast.LENGTH_LONG).show();
        listarPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListaPessoas.this, "teste ", Toast.LENGTH_LONG).show();
            }
        });
        ListarPessoas();
    }


    public void ListarPessoas(){
        List<ModeloPessoas> pessoas = db.ListarTodasPessoas();
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(ListaPessoas.this, android.R.layout.simple_list_item_1, arrayList);
        listarPessoas.setAdapter(adapter);

        for(ModeloPessoas p :  pessoas) {
            arrayList.add(p.getNome());
            adapter.notifyDataSetChanged();
            }
    }
}
