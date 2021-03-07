package br.com.nt;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.nt.application.DTO.PautaDto;
import br.com.nt.application.DTO.SessaoDto;
import br.com.nt.application.service.impl.SessaoServiceImpl;
import br.com.nt.domain.entity.Sessao;
import br.com.nt.domain.repository.SessaoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class SessaoTest {
 
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    
	@Autowired
	private SessaoRepository repository;
	
	@Mock
	private SessaoServiceImpl service;
	
	@Test
	public void testCriarPauta() {
		try {
			
			PautaDto pauta = PautaDto.builder()
					  .descricao("teste")
					  .titulo("titulo-teste")
					  .build();

			SessaoDto sessao = SessaoDto.builder()
					  .descricao("teste")
					  .titulo("titulo-teste")
					  .pauta(pauta)
					  .build();
			
			
			SessaoDto ressult;
			ressult = this.service.adicionar(sessao);

			assertThat(ressult).isNotNull() ;

		} catch (Exception e) {
			log.error("falha ao realizar testes - {}", e.getMessage());
		}
	}
	
	@Test
	public void testBuscarPauta() {
		
		Optional<Sessao> ressult = this.repository.findById(1L);
		assertThat(ressult.get()).isNotNull() ;
	}
	
	
}
