package com.tools.java_challenge.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DescricaoResponse {
    @JsonProperty("valor")
    private String valor;
    @JsonProperty("dataHora")
    private String dataHora;
    @JsonProperty("estabelecimento")
    private String estabelecimento;
    @JsonProperty("nsu")
    private String nsu;
    @JsonProperty("codigoAutorizacao")
    private String codigoAutorizacao;
    @JsonProperty("status")
    private String status;
}