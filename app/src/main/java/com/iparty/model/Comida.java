package com.iparty.model;

public class Comida {
    private String nome;
    private Float preco;
    private Float quantiodade_por_pessoa;

    public Comida(String nome, Float preco_por_litro) {
        this.nome = nome;
        this.preco = preco;
        this.quantiodade_por_pessoa = 1.0f;
    }

    public Comida(String nome, Float preco_por_litro, Float quantiodade_por_pessoa) {
        this.nome = nome;
        this.preco = preco;
        this.quantiodade_por_pessoa = quantiodade_por_pessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getQuantiodade_por_pessoa() {
        return quantiodade_por_pessoa;
    }

    public void setQuantiodade_por_pessoa(Float quantiodade_por_pessoa) {
        this.quantiodade_por_pessoa = quantiodade_por_pessoa;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }
}
