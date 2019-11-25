package br.com.sicredi.votacaoapi.application.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DetalhesDoErro {
	
	private String titulo;
	private int status;
	private String detalhes;
	private long timestamp;
	private String mensagemParaDesenvolvedor;
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static final class Builder {

		private String titulo;
		private int status;
		private String detalhes;
		private long timestamp;
		private String mensagemParaDesenvolvedor;

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

		public DetalhesDoErro constroi() {
			return new DetalhesDoErro(titulo, status, detalhes, timestamp, mensagemParaDesenvolvedor);
		}

	}

}
