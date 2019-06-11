package br.com.sicredi.votacao.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import br.com.sicredi.votacao.model.Sessao;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessaoDTO implements Serializable {

	private static final long serialVersionUID = -600298867937926578L;
	
	private Long id;
	private LocalDateTime inicio;
	private LocalDateTime fim;
	private PautaDTO pautaDTO;
	
	public static SessaoDTO parseDTO(Sessao sessao) {
		if (Objects.isNull(sessao)) {
			return null;
		}
		return SessaoDTO.builder()
				.id(sessao.getId())
				.inicio(sessao.getInicio())
				.fim(sessao.getFim())
				.pautaDTO(Optional.ofNullable(PautaDTO.parseDTO(sessao.getPauta())).orElse(null))
				.build();
	}
	
}
