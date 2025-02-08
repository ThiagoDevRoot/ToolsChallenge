package com.tools.java_challenge.mapper;

import com.tools.java_challenge.model.Pagamento;
import com.tools.java_challenge.request.PagamentoRequest;
import com.tools.java_challenge.response.PagamentoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PagamentoMapper {

    PagamentoMapper INSTANCE = Mappers.getMapper(PagamentoMapper.class);

    @Mappings({
            @Mapping(target = "transacao.cartao", source = "transacaoRequest.cartao"),
            @Mapping(target = "transacao.id", source = "transacaoRequest.id"),
            @Mapping(target = "transacao.descricao.valor", source = "transacaoRequest.descricaoRequest.valor"),
            @Mapping(target = "transacao.descricao.dataHora", source = "transacaoRequest.descricaoRequest.dataHora"),
            @Mapping(target = "transacao.descricao.estabelecimento", source = "transacaoRequest.descricaoRequest.estabelecimento"),
            @Mapping(target = "transacao.descricao.nsu", source = "transacaoRequest.descricaoRequest.nsu"),
            @Mapping(target = "transacao.descricao.codigoAutorizacao", source = "transacaoRequest.descricaoRequest.codigoAutorizacao"),
            @Mapping(target = "transacao.descricao.status", source = "transacaoRequest.descricaoRequest.status"),
            @Mapping(target = "transacao.formaPagamento.tipo", source = "transacaoRequest.formaPagamentoRequest.tipo"),
            @Mapping(target = "transacao.formaPagamento.parcelas", source = "transacaoRequest.formaPagamentoRequest.parcelas")
    })
    Pagamento toDomain(PagamentoRequest pagamentoRequest);


    @Mappings({
            @Mapping(target = "transacaoResponse.cartao", source = "transacao.cartao"),
            @Mapping(target = "transacaoResponse.id", source = "transacao.id"),
            @Mapping(target = "transacaoResponse.descricaoResponse.valor", source = "transacao.descricao.valor"),
            @Mapping(target = "transacaoResponse.descricaoResponse.dataHora", source = "transacao.descricao.dataHora"),
            @Mapping(target = "transacaoResponse.descricaoResponse.estabelecimento", source = "transacao.descricao.estabelecimento"),
            @Mapping(target = "transacaoResponse.descricaoResponse.nsu", source = "transacao.descricao.nsu"),
            @Mapping(target = "transacaoResponse.descricaoResponse.codigoAutorizacao", source = "transacao.descricao.codigoAutorizacao"),
            @Mapping(target = "transacaoResponse.descricaoResponse.status", source = "transacao.descricao.status"),
            @Mapping(target = "transacaoResponse.formaPagamentoResponse.tipo", source = "transacao.formaPagamento.tipo"),
            @Mapping(target = "transacaoResponse.formaPagamentoResponse.parcelas", source = "transacao.formaPagamento.parcelas")
    })
    PagamentoResponse toResponse(Pagamento pagamento);
}