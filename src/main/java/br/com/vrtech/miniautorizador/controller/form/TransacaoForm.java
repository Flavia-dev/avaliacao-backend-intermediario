package br.com.vrtech.miniautorizador.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.vrtech.miniautorizador.model.Cartao;
import br.com.vrtech.miniautorizador.model.Transacao;

public class TransacaoForm {

	@NotNull
	private String numeroCartao;

	@NotNull
	@NotBlank
	@Length(min = 4)
	private String senhaCartao;
	private BigDecimal valor;

	public TransacaoForm(String numeroCartao, String senhaCartao, BigDecimal valor) {
		this.numeroCartao = numeroCartao;
		this.senhaCartao = senhaCartao;
		this.valor = valor;
	}

	public Transacao convert() {
		Cartao cartao = new Cartao(numeroCartao, senhaCartao);
		return new Transacao(valor, cartao);
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public void setSenhaCartao(String senhaCartao) {
		this.senhaCartao = senhaCartao;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public String getSenhaCartao() {
		return senhaCartao;
	}

	public BigDecimal getValor() {
		return valor;
	}

}
