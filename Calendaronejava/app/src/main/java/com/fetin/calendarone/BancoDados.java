package com.fetin.calendarone;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDados extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String PESSOA = "bd_pessoa";
    private static final String TABELA_PESSOA = "tb_pessoa";
    private static final String COLUNA_NOME = "Nome";

    public BancoDados(Context context) {
        super(context, PESSOA, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY_COLUNA =  "CREATE TABLE " + TABELA_PESSOA + " ("
                + COLUNA_NOME + " TEXT)";
        db.execSQL(QUERY_COLUNA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void adicionardPessoa(ModeloPessoas pessoa){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, pessoa.getNome());
    }
}
