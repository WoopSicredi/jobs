package br.com.sicredi.votacaoapi.application.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

public class MessagesProperty {

	/**
	 * Nome do arquivo de properties.
	 */
	public static final ResourceBundle DEFAULT_RESOURCE_BUNDLE = ResourceBundle
			.getBundle("messages");

	private MessagesProperty() {
		super();
	}

	public static String getMessage(String key, String... params) {
		return getMessage(DEFAULT_RESOURCE_BUNDLE, key);
	}

	public static String getMessage(ResourceBundle bnd, String codMsg, String... params) {
		String retorno = null;
		if (bnd != null && codMsg != null) {
			boolean existeMsg = bnd.containsKey(codMsg);
			while (existeMsg) {
				retorno = bnd.getString(codMsg.toString());
				if (StringUtils.isNotEmpty(retorno)) {
					if (retorno.startsWith("${") && retorno.trim().endsWith("}")) {
						codMsg = retorno.replaceFirst("\\$", "").replaceFirst("\\{", "").replaceFirst("\\}", "").trim();
						existeMsg = bnd.containsKey(codMsg);
					} else {
						existeMsg = false;
					}
				} else {
					existeMsg = false;
				}
			}
		}
		return getTextMessageReplace(retorno, params);
	}

	private static String getTextMessageReplace(String descMsg, String... params) {
		if (descMsg != null && params != null) {
			MessageFormat mf = new MessageFormat(descMsg);
			descMsg = mf.format(params, new StringBuffer(), null).toString();
		}
		return descMsg;
	}
	
}
