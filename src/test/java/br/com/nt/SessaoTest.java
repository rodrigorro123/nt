package br.com.nt;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.nt.application.DTO.PautaDto;
import br.com.nt.application.DTO.SessaoDto;
import br.com.nt.application.service.impl.SessaoServiceImpl;
import br.com.nt.domain.entity.Sessao;
import br.com.nt.domain.repository.SessaoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
public class SessaoTest {
 
	@Autowired
	private SessaoRepository repository;
	
	@Autowired
	private SessaoServiceImpl service;
	
	@Test
	public void testCriarSessao() {
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
	public void testBuscarSessao() {
		
		Optional<Sessao> ressult = this.repository.findById(1L);
		assertThat(ressult.isPresent()).isFalse();
	}
	
	
}
