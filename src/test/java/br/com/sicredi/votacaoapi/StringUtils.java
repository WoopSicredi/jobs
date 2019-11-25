package br.com.sicredi.votacaoapi;

import java.nio.CharBuffer;

public final class StringUtils {

	public static final String spaces(int spaces) {
	  return CharBuffer.allocate(spaces).toString().replace('\0', 'C');
	}
	
}
