package br.com.sicredi.votacao.util;

public final class CalculoUtils {

	public static final int ZERO = 0;
	public static final int CEM = 100;
	
	private CalculoUtils() {
		throw new UnsupportedOperationException();
	}
	
	// TODO implementar testes
	public static final double percentual(long valor, long total) {
		if (total <= ZERO) {
			return ZERO;
		}
		return valor * CEM / total;
	}
	
}