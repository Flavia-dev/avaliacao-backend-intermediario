package br.com.vrtech.miniautorizador.controller;

import static org.junit.Assert.*;

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
public class TransacaoControllerTest {

	@Autowired
	private MockMvc mockMvc;	
		
	@Test
	public void deveriaExecutarUmaTransacaoComSucesso() throws Exception {
		URI uri = new URI("/transacoes");
		
		mockMvc.perform(MockMvcRequestBuilders
					.post(uri)
					.content("{\"numeroCartao\":\"1234567890123450\",\"senhaCartao\":\"1234\",\"valor\":\"10.00\"}")
					.contentType(MediaType.APPLICATION_JSON))
			  .andExpect(MockMvcResultMatchers
					  .status()
					  .is(201))
			  .andExpect(MockMvcResultMatchers
					  .content()
					  .string(Respostas.OK.toString()));			
				
	}
	
}
