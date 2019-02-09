package br.com.sicred.votacao_pauta.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "voto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="valor")
    private Boolean valor;
    @Column(name="data")
    private LocalDateTime data;
    @Column(name="id_participante")
    private Long idParticipante;
    @ManyToOne
    @JoinColumn(columnDefinition = "pauta_id", referencedColumnName = "id")
    private SecaoVotacao secaoVotacao;

}
