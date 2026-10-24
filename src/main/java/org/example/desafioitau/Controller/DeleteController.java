package org.example.desafioitau.Controller;

import org.example.desafioitau.Dto.EstatisticaResponse;
import org.example.desafioitau.Service.transacaoService;
import org.example.desafioitau.repository.transacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Delete")
public class DeleteController {
    @Autowired
    private transacaoService transacaoService;

    private final transacaoRepository transacaoRepository;

    private final transacaoController transacaoController;

    public DeleteController(transacaoRepository transacaoRepository, transacaoController transacaoController) {
        this.transacaoRepository = transacaoRepository;
        this.transacaoController = transacaoController;
    }


    @GetMapping()
    public EstatisticaResponse estatistica(){
        return transacaoService.estatisticas();
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteById(@RequestParam Long id){
        transacaoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
