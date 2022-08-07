package br.com.vrtech.miniautorizador.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "cartoes")
public class Cartao {
	
	@Id
	private String numero;
	private String senha;
	private BigDecimal saldo = new BigDecimal(500);
	
	@OneToMany(mappedBy = "cartao")
	private List<Transacao> transacoes;
	
	public Cartao() {
		
	}
	
	public Cartao(String numeroCartao, String senha) {
		this.numero = numeroCartao;
		this.senha = senha;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numeroCartao) {
		this.numero = numeroCartao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	public void efetuaDebito(BigDecimal valor) {
		setSaldo(saldo.subtract(valor));
	}
	
	public boolean verificaSaldo(BigDecimal valor) {
		return saldo.compareTo(valor) >=0;
	}
	
	public boolean verificaSenha(String senhaDigitada) {
		return this.senha.equals(senhaDigitada);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartao other = (Cartao) obj;
		return Objects.equals(numero, other.numero);
	}

}
