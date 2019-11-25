package br.com.sicredi.votacaoapi.domains.pauta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sicredi.votacaoapi.application.service.RestTemplateService;
import br.com.sicredi.votacaoapi.domains.pauta.dto.Associado;
import br.com.sicredi.votacaoapi.domains.pauta.dto.ValidationsAssociateResponse;
import br.com.sicredi.votacaoapi.domains.pauta.model.validations.AssociateStatus;

@Service
public class ChecarStatusAssociadoService {

	@Value(value = "${url.validations.associate}")
	private String urlValidationsAssociate;

	private RestTemplateService restTemplateService;

	@Autowired
	public ChecarStatusAssociadoService(RestTemplateService restTemplateService) {
		this.restTemplateService = restTemplateService;
	}

	public AssociateStatus checkAssociateStatus(Associado associado) {
		String cpf = associado.getCpf();
		if (cpf == null || cpf.length() == 0) {
			return AssociateStatus.ABLE_TO_VOTE;
		}

		String url = String.format("%s/users/%s", urlValidationsAssociate, cpf);
		ResponseEntity<ValidationsAssociateResponse> response = (ResponseEntity<ValidationsAssociateResponse>) restTemplateService
				.post(url, ValidationsAssociateResponse.class);
		
		return response != null && response.getStatusCodeValue() == 200
				? AssociateStatus.fromStatus(response.getBody().getStatus())
				: AssociateStatus.ABLE_TO_VOTE;
	}

}
