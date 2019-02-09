package br.com.sicred.votacao_pauta.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "secao_votacao")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of={"id"})
public class SecaoVotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="data_abertura")
    private LocalDateTime dataAbertura;
    @Column(name="data_fechamento")
    private LocalDateTime dataFechamento;
    @ManyToOne
    @JoinColumn(columnDefinition = "id_pauta", referencedColumnName = "id")
    private Pauta pauta;
}
