package br.com.vrtech.miniautorizador.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transacoes")
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal valor;
	private LocalDateTime data_hora = LocalDateTime.now();
	
	@ManyToOne(targetEntity = Cartao.class)
    @JoinColumn(name = "numero_cartao")
    private Cartao cartao;
	
	public Transacao() {
		
	}

	public Transacao(Long id, BigDecimal valor) {
		this.id = id;
		this.valor = valor;
	}

	public Transacao(BigDecimal valor, LocalDateTime data_hora, Cartao cartao) {
		this.valor = valor;
		this.data_hora = data_hora;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public LocalDateTime getData_hora() {
		return data_hora;
	}

	public Cartao getCartao() {
		return cartao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartao, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		return Objects.equals(cartao, other.cartao) && Objects.equals(id, other.id);
	}
	
	
	

}

