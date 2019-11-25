package br.com.sicredi.votacaoapi.domains.pauta.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.votacaoapi.application.error.MensagemValidacaoVotacaoEnum;
import br.com.sicredi.votacaoapi.application.error.RecursoNaoEncontradoException;
import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;
import br.com.sicredi.votacaoapi.domains.pauta.repository.PautaRepository;

@Service
public class RecuperarPautaService {

	private PautaRepository pautaRepository;

	@Autowired
	public RecuperarPautaService(PautaRepository pautaRepository) {
		this.pautaRepository = pautaRepository;
	}

	public Pauta retornarPautaPorId(Long pautaId) {
		Optional<Pauta> optTopic = pautaRepository.findById(pautaId);
		return optTopic.map(t -> t)
				.orElseThrow(() -> new RecursoNaoEncontradoException(MensagemValidacaoVotacaoEnum.PAUTA_NAO_EXISTE));
	}

}
