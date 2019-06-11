package br.com.sicredi.votacao.dto.requestbody;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.sicredi.votacao.model.Associado;
import br.com.sicredi.votacao.model.Sessao;
import br.com.sicredi.votacao.model.ValorVoto;
import br.com.sicredi.votacao.model.Voto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VotoSaveDTO implements Serializable {

	private static final long serialVersionUID = -8740027801916155561L;

	@NotNull(message = "{validation.voto.requiredIdSessao}")
	private Long idSessao;

	@NotNull(message = "{validation.voto.requiredIdAssociado}")
	private Long idAssociado;

	@NotNull(message = "{validation.voto.requiredValue}")
	private ValorVoto valorVoto;

	public Voto toModel() {
		return Voto.builder()
				.sessao(Sessao.builder().id(idSessao).build())
				.associado(Associado.builder().id(idAssociado).build())
				.valorVoto(valorVoto)
				.build();
	}

}