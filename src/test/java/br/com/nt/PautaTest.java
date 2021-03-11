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
import br.com.nt.application.service.impl.PautaServiceImpl;
import br.com.nt.domain.entity.Pauta;
import br.com.nt.domain.repository.PautaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
public class PautaTest {
    
	@Autowired
	private PautaRepository repository;
	
	@Autowired
	private PautaServiceImpl service;

	@Test
	public void testCriarPauta() {
		try {
			
			PautaDto pauta = PautaDto.builder()
					  .descricao("teste")
					  .titulo("titulo-teste")
					  .build();
			
			PautaDto ressult;
			ressult = this.service.adicionar(pauta);

			assertThat(ressult).isNotNull() ;

		} catch (Exception e) {
			log.error("falha ao realizar testes - {}", e.getMessage());
		}
	}
	
	@Test
	public void testBuscarPauta() {
		
		Optional<Pauta> ressult = this.repository.findById(14L);
		assertThat(ressult.isPresent()).isTrue();
	}
	
	

	
}
