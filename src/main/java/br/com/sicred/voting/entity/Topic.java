package br.com.sicred.voting.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "topic")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of={"id"})
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;
}
