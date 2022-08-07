package br.com.vrtech.miniautorizador.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vrtech.miniautorizador.model.Cartao;
import br.com.vrtech.miniautorizador.model.Respostas;
import br.com.vrtech.miniautorizador.model.Transacao;
import br.com.vrtech.miniautorizador.repository.CartaoRepository;

@Service
public class TransacaoService {

	@Autowired
	private CartaoRepository cartaorepository;

	public String verificaCartao(Transacao transacao) {
		Optional<Cartao> cartaoOptional = cartaorepository.findById(transacao.getCartao().getNumero());
		if (!cartaoOptional.isPresent())
			return Respostas.CARTAO_INEXISTENTE.name();
		if (!cartaoOptional.get().verificaSenha(transacao.getCartao().getSenha()))
			return Respostas.SENHA_INVALIDA.name();
		if (!cartaoOptional.get().verificaSaldo(transacao.getValor()))
			return Respostas.SALDO_INSUFICIENTE.name();
		
		cartaoOptional.get().efetuaDebito(transacao.getValor());
		return Respostas.OK.name();

	}

}
