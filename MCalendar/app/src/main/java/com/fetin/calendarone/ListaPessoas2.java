package com.fetin.calendarone;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class ListaPessoas2 extends AppCompatActivity {
    BancoDados db = new BancoDados(getApplication());
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    ListView listarPessoas;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listapessoas);
        ListarPessoas();
    }

    public void ListarPessoas() {
        List<ModeloPessoas> pessoas = db.ListarTodasPessoas();
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(ListaPessoas2.this, android.R.layout.simple_list_item_1, arrayList);
        listarPessoas.setAdapter(adapter);

        for (ModeloPessoas p : pessoas) {
            arrayList.add(p.getNome());
            adapter.notifyDataSetChanged();
        }
    }
}
