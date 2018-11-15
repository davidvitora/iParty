package com.iparty.model;

import com.iparty.enums.Festa;

import java.util.ArrayList;
import java.util.Date;

public class Evento {
    private int id;
    private String titulo;
    private Date data;
    private Festa tipo;
    private String endereço;
    private Double valor;
    private String aniversariante;
    private String noiva;
    private String noivo;
    private ArrayList<Convidado> convidados;

    public Evento(int id, String titulo, Date data, Festa tipo, String endereço, Double valor) {
        this.id = id;
        this.titulo = titulo;
        this.data = data;
        this.tipo = tipo;
        this.endereço = endereço;
        this.valor = valor;
        this.convidados = new ArrayList<Convidado>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Festa getTipo() {
        return tipo;
    }

    public void setTipo(Festa tipo) {
        this.tipo = tipo;
    }

    public String getEndereço() { return endereço; }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public Double getValor() { return valor; }

    public void setValor(Double valor) { this.valor = valor; }

    public String getAniversariante() {
        return aniversariante;
    }

    public void setAniversariante(String aniversariante) {
        this.aniversariante = aniversariante;
    }

    public String getNoiva() {
        return noiva;
    }

    public void setNoiva(String noiva) {
        this.noiva = noiva;
    }

    public String getNoivo() {
        return noivo;
    }

    public void setNoivo(String noivo) {
        this.noivo = noivo;
    }

    public ArrayList<Convidado> getConvidados() {
        return convidados;
    }

    public void setConvidados(ArrayList<Convidado> convidados) {
        this.convidados = convidados;
    }

    public void addConvidado(Convidado convidado) {
        this.convidados.add(convidado);
    }
}
