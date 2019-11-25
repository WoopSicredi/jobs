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
import br.com.sicredi.votacaoapi.application.error.BusinessException;
import br.com.sicredi.votacaoapi.application.error.MensagemValidacaoVotacaoEnum;
import br.com.sicredi.votacaoapi.application.error.RecursoNaoEncontradoException;
import br.com.sicredi.votacaoapi.domains.pauta.dto.ResultadoVotacaoResponse;
import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;
import br.com.sicredi.votacaoapi.domains.pauta.model.validations.TipoDeResultadoDaVotacao;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GetPollCountServiceTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private ApuracaoDaVotacaoPautaService apuracaoDaVotacaoPautaService;

	@Test
	public void apurarQuandoSessaoEstiverFinalizadaDeveriaRetornarCodigoDeStatus200Ok() {

		Pauta pauta = new Pauta();
		pauta.setId(1L);
		pauta.setNome(StringUtils.spaces(100));
		pauta.abrirSessao(null);
		
		ResultadoVotacaoResponse resultadoVotacaoResponse = new ResultadoVotacaoResponse(pauta, TipoDeResultadoDaVotacao.SIM);
		BDDMockito.when(apuracaoDaVotacaoPautaService.apurar(1L)).thenReturn(resultadoVotacaoResponse);
		
		ResponseEntity<String> response = restTemplate.getForEntity("/pauta/apuracao/1", String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		
	}

	@Test
	public void apurarQuandoPautaENulaDeveriaRetornarCodigoDeStatus400BadRequest() {
		ResponseEntity<String> response = restTemplate.getForEntity("/pauta/apuracao/A", String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void apurarQuandoCodigoDaPautaENegativaDeveriaRetornarCodigoDeStatus400BadRequest() {
		ResponseEntity<String> response = restTemplate.getForEntity("/pauta/apuracao/-1", String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void apurarQuandoPautaNaoExisteDeveriaRetornarCodigoDeStatus404ResourceNotFound() {
		BDDMockito.when(apuracaoDaVotacaoPautaService.apurar(1L)).thenThrow(new RecursoNaoEncontradoException(MensagemValidacaoVotacaoEnum.PAUTA_NAO_EXISTE));
		ResponseEntity<String> response = restTemplate.getForEntity("/pauta/apuracao/1", String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	public void apurarQuandoSessaoNaoFoiIniciadaDeveriaRetornarCodigoDeStatus400BadRequest() {
		BDDMockito.when(apuracaoDaVotacaoPautaService.apurar(1L)).thenThrow(new BusinessException(MensagemValidacaoVotacaoEnum.SESSAO_NAO_INICIOU));
		ResponseEntity<String> response = restTemplate.getForEntity("/pauta/apuracao/1", String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void apurarQuandoSessaoEstiverAtivaDeveriaRetornarCodigoDeStatus400BadRequest() {
		BDDMockito.when(apuracaoDaVotacaoPautaService.apurar(1L)).thenThrow(new BusinessException(MensagemValidacaoVotacaoEnum.SESSAO_ESTA_ATIVA));
		ResponseEntity<String> response = restTemplate.getForEntity("/pauta/apuracao/1", String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

}
