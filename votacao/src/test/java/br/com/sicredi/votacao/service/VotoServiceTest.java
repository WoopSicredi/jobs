package br.com.sicredi.votacao.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sicredi.votacao.exception.BusinessException;
import br.com.sicredi.votacao.mock.AssociadoMocker;
import br.com.sicredi.votacao.mock.PautaMocker;
import br.com.sicredi.votacao.mock.SessaoMocker;
import br.com.sicredi.votacao.mock.VotoMocker;
import br.com.sicredi.votacao.model.Voto;
import br.com.sicredi.votacao.repository.AssociadoRepository;
import br.com.sicredi.votacao.repository.SessaoRepository;
import br.com.sicredi.votacao.repository.VotoRepository;
import br.com.sicredi.votacao.service.impl.VotoServiceImpl;

@RunWith(SpringRunner.class)
public class VotoServiceTest {

	@InjectMocks
	private VotoServiceImpl votoService;
	
	@Mock
	private VotoRepository votoRepository;
	
	@Mock
	private SessaoRepository sessaoRepository;
	
	@Mock
	private AssociadoRepository associadoRepository;
	
	@Test(expected = BusinessException.class)
	public void givenVotoWhitNonExistentAssociado_whenSave_thenThrowsBusinessExcpetion() {
		when(associadoRepository.existsById(AssociadoMocker.ID)).thenReturn(false);
		when(sessaoRepository.findById(SessaoMocker.ID)).thenReturn(Optional.of(SessaoMocker.SESSAO));
		when(votoRepository.existsBySessaoPautaAndAssociado(PautaMocker.PAUTA, AssociadoMocker.ASSOCIADO))
				.thenReturn(false);
		
		votoService.save(VotoMocker.VOTO);
	}
	
	@Test(expected = BusinessException.class)
	public void givenVotoWhitNonExistentSessao_whenSave_thenThrowsBusinessExcpetion() {
		when(associadoRepository.existsById(AssociadoMocker.ID)).thenReturn(true);
		when(sessaoRepository.findById(SessaoMocker.ID)).thenReturn(Optional.empty());
		when(votoRepository.existsBySessaoPautaAndAssociado(PautaMocker.PAUTA, AssociadoMocker.ASSOCIADO))
				.thenReturn(false);
		
		votoService.save(VotoMocker.VOTO);
	}
	
	@Test(expected = BusinessException.class)
	public void givenVotoWhitSessaoClosed_whenSave_thenThrowsBusinessExcpetion() {
		when(associadoRepository.existsById(AssociadoMocker.ID)).thenReturn(true);
		when(sessaoRepository.findById(SessaoMocker.ID)).thenReturn(Optional.of(SessaoMocker.SESSAO_CLOSED));
		when(votoRepository.existsBySessaoPautaAndAssociado(PautaMocker.PAUTA, AssociadoMocker.ASSOCIADO))
				.thenReturn(false);
		
		votoService.save(VotoMocker.VOTO);
	}
	
	@Test(expected = BusinessException.class)
	public void givenVotoAlreadyRegistered_whenSave_thenThrowsBusinessExcpetion() {
		when(associadoRepository.existsById(AssociadoMocker.ID)).thenReturn(true);
		when(sessaoRepository.findById(SessaoMocker.ID)).thenReturn(Optional.of(SessaoMocker.SESSAO));
		when(votoRepository.existsBySessaoPautaAndAssociado(PautaMocker.PAUTA, AssociadoMocker.ASSOCIADO))
				.thenReturn(true);
		
		votoService.save(VotoMocker.VOTO);
	}
	
	@Test
	public void givenVoto_whenSave_thenSaveAndReturn() {
		when(associadoRepository.existsById(AssociadoMocker.ID)).thenReturn(true);
		when(sessaoRepository.findById(SessaoMocker.ID)).thenReturn(Optional.of(SessaoMocker.SESSAO));
		when(votoRepository.existsBySessaoPautaAndAssociado(PautaMocker.PAUTA, AssociadoMocker.ASSOCIADO))
				.thenReturn(false);
		when(votoRepository.save(VotoMocker.VOTO)).thenReturn(VotoMocker.VOTO_CREATED);
		
		Voto voto = votoService.save(VotoMocker.VOTO);
		assertThat(voto).isEqualToComparingOnlyGivenFields(VotoMocker.VOTO_CREATED, 
				"sessao",
				"associado",
				"valorVoto");
	}
	
	
}
