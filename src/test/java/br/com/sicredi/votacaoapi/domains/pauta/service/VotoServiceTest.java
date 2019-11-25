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

import br.com.sicredi.votacaoapi.application.error.BusinessException;
import br.com.sicredi.votacaoapi.application.error.MensagemValidacaoVotacaoEnum;
import br.com.sicredi.votacaoapi.application.error.RecursoNaoEncontradoException;
import br.com.sicredi.votacaoapi.domains.pauta.dto.Associado;
import br.com.sicredi.votacaoapi.domains.pauta.dto.VotoRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VotoServiceTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private VotoService voteService;

	@MockBean
	private RecuperarPautaService recuperarPautaService;

	@Test
	public void votarDeveriaPersistirDadosERetornarCodigoDeStatus200Ok() {
		VotoRequest request = new VotoRequest(1L, new Associado(1L), true);
		ResponseEntity<String> response = restTemplate.postForEntity("/pauta/votar/", request, String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void votarQuandoPautaENuloDeveriaRetornarCodigoDeStatus400BadRequest() {
		VotoRequest request = new VotoRequest(null, new Associado(1L), true);
		ResponseEntity<String> response = restTemplate.postForEntity("/pauta/votar/", request, String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void votarQuandoCodigoDaPautaENegativaDeveriaRetornarCodigoDeStatus400BadRequest() {
		VotoRequest request = new VotoRequest(-1L, new Associado(1L), true);
		ResponseEntity<String> response = restTemplate.postForEntity("/pauta/votar/", request, String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void votarQuandoAssociadoENuloDeveriaRetornarCodigoDeStatus400BadRequest() {
		VotoRequest request = new VotoRequest(1L, null, true);
		ResponseEntity<String> response = restTemplate.postForEntity("/pauta/votar/", request, String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void votarQuandoAssociadoNaoENuloEOCodidoDoAssociadoENuloDeveriaRetornarCodigoDeStatus400BadRequest() {
		VotoRequest request = new VotoRequest(1L, new Associado(null), true);
		ResponseEntity<String> response = restTemplate.postForEntity("/pauta/votar/", request, String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void votarQuandoAssociadoNaoENuloEOCodidoDoAssociadoENegativoDeveriaRetornarCodigoDeStatus400BadRequest() {
		VotoRequest request = new VotoRequest(1L, new Associado(-1L), true);
		ResponseEntity<String> response = restTemplate.postForEntity("/pauta/votar/", request, String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void votarQuandoAssociadoNaoENuloECPFContemLetrasDeveriaRetornarCodigoDeStatus400BadRequest() {
		VotoRequest request = new VotoRequest(1L, new Associado(1L, "ABCDEFGHIJL"), true);
		ResponseEntity<String> response = restTemplate.postForEntity("/pauta/votar/", request, String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void votarQuandoAssociadoNaoENuloECPFSem11DigitosDeveriaRetornarCodigoDeStatus400BadRequest() {
		VotoRequest request = new VotoRequest(1L, new Associado(1L, "012345"), true);
		ResponseEntity<String> response = restTemplate.postForEntity("/pauta/votar/", request, String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void votarQuandoAssociadoNaoENuloECPFVazioDeveriaRetornarCodigoDeStatus400BadRequest() {
		VotoRequest request = new VotoRequest(1L, new Associado(1L, ""), true);
		ResponseEntity<String> response = restTemplate.postForEntity("/pauta/votar/", request, String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void votarQuandoDecisaoENuloDeveriaRetornarCodigoDeStatus400BadRequest() {
		VotoRequest request = new VotoRequest(1L, new Associado(1L), null);
		ResponseEntity<String> response = restTemplate.postForEntity("/pauta/votar/", request, String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void votarQuandoPautaNaoExisteDeveriaRetornarCodigoDeStatus404ResourceNotFound() {
		Associado mockAssociado = new Associado(1L);
		BDDMockito.when(voteService.votar(1L, mockAssociado, true)).thenThrow(new RecursoNaoEncontradoException(MensagemValidacaoVotacaoEnum.PAUTA_NAO_EXISTE));
		ResponseEntity<String> response = restTemplate.postForEntity("/pauta/votar/", new VotoRequest(1L, mockAssociado, true), String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	public void votarQuandoAssociadoJaTiverVotadoNaPautaDeveriaRetornarCodigoDeStatus400BadRequest() {
		Associado mockAssociado = new Associado(1L);
		BDDMockito.when(voteService.votar(1L, mockAssociado, true)).thenThrow(new BusinessException(MensagemValidacaoVotacaoEnum.ASSOCIADO_JA_VOTOU_NESTA_PAUTA));
		ResponseEntity<String> response = restTemplate.postForEntity("/pauta/votar/", new VotoRequest(1L, mockAssociado, true), String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void votarQuandoSessaoNaoEstaAtivaDeveriaRetornarCodigoDeStatus400BadRequest() {
		Associado mockAssociado = new Associado(1L);
		BDDMockito.when(voteService.votar(1L, mockAssociado, true)).thenThrow(new BusinessException(MensagemValidacaoVotacaoEnum.SOMENTE_SESSOES_ATIVAS_ESTAO_APTAS_A_RECEBEREM_VOTOS));
		ResponseEntity<String> response = restTemplate.postForEntity("/pauta/votar/", new VotoRequest(1L, mockAssociado, true), String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void votarQuandoAssociadoEstiverImpedidoDeVotarDeveriaRetornarCodigoDeStatus400BadRequest() {
		Associado mockAssociado = new Associado(1L);
		BDDMockito.when(voteService.votar(1L, mockAssociado, true)).thenThrow(new BusinessException(MensagemValidacaoVotacaoEnum.ASSOCIADO_IMPEDIDO_DE_VOTAR));
		ResponseEntity<String> response = restTemplate.postForEntity("/pauta/votar/", new VotoRequest(1L, mockAssociado, true), String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}
	
}
