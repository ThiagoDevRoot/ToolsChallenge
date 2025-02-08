package com.tools.java_challenge.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Transacao {
    private String cartao;
    private String id;
    private Descricao descricao;
    private FormaPagamento formaPagamento;

}