package br.com.vrtech.miniautorizador.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.vrtech.miniautorizador.model.Cartao;

public class CartaoForm {

	@NotNull
	@NotEmpty
	@Length(min = 4)
	private String senha;
	@NotNull
	@NotEmpty
	@Length(min = 16, max = 16)
	private String numeroCartao;

	public String getSenha() {
		return senha;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public Cartao converter() {
		return new Cartao(numeroCartao, senha);
	}

}
