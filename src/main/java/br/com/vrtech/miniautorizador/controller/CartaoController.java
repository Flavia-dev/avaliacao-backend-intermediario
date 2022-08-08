package br.com.vrtech.miniautorizador.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vrtech.miniautorizador.controller.dto.CartaoDto;
import br.com.vrtech.miniautorizador.controller.form.CartaoForm;
import br.com.vrtech.miniautorizador.model.Cartao;
import br.com.vrtech.miniautorizador.repository.CartaoRepository;
import br.com.vrtech.miniautorizador.service.CartaoService;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

	private CartaoService cartaoService;

	CartaoController(CartaoService cartaoService) {
		this.cartaoService = cartaoService;
	}

	@Autowired
	private CartaoRepository cartaorepository;

	@GetMapping
	public List<CartaoDto> lista(String numeroCartao) {
		if (numeroCartao == null) {
			List<Cartao> cartoes = cartaorepository.findAll();
			return CartaoDto.converter(cartoes);
		} else {
			List<Cartao> cartoes = cartaorepository.findByNumero(numeroCartao);
			return CartaoDto.converter(cartoes);
		}

	}

	@GetMapping("/{numeroCartao}")
	public ResponseEntity<BigDecimal> verificarSaldo(@PathVariable String numeroCartao) {
		try {
			BigDecimal saldo = cartaoService.verificarSaldo(numeroCartao);
			return ResponseEntity.status(HttpStatus.OK).body(saldo);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CartaoDto> criarCartao(@RequestBody @Valid CartaoForm cartaoForm) {
		Cartao cartao = cartaoForm.converter();
		try {
			cartaoService.criarCartao(cartao);
			return ResponseEntity.status(HttpStatus.CREATED).body(new CartaoDto(cartao));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new CartaoDto(cartao));
		}
	}

}
