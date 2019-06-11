package br.com.sicredi.votacao.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sicredi.votacao.mock.PautaMocker;
import br.com.sicredi.votacao.mock.SessaoMocker;
import br.com.sicredi.votacao.service.PautaService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SessaoControllerIntegrationTest {
	
	private static final String URI = "/api/sessoes";
	
	private MockMvc mockMvc;
	
	private ObjectMapper mapper;
	
	@Autowired
  	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private PautaService pautaService;
	
	@Before
	public void setUp() {
		mockMvc = webAppContextSetup(webApplicationContext).alwaysDo(print()).build();
		mapper = new ObjectMapper();
		
		pautaService.save(PautaMocker.PAUTA);
	}
	
	@Test
	public void test1_givenSessao_whenPost_thenStatus201() throws Exception {
		mockMvc.perform(post(URI)
				.content(mapper.writeValueAsString(SessaoMocker.SESSAO_SAVE_DTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isCreated());
	}
	
	@Test
	public void test2_givenSessao_whenPost_thenStatus422() throws Exception {
		mockMvc.perform(post(URI)
				.content(mapper.writeValueAsString(SessaoMocker.SESSAO_SAVE_DTO_WITH_INVALID_PAUTA))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isUnprocessableEntity());
	}

}
