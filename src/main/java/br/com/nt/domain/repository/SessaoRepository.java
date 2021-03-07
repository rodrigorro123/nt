package br.com.nt.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.nt.domain.entity.Sessao;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {

	Optional<Sessao> findById(Long id) ;
	
	@Query(  value = " select * from tbl_sessao ts \n" + 
			"where ts.dt_validade > sysdate()\n" + 
			"and ts.id_pauta = :idPauta ",nativeQuery =  true
			)
	Optional<Sessao> findByPautaValida(Long idPauta); 
	
	
	@Query(  value = " select * from tbl_sessao ts \n" + 
			"where ts.dt_validade > sysdate()\n" + 
			"and ts.id_sessao = :idSessao ",nativeQuery =  true
			)
	Optional<Sessao> findBySessaoAtiva(Long idSessao); 

	
	@Query(  value = " select * from tbl_sessao ts \n" + 
			"where ts.dt_validade < sysdate()\n" + 
			"and flag_ativo is null ",nativeQuery =  true
			)
	List<Sessao> findBySessaoFinalizada(); 
	
}
