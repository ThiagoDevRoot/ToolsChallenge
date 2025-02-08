package com.tools.java_challenge.serviceTest;

import com.tools.java_challenge.model.Descricao;
import com.tools.java_challenge.model.FormaPagamento;
import com.tools.java_challenge.model.Pagamento;
import com.tools.java_challenge.model.Transacao;
import com.tools.java_challenge.service.PagamentoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class PagamentoServiceTest {

    @InjectMocks
    private PagamentoService pagamentoService;

    @Test
    public void testProcessarPagamento() {

        Pagamento pagamentoProcessado = pagamentoService.processarPagamento(criarPagamento());
        assertNotNull(pagamentoProcessado);
        assertEquals("1", pagamentoProcessado.getTransacao().getId());
        assertEquals(String.valueOf(new BigDecimal("10.50")), pagamentoProcessado.getTransacao().getDescricao().getValor());
        assertEquals(Descricao.Status.AUTORIZADO, pagamentoProcessado.getTransacao().getDescricao().getStatus());
    }


    @Test
    public void testEstornarPagamento() {

        Pagamento pagamentoProcessado = pagamentoService.processarPagamento(criarPagamento());

        pagamentoProcessado = pagamentoService.estornarPagamento(criarPagamento().getTransacao().getId());

        assertNotNull(pagamentoProcessado);
        assertEquals("CANCELADO", pagamentoProcessado.getTransacao().getDescricao().getStatus().toString());
    }


    @Test
    public void testConsultarPagamentoPorId() {
        Pagamento pagamentoProcessado = pagamentoService.processarPagamento(criarPagamento());

        pagamentoProcessado = pagamentoService.consultarPagamentoPorId(criarPagamento().getTransacao().getId());

        assertNotNull(pagamentoProcessado);
        assertEquals("AUTORIZADO", pagamentoProcessado.getTransacao().getDescricao().getStatus().toString());
    }

    @Test
    public void testConsultarTodosPagamentos() {
        Pagamento pagamentoProcessado = pagamentoService.processarPagamento(criarPagamento());

        List<Pagamento> pagamentosProcessadosList = new ArrayList<>();
        pagamentosProcessadosList.add(pagamentoProcessado);

        pagamentosProcessadosList = pagamentoService.consultarTodosPagamentos();

        assertNotNull(pagamentosProcessadosList);
    }

    private Pagamento criarPagamento() {
        Descricao descricao = new Descricao();
        descricao.setValor(String.valueOf(new BigDecimal("10.50")));
        descricao.setDataHora(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        descricao.setEstabelecimento("Loja Teste");
        descricao.setNsu("1234567890");
        descricao.setCodigoAutorizacao("ABCDEF");
        descricao.setStatus(Descricao.Status.AUTORIZADO);

        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setTipo(FormaPagamento.TipoPagamento.AVISTA);
        formaPagamento.setParcelas("1");

        Transacao transacao = new Transacao();
        transacao.setId("1");
        transacao.setCartao("4444********1234");
        transacao.setDescricao(descricao);
        transacao.setFormaPagamento(formaPagamento);

        Pagamento pagamento = new Pagamento();
        pagamento.setTransacao(transacao);
        return pagamento;
    }

}




