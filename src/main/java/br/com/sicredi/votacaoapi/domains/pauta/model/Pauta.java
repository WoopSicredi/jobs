package br.com.sicredi.votacaoapi.domains.pauta.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ruddy Paz
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pauta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL)
	private Set<Voto> votos = new HashSet<>();

	@Column(nullable = false)
	@Size(max = 100)
	private String nome;

	@Column(name = "inicio_votacao")
	private LocalDateTime inicioVotacao;

	@Column(name = "fim_votacao")
	private LocalDateTime fimVotacao;

	public Pauta(String nome) {
		this.nome = nome;
	}

}
