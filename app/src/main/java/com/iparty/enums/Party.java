package com.iparty.enums;


public enum Party {
    ANIVERSARIO(1, "Aniversario"),
    QUINZEANOS(2, "15 Anos"),
    CASAMENTO(3, "Casamento");


    private int codigo;
    private String descricao;

    Party(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public  int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Party toEnum(Party cod) {
        if(cod == null)
            return null;
        for(Party x: Party.values()) {
            if(cod.equals(x.getCodigo()))
                return x;
        }
        throw new IllegalArgumentException("Id inválido " + cod);
    }
}

