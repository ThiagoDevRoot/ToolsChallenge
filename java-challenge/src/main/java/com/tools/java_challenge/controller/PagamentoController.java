package com.tools.java_challenge.controller;

import com.tools.java_challenge.exception.TransacaoNotFoundException;
import com.tools.java_challenge.mapper.PagamentoMapper;
import com.tools.java_challenge.model.Pagamento;
import com.tools.java_challenge.request.PagamentoRequest;
import com.tools.java_challenge.response.PagamentoResponse;
import com.tools.java_challenge.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoResponse> efetuarPagamento(@RequestBody PagamentoRequest pagamentoRequest) {
        Pagamento transacaoProcessada = pagamentoService.processarPagamento(PagamentoMapper.INSTANCE.toDomain(pagamentoRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(PagamentoMapper.INSTANCE.toResponse(transacaoProcessada));
    }

    @GetMapping("/estorno/{id}")
    public ResponseEntity<?> estornarPagamento(@PathVariable String id) {

        try {
            Pagamento pagamentoEstornado = pagamentoService.estornarPagamento(id);
            return ResponseEntity.ok(PagamentoMapper.INSTANCE.toResponse(pagamentoEstornado));
        } catch (TransacaoNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/consulta/{id}")
    public ResponseEntity<?> consultarPagamentoPorId(@PathVariable String id) {

        try {
            Pagamento pagamentoConsultado = pagamentoService.consultarPagamentoPorId(id);
            return ResponseEntity.ok(PagamentoMapper.INSTANCE.toResponse(pagamentoConsultado));
        } catch (TransacaoNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/consulta")
    public ResponseEntity<List<PagamentoResponse>> consultarPagamentos() {
        List<Pagamento> pagamentos = pagamentoService.consultarTodosPagamentos();

        try {
            List<PagamentoResponse> pagamentoResponse = pagamentos.stream()
                    .map(PagamentoMapper.INSTANCE::toResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(pagamentoResponse);

        } catch (TransacaoNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
