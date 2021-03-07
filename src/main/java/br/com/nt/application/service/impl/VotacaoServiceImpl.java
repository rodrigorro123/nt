package br.com.nt.application.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.nt.application.DTO.UserDto;
import br.com.nt.application.DTO.VotoDto;
import br.com.nt.application.exception.ApiException;
import br.com.nt.application.service.VotacaoService;
import br.com.nt.application.service.client.UserClient;
import br.com.nt.domain.entity.Votacao;
import br.com.nt.domain.repository.SessaoRepository;
import br.com.nt.domain.repository.VotacaoRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
@Component
public class VotacaoServiceImpl implements VotacaoService {
	
	private final VotacaoRepository votacaoRepository;
	private final SessaoRepository sessaoRepository;
	private final UserClient userClient;
	 
	/**
	 * Metodo para adicionar voto
	 * @param voto
	 * @return VotoDto
	 * @throws ApiException
	 */
	public String adicionaVoto (VotoDto voto) throws  ApiException 
	{
		try {
			
			Votacao votacao = Votacao.builder()
								    .cpf(voto.getCpf())
								    .voto(voto.getVoto().name())
								    .idSessao(voto.getIdSessao() )
								    .build();
											
			votacaoRepository.save(votacao);
			
			return "votacao realizada com sucesso";
			}catch (Exception e) {
				log.info("Erro ao realizar votacao - " + e);
	            throw ApiException.builder()
	            .statusCode(HttpStatus.BAD_REQUEST.value())
	            .code(ApiException.VALIDATION_ERROR)
	            .message("Erro ao realizar votacao")
	            .build();
				}
		}
	
	
	/**
	 * Metodo para validar pre-requisitos para votação
	 * @param voto
	 * @return Boolean
	 * @throws ApiException
	 */
	public Boolean validaVotacao(VotoDto voto) throws ApiException {
		
		try {
			
			if(validaUserExterno(voto.getCpf())) {
	            throw ApiException.builder()
	            .statusCode(HttpStatus.BAD_REQUEST.value())
	            .code(ApiException.VALIDATION_ERROR)
	            .message("Associado informado invalido para votacao")
	            .build();
			}
			
			if( votacaoRepository.findByCpfAndIdSessao( voto.getCpf() , voto.getIdSessao() ).orElse(null) != null ) {
	            throw ApiException.builder()
	            .statusCode(HttpStatus.BAD_REQUEST.value())
	            .code(ApiException.VALIDATION_ERROR)
	            .message("Associado ja realizou votacao")
	            .build();
			}
			
			if(sessaoRepository.findById(voto.getIdSessao()).orElse(null) == null) {
	            throw ApiException.builder()
	            .statusCode(HttpStatus.BAD_REQUEST.value())
	            .code(ApiException.VALIDATION_ERROR)
	            .message("Numero sessao invalida")
	            .build();
			}
			
			if(sessaoRepository.findBySessaoAtiva(voto.getIdSessao()).orElse(null) == null) {
	            throw ApiException.builder()
	            .statusCode(HttpStatus.BAD_REQUEST.value())
	            .code(ApiException.VALIDATION_ERROR)
	            .message("Sessao Finalizada")
	            .build();
			}
			
			return true;
		}catch(ApiException ae){
            throw ApiException.builder()
            .statusCode(ae.getStatusCode())
            .code(ae.getCode())
            .message(ae.getMessage())
            .build();
		
		}catch (Exception e) {
			log.info("Erro ao realizar votacao - " + e);
            throw ApiException.builder()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .code(ApiException.VALIDATION_ERROR)
            .message("Erro ao realizar votacao")
            .build();
			}
	}

	private Boolean validaUserExterno(String cpf) throws ApiException{
		
		try {
			UserDto user = userClient.getUserPermission(cpf);
			
			if("UNABLE_TO_VOTE".equals(user.getStatus())) {
				return true;
			}else {
				return false;
			}
		} catch (FeignException fe) {
			log.error(fe.getMessage());
			if(fe.status() == 404 ) {//cpf retornado 404 -> invalido
				return true;
			}
            throw ApiException.builder()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .code(ApiException.VALIDATION_ERROR)
            .message("Erro ao consultar api externa de consulta de usuarios " + fe.contentUTF8())
            .build();

		} catch (Exception e) {
			log.error("Erro ao consultar api externa de user - " + e);
            throw ApiException.builder()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .code(ApiException.VALIDATION_ERROR)
            .message("Erro ao consultar api externa de consulta de usuarios")
            .build();
		}
	}
	
	
	/**
	 * Metodo para realizar sumarizacao da votação por sessao
	 * @author rodrigo
	 * @param idSessao
	 * @throws ApiException
	 */
	@Override
	public Map<String, Long> getVotacao(Long idSessao) throws ApiException {
		try {
			
			List<Votacao> result = votacaoRepository.findByIdSessao(idSessao);
			
			if(result.isEmpty() ) {
	            throw ApiException.builder()
	            .statusCode(HttpStatus.BAD_REQUEST.value())
	            .code(ApiException.VALIDATION_ERROR)
	            .message("Sessao informada invalida")
	            .build();
			}
			return result.stream().collect(
                    Collectors.groupingBy(Votacao::getVoto, Collectors.counting()
                    		)
            );
		}catch(ApiException ae){
            throw ApiException.builder()
            .statusCode(ae.getStatusCode())
            .code(ae.getCode())
            .message(ae.getMessage())
            .build();
		} catch (Exception e) {
			log.info("Erro ao totalziar votacao - " + e);
            throw ApiException.builder()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .code(ApiException.VALIDATION_ERROR)
            .message("Erro ao totalizar votacao")
            .build();
		}
		
	}
}