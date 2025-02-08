package com.tools.java_challenge.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamento {

    public enum TipoPagamento {
        AVISTA,
        PARCELADO_EMISSOR,
        PARCELADO_LOJA
    }

    private TipoPagamento tipo;
    private String parcelas;
}