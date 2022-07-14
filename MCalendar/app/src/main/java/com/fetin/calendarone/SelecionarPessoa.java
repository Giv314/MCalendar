package com.fetin.calendarone;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SelecionarPessoa extends AppCompatActivity {
    ListView listViewPessoas;
    ArrayAdapter<String> adapter;
    ArrayList<Integer> arrayIds;
    SQLiteDatabase db;
    public Integer idSelecionado;

    private static final String BANCO_NOME = "bd_pessoa";
    private static final String NOME_TABELA = "tb_pessoa";
    private static final String COLUNA_NOME = "Nome";
    private static final String COLUNA_CODIGO = "id";
    private static final String COLUNA_MENSAGENS = "Mensagem";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_pessoa);
        listViewPessoas = findViewById(R.id.listViewPessoas2);

        Intent intent = getIntent();
        String Mensagem = intent.getStringExtra("sharedText");
        ListarPessoas();
        listViewPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idSelecionado = arrayIds.get(position);
                adicionarMensagem(idSelecionado, Mensagem);
                Toast.makeText(SelecionarPessoa.this, "Mensagem adicionada a pessoa!", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
            }
        });
    }

    public void ListarPessoas(){
        try {
            arrayIds = new ArrayList<>();
            db = openOrCreateDatabase(BANCO_NOME, MODE_PRIVATE, null);
            String query = "SELECT " + COLUNA_CODIGO + ", " + COLUNA_NOME + " FROM " + NOME_TABELA;
            Cursor meuCursor = db.rawQuery(query, null);
            ArrayList<String> linhas = new ArrayList<String>();
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, linhas
            );
            listViewPessoas.setAdapter(adapter);
            meuCursor.moveToFirst();
            while(meuCursor!=null){
                linhas.add(meuCursor.getString(1));
                arrayIds.add(meuCursor.getInt(0));
                meuCursor.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void adicionarMensagem(int id, String Mensagem){
        try{
        db = openOrCreateDatabase(BANCO_NOME, MODE_PRIVATE, null);
        String sql = "UPDATE " + NOME_TABELA + " SET " + COLUNA_MENSAGENS + "=? WHERE " + COLUNA_CODIGO + "=?";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindString(1, Mensagem);
        stmt.bindLong(2, id);
        stmt.executeUpdateDelete();
        db.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
