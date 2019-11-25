package br.com.sicredi.votacaoapi.domains.pauta.model.validations;

import lombok.Getter;

@Getter
public enum AssociateStatus {

	ABLE_TO_VOTE,
	UNABLE_TO_VOTE;
	
	public static AssociateStatus fromStatus(String status) {
		for(AssociateStatus types : values()) {
			if(types.toString().equals(status)) {
				return types;
			}
		}
		return ABLE_TO_VOTE;
	}
	
}
