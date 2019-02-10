package br.com.sicred.voting.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "voto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of={"id"})
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="valor")
    private Boolean value;
    @Column(name="data")
    private LocalDateTime date;
    @Column(name="id_participante")
    private Long participantId;
    @ManyToOne
    @JoinColumn(columnDefinition = "id_secao_votacao", referencedColumnName = "id")
    private VotingSection votingSection;

}
