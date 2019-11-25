package br.com.sicredi.votacaoapi.application.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class DetalhesDoErroDeValidacao extends DetalhesDoErro {

	private String campo;
	private String mensagemDoCampo;

	private DetalhesDoErroDeValidacao(String titulo, int status, String detalhes, long timestamp, String mensagemParaDesenvolvedor,
			String campo, String mensagemDoCampo) {
		super(titulo, status, detalhes, timestamp, mensagemParaDesenvolvedor);
		this.campo = campo;
		this.mensagemDoCampo = mensagemDoCampo;
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static final class Builder {

		private String titulo;
		private int status;
		private String detalhes;
		private long timestamp;
		private String mensagemParaDesenvolvedor;
		private String campo;
		private String mensagemDoCampo;

		public static Builder newBuilder() {
			return new Builder();
		}

		public Builder comTitulo(String titulo) {
			this.titulo = titulo;
			return this;
		}

		public Builder comStatus(int status) {
			this.status = status;
			return this;
		}

		public Builder comDetalhes(String detalhes) {
			this.detalhes = detalhes;
			return this;
		}

		public Builder comTimestamp(long timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public Builder comMensagemParaDesenvolvedor(String mensagemParaDesenvolvedor) {
			this.mensagemParaDesenvolvedor = mensagemParaDesenvolvedor;
			return this;
		}

		public Builder comCampo(String campo) {
			this.campo = campo;
			return this;
		}

		public Builder comMensagemDoCampo(String mensagemDoCampo) {
			this.mensagemDoCampo = mensagemDoCampo;
			return this;
		}

		public DetalhesDoErroDeValidacao constroi() {
			return new DetalhesDoErroDeValidacao(titulo, status, detalhes, timestamp, mensagemParaDesenvolvedor, campo, mensagemDoCampo);
		}

	}

}
