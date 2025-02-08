package com.tools.java_challenge.service;

import com.tools.java_challenge.exception.TransacaoNotFoundException;
import com.tools.java_challenge.model.Descricao;
import com.tools.java_challenge.model.Pagamento;
import com.tools.java_challenge.model.Transacao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PagamentoService {


    private List<Pagamento> transacoes = new ArrayList<>();
    private Random random = new Random();

    public Pagamento processarPagamento(Pagamento pagamento) {
        Transacao transacao = pagamento.getTransacao();

        String nsu = String.valueOf(1000000000L + random.nextInt(900000000));
        transacao.getDescricao().setNsu(nsu);

        String codigoAutorizacao = String.valueOf(random.nextInt(900000000));
        transacao.getDescricao().setCodigoAutorizacao(codigoAutorizacao);


        transacao.getDescricao().setStatus(Descricao.Status.AUTORIZADO);

        transacoes.add(pagamento);
        return pagamento;
    }

    public Pagamento estornarPagamento(String id) {
        Pagamento pagamento = transacoes.stream()
                .filter(p -> p.getTransacao().getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TransacaoNotFoundException("Transação não encontrada"));

        pagamento.getTransacao().getDescricao().setStatus(Descricao.Status.CANCELADO);
        return pagamento;
    }


    public Pagamento consultarPagamentoPorId(String id) {
        return transacoes.stream()
                .filter(p -> p.getTransacao().getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TransacaoNotFoundException("Transação não encontrada"));

    }

    public List<Pagamento> consultarTodosPagamentos() {
        return transacoes;
    }

}
