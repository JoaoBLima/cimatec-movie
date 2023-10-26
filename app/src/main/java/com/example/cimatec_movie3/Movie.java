package com.example.cimatec_movie3;

import androidx.annotation.NonNull;

public class Movie {
    private String id;
    private String nome;
    private int ano;
    private int curtida;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getCurtida() {
        return curtida;
    }

    public void setCurtida(int curtida) {
        this.curtida = curtida;
    }
    @Override
    @NonNull
    public String toString(){
        return "Nome: " + nome + ", Curtida: " + curtida + ", Ano: " + ano;
    }
}
