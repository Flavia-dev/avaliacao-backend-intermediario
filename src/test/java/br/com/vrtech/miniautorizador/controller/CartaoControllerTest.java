package br.com.vrtech.miniautorizador.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.vrtech.miniautorizador.model.Respostas;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartaoControllerTest {

	@Autowired
	private MockMvc mockMvc;

//	@Test
//	void deveriaCriarNovoCartao() throws Exception {
//		URI uri = new URI("/cartoes");
//
//		mockMvc.perform(
//				MockMvcRequestBuilders.post(uri).content("{\"numeroCartao\":\"7894561237894561\",\"senha\":\"1234\"}")
//						.contentType(MediaType.APPLICATION_JSON))
//						.andExpect(MockMvcResultMatchers.status()
//						.is(201))
//						.andExpect(MockMvcResultMatchers.content()
//						.json("{\"numeroCartao\":\"7894561237894561\",\"senha\":\"1234\"}"));
//	}
//	

	
	
	@Test
	public void deveriaDevolver404CasoNmeroCartaoNaoExista() throws Exception {
		URI uri = new URI("/cartoes/1");

		mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(404))
				.andExpect(MockMvcResultMatchers.content().string(""));
	}
	
	@Test
	void deveriaDevolver422SeCartaoJaExiste() throws Exception {
		URI uri = new URI("/cartoes");
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content("{\"numeroCartao\":\"1234567890123450\",\"senha\":\"1234\"}")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(422))
		.andExpect(MockMvcResultMatchers
				.content()
				.json("{\"senha\":\"1234\",\"numeroCartao\":\"1234567890123450\"}"));		
	}

	@Test
	void deveriaDevolver422CasoSejaDigitadaSenhaIncorreta() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(new URI("/transacoes"))
				.content("{\"numeroCartao\":\"1234567890123450\",\"senhaCartao\":\"0000\",\"valor\":\"10.00\"}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(422))
				.andExpect(MockMvcResultMatchers.content().string(Respostas.SENHA_INVALIDA.toString()));
	}

	@Test
	void deveriaDevolver422SeNaoTiverSaldo() throws Exception {
		URI uri = new URI("/transacoes");
		String json = "{\"numeroCartao\":\"1234567890123450\",\"senhaCartao\":\"1234\",\"valor\":\"600.00\"}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(422))
				.andExpect(MockMvcResultMatchers.content().string(Respostas.SALDO_INSUFICIENTE.toString()));
	}

}
