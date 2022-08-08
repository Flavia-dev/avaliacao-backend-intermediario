package br.com.vrtech.miniautorizador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.vrtech.miniautorizador.model.Transacao;


public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

	Optional<Transacao> findById(String numeroCartao);

}
