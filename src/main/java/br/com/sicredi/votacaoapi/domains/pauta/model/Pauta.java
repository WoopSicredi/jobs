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

import br.com.sicredi.votacaoapi.application.error.BusinessException;
import br.com.sicredi.votacaoapi.application.error.MensagemValidacaoVotacaoEnum;
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
	
	public void abrirSessao(Long duracaoEmMinutos) {
		if (temSessaoIniciada()) {
			throw new BusinessException(MensagemValidacaoVotacaoEnum.SESSAO_JA_FOI_INICIADA_EXCEPTION);
		}
		
		inicioVotacao = LocalDateTime.now();
		fimVotacao = inicioVotacao.plusMinutes(duracaoEmMinutos == null ? 1 : duracaoEmMinutos);
	}
	
	public boolean temSessaoAtiva() {
		return temSessaoIniciada() && LocalDateTime.now().isBefore(fimVotacao);
	}
	
	public boolean temSessaoIniciada() {
		return inicioVotacao != null;
	}

}
