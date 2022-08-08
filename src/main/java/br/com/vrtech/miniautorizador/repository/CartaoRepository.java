package br.com.vrtech.miniautorizador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vrtech.miniautorizador.model.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, String> {

	List<Cartao> findByNumero(String numeroCartao);

}
