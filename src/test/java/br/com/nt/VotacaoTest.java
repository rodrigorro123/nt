package br.com.nt;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.nt.application.DTO.TipoVotoEnum;
import br.com.nt.application.DTO.VotoDto;
import br.com.nt.application.service.impl.VotacaoServiceImpl;
import br.com.nt.domain.repository.VotacaoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
public class VotacaoTest {
	
	@Autowired
	private VotacaoServiceImpl service;
	
	@Autowired
	private VotacaoRepository repository;
	
	@Autowired
	private MockMvc mockMvc;	

	@Test
	public void testRealizarValidacaoVotacao() {
		try {
		//	BDDMockito.doNothing();
			VotoDto voto = VotoDto.builder()
								.cpf("123456789")
								.voto(TipoVotoEnum.SIM)
								.idSessao(1L)
								.build();
			
			
			Boolean result =  this.service.validaVotacao(voto);

			assertThat(result).isEqualTo(false) ;

		} catch (Exception e) {
			log.error("falha ao realizar testes - {}", e.getMessage());
		}
	}	
	
	@Test
	public void testRealizarVotacao() {
		try {
			//BDDMockito.doNothing();
			VotoDto voto = VotoDto.builder()
								.cpf("987654321")
								.voto(TipoVotoEnum.NAO)
								.idSessao(1L)
								.build();
			
  			String result =  this.service.adicionaVoto(voto);

			assertThat(result).as("votacao realizada com sucesso") ;

		} catch (Exception e) {
			log.error("falha ao realizar testes - {}", e.getMessage());
		}
	}
	
	@Test
	public void testBuscarVoto() {
		
		Long result = this.repository.count();
		assertThat(result > 0) ;
	}
	
	
	@Test
	public void testVerificarVotacao() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders
		.get("/votacao/resultado?idSessao=1")
		.contentType(MediaType.APPLICATION_JSON) )
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful() ) ;

	}
	
}
