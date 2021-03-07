package br.com.nt.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nt.domain.entity.Votacao;

public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

	List<Votacao> findByIdSessao(Long idSessao);
	
	Optional<Votacao> findByCpfAndIdSessao(String cpf, Long idSessao) ;
	 
}
