package com.fetin.calendarone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri ident = null;
        Button b1 = findViewById(R.id.botaoSelect);
        Button b2 = findViewById(R.id.botaoAbrir);
        FloatingActionButton b3 = findViewById(R.id.BotFlut);
        BancoDados db = new BancoDados(this);
        b1.setOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date picker");
        });
        b2.setOnClickListener(v -> abrirArquivo(ident));
        b3.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CriarPessoas.class);
            startActivity(intent);
        });
        db.adicionarPessoa(new ModeloPessoas("Joao"));
        Toast ps = Toast.makeText(MainActivity.this, "Pessoa salva!", Toast.LENGTH_LONG);
        ps.show();
        }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String DiaAtual = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView texto = findViewById(R.id.MudaData);
        texto.setText(DiaAtual);
    }

    // Requisita acesso ao txt
    private static final int PICK_TXT_FILE = 2;

    private void abrirArquivo(Uri pickerInitialUri) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/txt");
        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri);
        startActivityForResult(intent, PICK_TXT_FILE);
    }
}