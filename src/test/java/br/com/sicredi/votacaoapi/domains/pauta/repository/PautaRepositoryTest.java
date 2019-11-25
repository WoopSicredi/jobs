package br.com.sicredi.votacaoapi.domains.pauta.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sicredi.votacaoapi.StringUtils;
import br.com.sicredi.votacaoapi.domains.pauta.model.Pauta;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PautaRepositoryTest {

	private PautaRepository pautaRepository;

	@Autowired
	public PautaRepositoryTest(PautaRepository pautaRepository) {
		this.pautaRepository = pautaRepository;
	}

	@Test
	public void createShouldPersistData() {
		Pauta pauta = new Pauta("Pauta de Teste");
		pautaRepository.save(pauta);
		assertThat(pauta.getId()).isNotNull();
		assertThat(pauta.getNome()).isEqualTo("Pauta de Teste");
	}

	@Test
	public void deleteShouldRemoveData() {
		Pauta pauta = new Pauta("Pauta de Teste");
		pautaRepository.save(pauta);
		pautaRepository.delete(pauta);
		assertThat(pautaRepository.findById(pauta.getId())).isEmpty();
	}

	@Test
	public void updateShouldChangeAndPersistData() {
		Pauta pauta = new Pauta("Pauta de Teste");
		pautaRepository.save(pauta);
		pauta.setNome("Pauta de Teste Dois");
		pautaRepository.save(pauta);
		Optional<Pauta> optChangedTopic = pautaRepository.findById(pauta.getId());
		assertThat(optChangedTopic
				.map(Pauta::getNome)
				.filter(n -> n.equals("Pauta de Teste Dois"))
				.isPresent()).isEqualTo(true);
	}
	
	@Test
	public void createWhenNameIsNullShouldThrowDataIntegrityViolationException() {
		DataIntegrityViolationException thrown = catchThrowableOfType(() -> pautaRepository.save(new Pauta()),
				DataIntegrityViolationException.class);
		
		assertThat(thrown).isNotNull();
	}
	
	@Test
	public void createWhenNameSizeIsLargerThan100ShouldThrowConstraintViolationException() {
		ConstraintViolationException thrown = catchThrowableOfType(() -> pautaRepository.save(new Pauta(StringUtils.spaces(101))),
				ConstraintViolationException.class);

		assertThat(thrown).isNotNull();
	}

}
