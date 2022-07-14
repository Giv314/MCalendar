package com.fetin.calendarone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReceberConversas extends AppCompatActivity {
    TextView texto2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversas);

        texto2 = findViewById(R.id.TextoRecebido);
        Intent intent = getIntent();
        String msRec = intent.getStringExtra(Intent.EXTRA_TEXT);
        // String sharedTxt = lerTexto2(sharedFile);
        texto2.setText(msRec);
    }

  /*  public String lerTexto2(File sharedFile){
        StringBuilder texto2 = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(sharedFile));
            String linha;
            while((linha = br.readLine()) != null){
                texto2.append(linha);
                texto2.append("\n");
            }
            br.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        return texto2.toString();
    }

   */

}
