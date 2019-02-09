package br.com.sicred.votacao_pauta.dto;

import br.com.sicred.votacao_pauta.entity.SecaoVotacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoSecaoDto {

    private SecaoVotacao secaoVotacao;
    private Double percentualSim;
    private Double percentualNao;
}
