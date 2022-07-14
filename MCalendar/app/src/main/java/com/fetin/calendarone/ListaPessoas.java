package com.fetin.calendarone;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


public class ListaPessoas extends AppCompatActivity {
    ListView listViewPessoas;
    ArrayList<Integer> arrayIds;
    SQLiteDatabase db;
    int idSelecionado;

    private static final String BANCO_NOME = "bd_pessoa";
    private static final String NOME_TABELA = "tb_pessoa";
    private static final String COLUNA_NOME = "Nome";
    private static final String COLUNA_CODIGO = "id";
    private static final String COLUNA_MENSAGENS = "Mensagem";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listapessoas);

        listViewPessoas = findViewById(R.id.ListaPessoas);
        Button criarBot = findViewById(R.id.criarBot);
        EditText CampoPessoa = findViewById(R.id.campoPessoa);

        criarBancoDados();
        ListarPessoas();

         criarBot.setOnClickListener(view -> {
            String NomeDigitado = CampoPessoa.getText().toString();
                    if (NomeDigitado.isEmpty()) {
                        Toast.makeText(this, "Insira o nome da pessoa!", Toast.LENGTH_SHORT).show();
                    } else {
                        criarPessoa(NomeDigitado);
                        Toast.makeText(ListaPessoas.this, "Pessoa adicionada!", Toast.LENGTH_LONG).show();
                        ListarPessoas();
                    }
                });

        listViewPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String MsgMandada = AbreMensagem(position);
                Intent intent2 = new Intent(getApplicationContext(), MostraConversas.class);
                intent2.putExtra("MsgMandada", MsgMandada);
                startActivity(intent2);
            }
        });
        listViewPessoas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                String NomePessoa = (String) listViewPessoas.getItemAtPosition(pos);
                Dialog dialogo = FuncaoOpcoes(NomePessoa, pos);
                dialogo.show();
                return true;
            }
        });
    }

    public Dialog FuncaoOpcoes(String NomePessoa, Integer posicao) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListaPessoas.this);
        builder.setMessage("Deseja Excluir " + NomePessoa + "?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deletar(posicao);
                        Toast.makeText(ListaPessoas.this, "Pessoa deletada!", Toast.LENGTH_SHORT).show();
                        ListarPessoas();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ListarPessoas();
                            }
                        });
        // Retorna o dialog
        return builder.create();
    }

    public void criarBancoDados(){
        try {
            db = openOrCreateDatabase(BANCO_NOME, MODE_PRIVATE, null);
            String QUERY_COLUNA = "CREATE TABLE IF NOT EXISTS " + NOME_TABELA + " ("
                    + COLUNA_CODIGO + " INTEGER PRIMARY KEY," + COLUNA_NOME + " TEXT, " + COLUNA_MENSAGENS + " TEXT)";
            db.execSQL(QUERY_COLUNA);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void criarPessoa(String NomeDigitado){
        try{
            db = openOrCreateDatabase(BANCO_NOME, MODE_PRIVATE, null);
            String sql = "INSERT INTO " + NOME_TABELA + " (" + COLUNA_NOME + ") VALUES (?)";
            SQLiteStatement stmt = db.compileStatement(sql);
            stmt.bindString(1,NomeDigitado);
            stmt.executeInsert();
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deletar(Integer i){
        try{
            db = openOrCreateDatabase(BANCO_NOME, MODE_PRIVATE, null);
            String sql = "DELETE FROM " + NOME_TABELA + " WHERE " + COLUNA_CODIGO +" =?";
            SQLiteStatement stmt = db.compileStatement(sql);
            stmt.bindLong(1, arrayIds.get(i));
            stmt.executeUpdateDelete();
            ListarPessoas();
            db.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String AbreMensagem(int position){
        ListarPessoas();
        String query = "SELECT " + COLUNA_CODIGO + ", " + COLUNA_NOME + ", " + COLUNA_MENSAGENS + " FROM " + NOME_TABELA;
        Cursor cur = db.rawQuery(query, null);
        cur.moveToPosition(position);
        String Msg = cur.getString(2);
        return Msg;
    }
}
