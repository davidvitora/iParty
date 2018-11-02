package com.iparty.model;

public class Bebida {
    private String nome;
    private Float preco_por_litro;
    private boolean alcoolica;
    private Float quantiodade_por_pessoa;

    public Bebida(String nome, Float preco_por_litro, boolean alcoolica) {
        this.nome = nome;
        this.preco_por_litro = preco_por_litro;
        this.alcoolica = alcoolica;
        this.quantiodade_por_pessoa = 1.0f;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPreco_por_litro() {
        return preco_por_litro;
    }

    public void setPreco_por_litro(Float preco_por_litro) {
        this.preco_por_litro = preco_por_litro;
    }

    public boolean isAlcoolica() {
        return alcoolica;
    }

    public void setAlcoolica(boolean alcoolica) {
        this.alcoolica = alcoolica;
    }

    public Float getQuantiodade_por_pessoa() {
        return quantiodade_por_pessoa;
    }

    public void setQuantiodade_por_pessoa(Float quantiodade_por_pessoa) {
        this.quantiodade_por_pessoa = quantiodade_por_pessoa;
    }
}
