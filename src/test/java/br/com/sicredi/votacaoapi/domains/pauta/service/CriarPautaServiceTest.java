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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sicredi.votacaoapi.StringUtils;
import br.com.sicredi.votacaoapi.domains.pauta.dto.CriarPautaRequest;
import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CriarPautaServiceTest {

	@Autowired
	private TestRestTemplate restTemplate;

    @MockBean
    private CriarPautaService criarPautaService;

	@Test
	public void createShouldPersistDataAndReturnStatusCode200Ok() {
		Pauta pauta = new Pauta();
		pauta.setId(1L);
		pauta.setNome(StringUtils.spaces(100));
		BDDMockito.when(criarPautaService.criar(StringUtils.spaces(100))).thenReturn(pauta);
		CriarPautaRequest request = CriarPautaRequest.converterEmDTO(pauta);
		ResponseEntity<String> response = restTemplate.postForEntity("/pauta/criar/", request, String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void createWhenNameIsNullShouldReturnStatusCode400BadRequest() {
		Pauta pauta = new Pauta();
		pauta.setId(1L);
		pauta.setNome(null);
		BDDMockito.when(criarPautaService.criar(null)).thenReturn(pauta);
		CriarPautaRequest request = CriarPautaRequest.converterEmDTO(pauta);
		ResponseEntity<String> response = restTemplate.postForEntity("/topic/create/", request, String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void createWhenNameSizeIsLargerThan100ShouldReturnStatusCode400BadRequest() {
		Pauta pauta = new Pauta();
		pauta.setId(1L);
		pauta.setNome(StringUtils.spaces(101));
		BDDMockito.when(criarPautaService.criar(StringUtils.spaces(101))).thenReturn(pauta);
		CriarPautaRequest request = CriarPautaRequest.converterEmDTO(pauta);
		ResponseEntity<String> response = restTemplate.postForEntity("/topic/create/", request, String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

}
