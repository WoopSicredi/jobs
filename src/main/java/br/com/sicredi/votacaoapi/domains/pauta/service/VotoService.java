package br.com.sicredi.votacaoapi.domains.pauta.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.votacaoapi.application.error.BusinessException;
import br.com.sicredi.votacaoapi.application.error.MensagemValidacaoVotacaoEnum;
import br.com.sicredi.votacaoapi.domains.pauta.dto.Associado;
import br.com.sicredi.votacaoapi.domains.pauta.dto.VotoResponse;
import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;
import br.com.sicredi.votacaoapi.domains.pauta.model.Voto;
import br.com.sicredi.votacaoapi.domains.pauta.model.validations.AssociateStatus;
import br.com.sicredi.votacaoapi.domains.pauta.repository.VotoRepository;

@Service
public class VotoService {
	
	private VotoRepository votoRepository;

	private RecuperarPautaService recuperarPautaService;
	
	private ChecarStatusAssociadoService checarStatusAssociadoService;
	
	@Autowired
	public VotoService(RecuperarPautaService recuperarPautaService, ChecarStatusAssociadoService checkAssociateStatusService, VotoRepository votoRepository) {
		this.recuperarPautaService = recuperarPautaService;
		this.checarStatusAssociadoService = checkAssociateStatusService;
		this.votoRepository = votoRepository;
	}

	public VotoResponse votar(Long pautaId, Associado associado, Boolean decisao) {
		Long usuarioId = associado.getId();
		Pauta pauta = recuperarPautaService.retornarPautaPorId(pautaId);		
		Optional<Voto> optVote = pauta.getVotos().stream().filter(v -> usuarioId.equals(v.getUsuarioId())).findFirst();
		if (optVote.isPresent()) {
			throw new BusinessException(MensagemValidacaoVotacaoEnum.ASSOCIADO_JA_VOTOU_NESTA_PAUTA);
		} else if (!pauta.temSessaoAtiva()) {
			throw new BusinessException(MensagemValidacaoVotacaoEnum.SOMENTE_SESSOES_ATIVAS_ESTAO_APTAS_A_RECEBEREM_VOTOS);
		} else if (checarStatusAssociadoService.recuperarStatus(associado) == AssociateStatus.UNABLE_TO_VOTE) {
			throw new BusinessException(MensagemValidacaoVotacaoEnum.ASSOCIADO_IMPEDIDO_DE_VOTAR);
		}
		
		Voto vote = votoRepository.save(new Voto(pauta, usuarioId, decisao));
		return new VotoResponse(pauta, associado, vote.getDecisao());
	}
	
}
