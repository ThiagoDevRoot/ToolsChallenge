package com.tools.java_challenge.controllerTest;

import com.tools.java_challenge.controller.PagamentoController;
import com.tools.java_challenge.exception.TransacaoNotFoundException;
import com.tools.java_challenge.model.Descricao;
import com.tools.java_challenge.model.FormaPagamento;
import com.tools.java_challenge.model.Pagamento;
import com.tools.java_challenge.model.Transacao;
import com.tools.java_challenge.response.PagamentoResponse;
import com.tools.java_challenge.service.PagamentoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PagamentoControllerTest {

    @InjectMocks
    private PagamentoController pagamentoController;

    @Mock
    private PagamentoService pagamentoService;

    private Pagamento pagamento;

    private PagamentoResponse pagamentoResponse;

    public void setUp() {
        pagamento = criarPagamento();
        pagamentoResponse = criarPagamentoResponse();
    }

    @Test
    public void testEfetuarPagamento() {

        Pagamento pagamento = criarPagamento();
        when(pagamentoService.processarPagamento(any(Pagamento.class))).thenReturn(pagamento);


        ResponseEntity<PagamentoResponse> response = pagamentoController.efetuarPagamento(null);


        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());


        PagamentoResponse pagamentoResponse = response.getBody();
        assertEquals(pagamento.getTransacao().getId(), pagamentoResponse.getTransacaoResponse().getId());
    }

    @Test
    public void testEstornarPagamento() {
        String id = "1";
        when(pagamentoService.estornarPagamento(id)).thenReturn(pagamento);

        ResponseEntity<?> response = pagamentoController.estornarPagamento(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testEstornarPagamentoNaoEncontrado() {
        String id = "1";
        when(pagamentoService.estornarPagamento(id)).thenThrow(TransacaoNotFoundException.class);

        ResponseEntity<?> response = pagamentoController.estornarPagamento(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testConsultarPagamentoPorId() {
        String id = "1";
        when(pagamentoService.consultarPagamentoPorId(id)).thenReturn(pagamento);

        ResponseEntity<?> response = pagamentoController.consultarPagamentoPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testConsultarPagamentos() {
        List<Pagamento> pagamentos = new ArrayList<>();
        pagamentos.add(pagamento);
        when(pagamentoService.consultarTodosPagamentos()).thenReturn(pagamentos);

        ResponseEntity<List<PagamentoResponse>> response = pagamentoController.consultarPagamentos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
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

    private PagamentoResponse criarPagamentoResponse() {
        PagamentoResponse pagamentoResponse = new PagamentoResponse();
        return pagamentoResponse;
    }


}