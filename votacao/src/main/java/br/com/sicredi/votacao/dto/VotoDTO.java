package br.com.sicredi.votacao.dto;

import java.util.Objects;
import java.util.Optional;

import br.com.sicredi.votacao.model.ValorVoto;
import br.com.sicredi.votacao.model.Voto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VotoDTO {
	
	private Long id;
	private ValorVoto valorVoto;
	private SessaoDTO sessaoDTO;
	private AssociadoDTO associadoDTO;
	
	public static VotoDTO parseDTO(Voto voto) {
		if (Objects.isNull(voto)) {
			return null;
		}
		return VotoDTO.builder()
				.id(voto.getId())
				.valorVoto(voto.getValorVoto())
				.sessaoDTO(Optional.ofNullable(SessaoDTO.parseDTO(voto.getSessao())).orElse(null))
				.associadoDTO(Optional.ofNullable(AssociadoDTO.parseDTO(voto.getAssociado())).orElse(null))
				.build();
	}

}
