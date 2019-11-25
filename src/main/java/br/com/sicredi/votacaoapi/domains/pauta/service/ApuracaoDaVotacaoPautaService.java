package br.com.sicredi.votacaoapi.domains.pauta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.votacaoapi.application.error.BusinessException;
import br.com.sicredi.votacaoapi.application.error.MensagemValidacaoVotacaoEnum;
import br.com.sicredi.votacaoapi.domains.pauta.dto.ResultadoVotacaoResponse;
import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;
import br.com.sicredi.votacaoapi.domains.pauta.model.validations.TipoDeResultadoDaVotacao;

@Service
public class ApuracaoDaVotacaoPautaService {

	private RecuperarPautaService recuperarPautaService;

	@Autowired
	public ApuracaoDaVotacaoPautaService(RecuperarPautaService recuperarPautaService) {
		this.recuperarPautaService = recuperarPautaService;
	}

	public ResultadoVotacaoResponse apurar(Long pautaId) {
		Pauta pauta = recuperarPautaService.retornarPautaPorId(pautaId);
		if (!pauta.temSessaoIniciada()) {
			throw new BusinessException(MensagemValidacaoVotacaoEnum.SESSAO_NAO_INICIOU);
		} else if (pauta.temSessaoAtiva()) {
			throw new BusinessException(MensagemValidacaoVotacaoEnum.SESSAO_ESTA_ATIVA);
		}

		TipoDeResultadoDaVotacao votingResult = TipoDeResultadoDaVotacao.deAcordoComAQuantidadeDeVotos(
					pauta.getVotos()
					.stream()
					.map(v -> v.getDecisao() ? 1 : -1)
					.reduce(0, (subtotal, element) -> subtotal + element)
		);

		return new ResultadoVotacaoResponse(pauta, votingResult);
	}

}
