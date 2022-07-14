package com.fetin.calendarone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BancoDados extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String BANCO_NOME = "bd_pessoa";
    private static final String TABELA_NOME = "tb_pessoa";
    private static final String COLUNA_NOME = "Nome";
    private static final String COLUNA_CODIGO = "Codigo";
    private static final String COLUNA_MENSAGENS = "Mensagem";

    public BancoDados(Context context) {

        super(context, BANCO_NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY_COLUNA = "CREATE TABLE " + TABELA_NOME + " ("
                + COLUNA_CODIGO + " INTEGER PRIMARY KEY," + COLUNA_NOME + " TEXT, " + COLUNA_MENSAGENS + " TEXT)";
        db.execSQL(QUERY_COLUNA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void adicionarPessoa(ModeloPessoas pessoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, pessoa.getNome());
        db.insert(TABELA_NOME, null, values);
        db.close();
    }

    void DeletarPessoa(ModeloPessoas pessoa) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABELA_NOME, COLUNA_NOME + " = ?", new String[]{pessoa.getNome()});
        db.close();
    }

    /*ModeloPessoas selecionarPessoa(int codigo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.query(TABELA_NOME, new String[]{COLUNA_CODIGO, COLUNA_NOME}, COLUNA_CODIGO +
                " = ?", new String[]{String.valueOf(codigo)}, null, null, null, null);
        if (cur != null) {
            cur.moveToFirst();
        }
        ModeloPessoas pessoa1 = new ModeloPessoas(Integer.parseInt(cur.getString(0)), String.valueOf(cur.getString(1)));
        return pessoa1;
    }

     */

    void atualizarPessoas(ModeloPessoas pessoa) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, pessoa.getNome());
        db.update(TABELA_NOME, values, COLUNA_CODIGO + " = ?",
                new String[]{String.valueOf(pessoa.getCodigo())});
    }

    public List<ModeloPessoas> ListarTodasPessoas(){
        List<ModeloPessoas> ListaPessoas = new ArrayList<ModeloPessoas>();
        String query = "SELECT * FROM " + TABELA_NOME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()) {
            do{
                ModeloPessoas pessoa = new ModeloPessoas();
                pessoa.setCodigo(Integer.parseInt(c.getString(0)));
                pessoa.setNome(c.getString(1));
                ListaPessoas.add(pessoa);
            } while(c.moveToNext());
        }
    return ListaPessoas;
    }
    void adicionarMensagem(ModeloPessoas pessoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_MENSAGENS, pessoa.getNome());
        db.insert(TABELA_NOME, null, values);
        db.close();
    }
}
