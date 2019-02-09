package br.com.sicred.voting.entity;

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
public class VotingSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="data_abertura")
    private LocalDateTime openingDate;
    @Column(name="data_fechamento")
    private LocalDateTime closingDate;
    @ManyToOne
    @JoinColumn(columnDefinition = "id_pauta", referencedColumnName = "id")
    private Topic topic;
}
