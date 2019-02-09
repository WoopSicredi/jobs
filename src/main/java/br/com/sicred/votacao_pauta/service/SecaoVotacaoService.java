package br.com.sicred.votacao_pauta.service;

import br.com.sicred.votacao_pauta.dto.ResultadoSecaoDto;
import br.com.sicred.votacao_pauta.dto.SecaoVotacaoDto;
import br.com.sicred.votacao_pauta.entity.SecaoVotacao;

public interface SecaoVotacaoService {

    SecaoVotacao createSecaoVotacao(SecaoVotacaoDto dto);

    void voteForSecao(Long idSecao, Long idParticipante, Boolean voto);

    ResultadoSecaoDto getResultadoVotacao(Long idSecao);
}
