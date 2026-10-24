package org.example.desafioitau.Entity;

import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transacaos")
public class transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private OffsetDateTime dataHora;

}
