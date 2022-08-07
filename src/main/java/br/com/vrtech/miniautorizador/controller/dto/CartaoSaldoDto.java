package br.com.vrtech.miniautorizador.controller.dto;

import java.math.BigDecimal;

import br.com.vrtech.miniautorizador.model.Cartao;

public class CartaoSaldoDto {
	
	private BigDecimal saldo;
	
	public CartaoSaldoDto(Cartao cartao) {
		this.setSaldo(cartao.getSaldo());
		
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
