package br.com.sicredi.votacaoapi.domains.pauta.model.validations;

public enum TipoDeResultadoDaVotacao {
	
	SIM,
	NAO,
	EMPATE,
	SEM_VOTOS;
	
	public static TipoDeResultadoDaVotacao deAcordoComAQuantidadeDeVotos(Integer quantidadeDeVotos) {
		if (quantidadeDeVotos == null) {
			return SEM_VOTOS;
		} else if (quantidadeDeVotos == 0) {
			return EMPATE;
		} else if (quantidadeDeVotos < 0) {
			return NAO;
		}
		return SIM;
	}


}
