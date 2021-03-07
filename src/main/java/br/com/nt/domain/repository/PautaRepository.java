package br.com.nt.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nt.domain.entity.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
	
	Optional<Pauta> findByTitulo(String titulo) ;

}
