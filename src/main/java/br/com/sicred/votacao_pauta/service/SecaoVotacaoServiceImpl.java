package br.com.sicred.votacao_pauta.service;

import br.com.sicred.votacao_pauta.dto.ResultadoSecaoDto;
import br.com.sicred.votacao_pauta.dto.SecaoVotacaoDto;
import br.com.sicred.votacao_pauta.entity.Pauta;
import br.com.sicred.votacao_pauta.entity.SecaoVotacao;
import br.com.sicred.votacao_pauta.entity.Voto;
import br.com.sicred.votacao_pauta.exception.VotacaoAindaAbertaException;
import br.com.sicred.votacao_pauta.exception.VotacaoEncerradaException;
import br.com.sicred.votacao_pauta.exception.VotoPreExistenteException;
import br.com.sicred.votacao_pauta.repository.PautaRepository;
import br.com.sicred.votacao_pauta.repository.SecaoVotacaoRepository;
import br.com.sicred.votacao_pauta.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecaoVotacaoServiceImpl implements SecaoVotacaoService{

    private final PautaRepository pautaRepository;
    private final SecaoVotacaoRepository secaoVotacaoRepository;
    private final VotoRepository votoRepository;

    @Override
    public SecaoVotacao createSecaoVotacao(SecaoVotacaoDto dto) {
        Optional<Pauta> pauta = this.pautaRepository.findById(dto.getIdPauta());
        pauta.orElseThrow(() -> new IllegalArgumentException("Id de pauta inválido"));
        SecaoVotacao secao = SecaoVotacao.builder()
                .pauta(pauta.get())
                .dataAbertura(dto.getDataAbertura())
                .dataFechamento(
                        dto.getDataEncerramento() == null ?
                                dto.getDataAbertura().plus(1, MINUTES) : dto.getDataEncerramento()).build();
        return secaoVotacaoRepository.save(secao);
    }

    @Override
    public void voteForSecao(Long idSecao, Long idParticipante, Boolean voto) {
        Optional<SecaoVotacao> secao = this.secaoVotacaoRepository.findById(idSecao);
        secao.orElseThrow(() -> new IllegalArgumentException("Id de seção inválido"));
        if(secao.get().getDataFechamento() != null && secao.get().getDataFechamento().isBefore(LocalDateTime.now())) {
            throw new VotacaoEncerradaException();
        }
        Optional<Voto> votoExistente = this.votoRepository.findBySecaoAndParticipante(idSecao, idParticipante);
        votoExistente.ifPresent(s -> {throw new VotoPreExistenteException();});
        this.votoRepository.save(
                Voto.builder()
                        .data(LocalDateTime.now())
                        .idParticipante(idParticipante)
                        .secaoVotacao(secao.get())
                        .valor(voto)
                        .build());
    }

    @Override
    public ResultadoSecaoDto getResultadoVotacao(Long idSecao) {
        Optional<SecaoVotacao> secao = this.secaoVotacaoRepository.findById(idSecao);
        secao.orElseThrow(() -> new IllegalArgumentException("Id de seção inválido"));
        if(secao.get().getDataFechamento().isAfter(LocalDateTime.now())) {
            throw new VotacaoAindaAbertaException();
        }
        List<Voto> votos = this.votoRepository.findBySecao(idSecao);
        if(votos.isEmpty()) {
            return ResultadoSecaoDto
                    .builder()
                    .percentualNao(0d)
                    .percentualNao(0d).build();
        }
        List<Voto> votosSim = votos.stream().filter(voto -> voto.getValor()).collect(Collectors.toList());
        double percentualSim = new Double(votosSim.size()) / votos.size() * 100;
        return ResultadoSecaoDto.builder()
                .percentualSim(percentualSim)
                .percentualNao(100-percentualSim)
                .build();
    }


}
