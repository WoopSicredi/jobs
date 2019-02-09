package br.com.sicredi.votacao_pauta.service;


import br.com.sicred.votacao_pauta.dto.SecaoVotacaoDto;
import br.com.sicred.votacao_pauta.entity.Pauta;
import br.com.sicred.votacao_pauta.entity.SecaoVotacao;
import br.com.sicred.votacao_pauta.repository.PautaRepository;
import br.com.sicred.votacao_pauta.repository.SecaoVotacaoRepository;
import br.com.sicred.votacao_pauta.repository.VotoRepository;
import br.com.sicred.votacao_pauta.service.SecaoVotacaoService;
import br.com.sicred.votacao_pauta.service.SecaoVotacaoServiceImpl;
import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SecaoVotacaoServiceTest {

    private SecaoVotacaoService secaoVotacaoService;
    @Mock
    private SecaoVotacaoRepository secaoVotacaoRepository;
    @Mock
    private PautaRepository pautaRepository;
    @Mock
    private VotoRepository votoRepository;
    private Faker faker;

    @Before
    public void setup() {
        this.secaoVotacaoService = new SecaoVotacaoServiceImpl(
                pautaRepository, secaoVotacaoRepository, votoRepository);
        this.faker = new Faker();
    }


    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidIdPautaThenShouldThrowExceptionWhenCreatingSection() {
        //Arrange
        when(pautaRepository.findById(anyLong())).thenReturn(Optional.empty());
        //Act
        this.secaoVotacaoService.createSecaoVotacao(
                SecaoVotacaoDto.builder()
                        .dataAbertura(LocalDateTime.now())
                        .idPauta(new Random().nextLong())
                        .build());
        //Assert não é necessário aqui pois esperamos que uma exception seja lançada
    }

    @Test
    public void givenValidDataThenShouldReturnEntityWhenCreatingSection() {
        //Arrange
        LocalDateTime dataAbertura = LocalDateTime.now();
        LocalDateTime expectedDataEncerramento = dataAbertura.plusMinutes(1);
        Pauta expectedPauta = Pauta.builder().descricao(faker.esports().event()).build();
        SecaoVotacao expectedSecao = SecaoVotacao.builder()
                .id(new Random().nextLong())
                .dataAbertura(dataAbertura)
                .dataFechamento(expectedDataEncerramento)
                .pauta(expectedPauta)
                .build();
        when(pautaRepository.findById(anyLong())).thenReturn(
                Optional.of(expectedPauta));
        when(secaoVotacaoRepository.save(any())).thenReturn(expectedSecao);
        //Act
        SecaoVotacao secaoVotacao = this.secaoVotacaoService.createSecaoVotacao(
                SecaoVotacaoDto.builder()
                        .dataAbertura(dataAbertura)
                        .idPauta(new Random().nextLong())
                        .build());
        assertThat(expectedSecao, equalTo(secaoVotacao));
    }
}
