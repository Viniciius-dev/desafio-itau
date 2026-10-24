package org.example.desafioitau.Controller;

import org.example.desafioitau.Dto.EstatisticaResponse;
import org.example.desafioitau.Entity.transacao;
import org.example.desafioitau.Service.transacaoService;
import org.example.desafioitau.repository.transacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estatistica")
public class transacaoController {
    @Autowired
    private transacaoService transacaoService;

    @Autowired
    private transacaoRepository transacaoRepository;
    @GetMapping()
    public EstatisticaResponse estatistica(){
        return transacaoService.estatisticas();
    }

    @PostMapping()
    public ResponseEntity<transacao> saveTransacao(@RequestBody transacao transacao){
        transacao = transacaoService.save(transacao);

        return new ResponseEntity<>(transacao, HttpStatus.CREATED);
    }
}
