package br.com.vrtech.miniautorizador.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.vrtech.miniautorizador.model.Cartao;
import br.com.vrtech.miniautorizador.repository.CartaoRepository;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository cartaoRepository;

	public void criarCartao(Cartao cartao) {
		Optional<Cartao> optional = cartaoRepository.findById(cartao.getNumero());
		try {
			optional.orElseThrow(() -> new NotFoundException());
			throw new RuntimeException();
		} catch (NotFoundException e) {
			cartaoRepository.save(cartao);
		}
	}

	public BigDecimal verificarSaldo(String numeroCartao) {
		Optional<Cartao> optional = cartaoRepository.findById(numeroCartao);
		optional.orElseThrow(() -> new RuntimeException());
		return optional.get().getSaldo();
	}

	public Optional<Cartao> findByNumero(String numeroCartao) {
		 return cartaoRepository.findById(numeroCartao);
	}

}