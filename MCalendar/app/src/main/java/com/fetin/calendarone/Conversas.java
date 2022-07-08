package com.fetin.calendarone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class Conversas extends AppCompatActivity {

    private TextView texto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversas);
        texto2 = findViewById(R.id.TextoRecebido);
        PegarTexto();
    }

    private void PegarTexto() {
        Intent intent = getIntent();
        String textrcv = intent.getStringExtra(Intent.EXTRA_TEXT);
        texto2.setText(textrcv);
    }

}