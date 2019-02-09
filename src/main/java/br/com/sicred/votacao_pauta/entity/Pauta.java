package br.com.sicred.votacao_pauta.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pauta")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of={"id"})
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String descricao;
}
