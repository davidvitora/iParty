package com.iparty.model;

/**
 * Created by Luiz Alexandre on 01/11/2018
 */
public class Guests {
    private int id;
    private String nome;
    private int telefone;
    private int idade;

    public Guests(final int id, final String nome, final int telefone, final int idade) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
