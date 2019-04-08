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

import br.com.sicredi.votacao.mock.AssociadoMocker;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AssociadoControllerIntegrationTest {

	private static final String URI = "/api/associados";
	
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
	public void test1_givenAssociado_whenPost_thenStatus201() throws Exception {
		mockMvc.perform(post(URI)
				.content(mapper.writeValueAsString(AssociadoMocker.ASSOCIADO_SAVE_DTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.nome").value(AssociadoMocker.NOME))
			.andExpect(jsonPath("$.cpf").value(AssociadoMocker.CPF));
	}
	
	@Test
	public void test2_givenAssociado_whenGet_thenStatus200() throws Exception {
		mockMvc.perform(get(URI))
			.andExpect(status().isOk());
	}
	
	@Test
	public void test3_givenAssociado_WhenGet_thenStatus200() throws Exception {
		mockMvc.perform(get(URI + "/{id}", AssociadoMocker.ID))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(AssociadoMocker.ID));
	}
	
	@Test
	public void test4_whenGetNotFoundAssociado_thenStatus404() throws Exception {
		mockMvc.perform(get(URI + "{id}", AssociadoMocker.ID_NOT_FOUND))
			.andDo(print())
			.andExpect(status().isNotFound());
	}
	

}
