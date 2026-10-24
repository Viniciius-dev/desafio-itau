package org.example.desafioitau.Service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Id;
import org.example.desafioitau.Controller.DeleteController;
import org.example.desafioitau.Dto.EstatisticaResponse;
import org.example.desafioitau.Entity.transacao;
import org.example.desafioitau.repository.transacaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;


@Service
public class transacaoService {


    private final DeleteController  deleteController;
    private final transacaoRepository transacaoRepository;


    public transacaoService(DeleteController deleteController, transacaoRepository transacaoRepository) {
        this.deleteController = deleteController;
        this.transacaoRepository = transacaoRepository;
    }


    public List<transacao> findAll() {
        OffsetDateTime limite = OffsetDateTime.now().minusSeconds(60);

        return transacaoRepository.findAll()
                .stream()
                .filter(t -> t.getDataHora().isAfter(limite))
                .toList();
    }

  /*  public DoubleSummaryStatistics estatisticas(){
        OffsetDateTime limite = OffsetDateTime.now().minusSeconds(60);
        return transacaoRepository.findAll().stream()
                .filter(t -> t.getDataHora().isAfter(limite))
                .mapToDouble(t -> t.getValor().doubleValue())
                .summaryStatistics();
    }

    public EstatisticaResponse estatistica(){
        OffsetDateTime limite = OffsetDateTime.now().minusSeconds(60);

        DoubleSummaryStatistics s = transacaoRepository.finAll().stream()
                .mapToDouble(t -> t.getValor().doubleValue())
                .summaryStatistics();
    } */

    public EstatisticaResponse estatisticas(){
        OffsetDateTime limite = OffsetDateTime.now().minusSeconds(60);
        DoubleSummaryStatistics s = transacaoRepository.findAll().stream()
                .filter(t -> t.getDataHora().isAfter(limite))
                .mapToDouble(t -> t.getValor().doubleValue())
                .summaryStatistics();
        if(s.getCount() == 0){
            return new EstatisticaResponse(0,0,0,0,0);
        }

        return new EstatisticaResponse(
                s.getCount(),
                s.getSum(),
                s.getAverage(),
                s.getMax(),
                s.getMin()

        );
    }

    public void delete(Long id){
        if(!transacaoRepository.existsById(id)){
            throw new EntityNotFoundException("transacao nao encontrado" + id);
        }
        transacaoRepository.deleteById(id);
    }

    public transacao save(transacao transacao){
        if(transacao.getValor().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("valor invalido");
        }

        if(transacao.getDataHora().isAfter(OffsetDateTime.now()) || transacao.getDataHora() == null){
            throw new IllegalArgumentException("data hora invalido");
        }
        return  transacaoRepository.save(transacao);
    }
}
