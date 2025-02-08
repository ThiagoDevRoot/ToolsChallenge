package com.tools.java_challenge.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransacaoRequest {

    @JsonProperty("cartao")
    private String cartao;
    @JsonProperty("id")
    private String id;
    @JsonProperty("descricao")
    private DescricaoRequest descricaoRequest;
    @JsonProperty("formaPagamento")
    private FormaPagamentoRequest formaPagamentoRequest;

}