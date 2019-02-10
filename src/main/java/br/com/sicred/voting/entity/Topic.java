package br.com.sicred.voting.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pauta")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of={"id"})
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="descricao")
    private String description;
}
