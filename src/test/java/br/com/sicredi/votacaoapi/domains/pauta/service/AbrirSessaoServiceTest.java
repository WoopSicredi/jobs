package br.com.sicredi.votacaoapi.domains.pauta.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sicredi.votacaoapi.application.error.MensagemValidacaoVotacaoEnum;
import br.com.sicredi.votacaoapi.application.error.RecursoNaoEncontradoException;
import br.com.sicredi.votacaoapi.domains.pauta.dto.AbrirSessaoRequest;
import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AbrirSessaoServiceTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private AbrirSessaoService abrirSessaoService;

	@Test
	public void abrirSessaoDeveriaPersistirDadosERetornarCodigoDeStatus200Ok() {
		Pauta pauta = new Pauta();
		pauta.setId(1L);
		pauta.abrirSessao(null);
		BDDMockito.when(abrirSessaoService.abrirSessao(pauta.getId(), null)).thenReturn(pauta);
		AbrirSessaoRequest request = new AbrirSessaoRequest(pauta.getId(), null);

		HttpEntity<AbrirSessaoRequest> requestUpdate = new HttpEntity<>(request, new HttpHeaders());
		ResponseEntity<String> response = restTemplate.exchange("/pauta/abrirSessao/", HttpMethod.PUT, requestUpdate,
				String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void abrirSessaoQuandoPautaENuloDeveriaRetornarCodigoDeStatus400BadRequest() {
		AbrirSessaoRequest request = new AbrirSessaoRequest(null, null);
		ResponseEntity<String> response = restTemplate.exchange(
			"/pauta/abrirSessao/", 
			HttpMethod.PUT,
			new HttpEntity<>(request, new HttpHeaders()), 
			String.class
		);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void abrirSessaoQuandoCodigoDaPautaENegativaDeveriaRetornarCodigoDeStatus400BadRequest() {
		AbrirSessaoRequest request = new AbrirSessaoRequest(-1L, null);
		ResponseEntity<String> response = restTemplate.exchange(
			"/pauta/abrirSessao/", 
			HttpMethod.PUT,
			new HttpEntity<>(request, new HttpHeaders()), 
			String.class
		);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void abrirSessaoQuandoPautaNaoExisteDeveriaRetornarCodigoDeStatus404NotFound() {
		BDDMockito.when(abrirSessaoService.abrirSessao(1L, null)).thenThrow(new RecursoNaoEncontradoException(MensagemValidacaoVotacaoEnum.PAUTA_NAO_EXISTE));
		AbrirSessaoRequest request = new AbrirSessaoRequest(1L, null);
		HttpEntity<AbrirSessaoRequest> requestUpdate = new HttpEntity<>(request, new HttpHeaders());
		ResponseEntity<String> response = restTemplate.exchange("/pauta/abrirSessao/", HttpMethod.PUT, requestUpdate,
				String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	public void abrirSessaoQuandoDuracaoEmMinutosENegativoDeveriaRetornarCodigoDeStatus400BadRequest() {
		AbrirSessaoRequest request = new AbrirSessaoRequest(1L, -1L);
		ResponseEntity<String> response = restTemplate.exchange(
			"/pauta/abrirSessao/", 
			HttpMethod.PUT,
			new HttpEntity<>(request, new HttpHeaders()), 
			String.class
		);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

}
