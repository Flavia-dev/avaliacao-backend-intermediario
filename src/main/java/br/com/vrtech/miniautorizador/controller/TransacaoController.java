package br.com.vrtech.miniautorizador.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.vrtech.miniautorizador.controller.form.TransacaoForm;
import br.com.vrtech.miniautorizador.model.Respostas;
import br.com.vrtech.miniautorizador.model.Transacao;
import br.com.vrtech.miniautorizador.service.TransacaoService;

@RestController
@RequestMapping("transacoes")
public class TransacaoController {
	
	@Autowired
    private TransacaoService transacaoService;

	@PostMapping
    @Transactional
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<String> executaTransacao(@RequestBody @Valid TransacaoForm transacaoForm) {
		Transacao transacao = transacaoForm.convert();
				String resposta = transacaoService.verificaCartao(transacao);
//		try {
//				return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
//						
//		} catch (RuntimeException e) {
//				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
//		}
		
		if (!(resposta == Respostas.OK.name())) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(resposta);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }
}
