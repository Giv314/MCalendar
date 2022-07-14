package com.fetin.calendarone;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Conversas extends AppCompatActivity {

    private TextView texto2;
    private static final int READ_REQUEST_CODE = 42;
    String TextoFinal;
    FloatingActionButton botaoMandar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversas);
        texto2 = findViewById(R.id.TextoRecebido);
        texto2.setMovementMethod(new ScrollingMovementMethod());
        botaoMandar = findViewById(R.id.enviarBotao);

        BuscarArquivo();
        botaoMandar.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(), SelecionarPessoa.class);
            intent2.putExtra("sharedText", TextoFinal);
            startActivity(intent2);
        });
    }
    // Ler conteudo do txt
    private String lerTexto(String input){
        File arquivo = new File(Environment.getExternalStorageDirectory(), input);
        StringBuilder texto = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;
            while((linha = br.readLine()) != null){
                texto.append(linha);
                texto.append("\n");
            }
            br.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        return texto.toString();
    }

    private void BuscarArquivo(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                String path = uri.getPath();
                path = path.substring(path.indexOf(":") + 1);
                if(path.contains("emulated")) {
                    path = path.substring(path.indexOf("0") + 1);
                }
                TextoFinal = lerTexto(path);
                texto2.setText(TextoFinal);
            }
        }
    }
    public String lerTexto2(File sharedFile){
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


}