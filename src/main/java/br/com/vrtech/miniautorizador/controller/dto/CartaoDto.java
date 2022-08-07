package br.com.vrtech.miniautorizador.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.vrtech.miniautorizador.model.Cartao;

public class CartaoDto {

	private String numeroCartao;
	private String senha;

	public CartaoDto(Cartao cartao) {
		this.senha = cartao.getSenha();
		this.numeroCartao = cartao.getNumero();

	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public String getSenha() {
		return senha;
	}

	public static List<CartaoDto> converter(List<Cartao> cartoes) {
		return cartoes.stream().map(CartaoDto::new).collect(Collectors.toList());
	}

}
