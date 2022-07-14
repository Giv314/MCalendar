package com.fetin.calendarone;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MostraConversas extends AppCompatActivity {
    SQLiteDatabase db;
    private static final String BANCO_NOME = "bd_pessoa";
    private static final String NOME_TABELA = "tb_pessoa";
    private static final String COLUNA_NOME = "Nome";
    private static final String COLUNA_CODIGO = "id";
    private static final String COLUNA_MENSAGENS = "Mensagem";
    public String Mensagem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_conversas);
        TextView msgMostr = findViewById(R.id.msgMostr);
        msgMostr.setMovementMethod(new ScrollingMovementMethod());
        Intent intent = getIntent();
        Mensagem = intent.getStringExtra("MsgMandada");
            msgMostr.setText(Mensagem);
    }
}
