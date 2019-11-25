package br.com.sicredi.votacaoapi.application.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

	public ResponseEntity<?> post(String url, Class<?> responseType) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			return restTemplate.getForEntity(url, responseType);
		} catch (HttpClientErrorException e) {
			return null;
		}
		
	}
	
}
