package br.com.sicredi.votacao.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sicredi.votacao.exception.BusinessException;
import br.com.sicredi.votacao.mock.PautaMocker;
import br.com.sicredi.votacao.mock.SessaoMocker;
import br.com.sicredi.votacao.model.Sessao;
import br.com.sicredi.votacao.repository.PautaRepository;
import br.com.sicredi.votacao.repository.SessaoRepository;
import br.com.sicredi.votacao.repository.VotoRepository;
import br.com.sicredi.votacao.service.impl.SessaoServiceImpl;

@RunWith(SpringRunner.class)
public class SessaoServiceTest {
	
	private SessaoServiceImpl sessaoService;
	
	@Mock
	private SessaoRepository sessaoRepository;
	
	@Mock
	private PautaRepository pautaRepository;
	
	@Mock
	private VotoRepository votoRepository; 

	@Before
	public void setUp() {
		sessaoService = new SessaoServiceImpl(sessaoRepository, pautaRepository, votoRepository); 
	}
	
	@Test(expected = BusinessException.class)
	public void givenSessaoWhitNonExistentPauta_whenSave_thenThrowsBusinessExcpetion() {
		when(pautaRepository.existsById(PautaMocker.ID)).thenReturn(false);
		sessaoService.save(SessaoMocker.SESSAO);
	}
	
	@Test
	public void givenSessao_whenSave_thenSaveAndReturn() {
		when(pautaRepository.existsById(PautaMocker.ID)).thenReturn(true);
		when(sessaoRepository.save(SessaoMocker.SESSAO)).thenReturn(SessaoMocker.SESSAO_CREATED);
		
		Sessao sessao = sessaoRepository.save(SessaoMocker.SESSAO);
		assertNotNull(sessao.getId());
		assertThat(sessao).isEqualToComparingOnlyGivenFields(SessaoMocker.SESSAO_CREATED, 
				"pauta",
				"inicio",
				"fim");
	}
}
