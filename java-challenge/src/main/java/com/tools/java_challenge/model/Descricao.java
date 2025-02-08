package com.tools.java_challenge.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class Descricao {

    public enum Status {
        AUTORIZADO,
        NEGADO,
        CANCELADO
    }

    private String valor;
    private String dataHora;
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;
    private Status status;

}