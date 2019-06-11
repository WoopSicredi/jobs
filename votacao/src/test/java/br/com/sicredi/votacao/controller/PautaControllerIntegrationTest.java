package br.com.sicredi.votacao.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PautaControllerIntegrationTest {
	
	private static final String URI = "/api/pautas";
	
	private MockMvc mockMvc;
	
	private ObjectMapper mapper;
	
	@Autowired
  	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = webAppContextSetup(webApplicationContext).alwaysDo(print()).build();
		mapper = new ObjectMapper();
	}
	
	@Test
	public void test1_givenPauta_whenPost_thenStatus201() throws Exception {
		mockMvc.perform(post(URI)
				.content(mapper.writeValueAsString(PautaMocker.PAUTA_SAVE_DTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.nome").value(PautaMocker.NOME))
			.andExpect(jsonPath("$.pergunta").value(PautaMocker.PERGUNTA));
	}
	
	@Test
	public void test2_givenPauta_whenGet_thenStatus200() throws Exception {
		mockMvc.perform(get(URI))
			.andExpect(status().isOk());
	}
	
	@Test
	public void test3_givenPauta_WhenGet_thenStatus200() throws Exception {
		mockMvc.perform(get(URI + "/{id}", PautaMocker.ID))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(PautaMocker.ID));
	}
	
	@Test
	public void test4_whenGetPautaNonexistent_thenStatus404() throws Exception {
		mockMvc.perform(get(URI + "/{id}", PautaMocker.ID_NOT_FOUND))
			.andDo(print())
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void test5_givenResultadoPauta_WhenGet_thenStatus200() throws Exception {
		mockMvc.perform(get(URI + "/{id}/resultados", PautaMocker.ID))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.nomePauta").value(PautaMocker.NOME))
			.andExpect(jsonPath("$.pergunta").value(PautaMocker.PERGUNTA));
	}
	
	@Test
	public void test5_givenResultadoPautaNonexistent_WhenGet_thenStatus404() throws Exception {
		mockMvc.perform(get(URI + "/{id}/resultados", PautaMocker.ID_NOT_FOUND))
			.andDo(print())
			.andExpect(status().isNotFound());
	}

}
