package com.fetin.calendarone;

public class ModeloPessoas {
    String nome;
    int codigo;

    public ModeloPessoas(){

    }

    public ModeloPessoas(int _codigo, String _nome){
        this.nome = _nome;
        this.codigo = _codigo;
    }

    public ModeloPessoas(String _nome) {
        this.nome = _nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
