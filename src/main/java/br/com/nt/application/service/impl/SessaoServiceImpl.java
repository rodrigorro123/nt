package br.com.nt.application.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.nt.application.DTO.SessaoDto;
import br.com.nt.application.exception.ApiException;
import br.com.nt.application.service.SessaoService;
import br.com.nt.domain.entity.Sessao;
import br.com.nt.domain.repository.PautaRepository;
import br.com.nt.domain.repository.SessaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class SessaoServiceImpl implements SessaoService {
	
	private final SessaoRepository sessaoRepository;
	private final PautaRepository pautaRepository;
	private final ModelMapper mapper;
	
	 
	/**
	 * Metodo para criar uma nova Sessao
	 * @param sessao
	 * @return SessaoDto
	 * @throws ApiException
	 */
	public SessaoDto adicionar (SessaoDto sessao) throws  ApiException 
	{
		try {
			SessaoDto ret =  new SessaoDto();
			
			Sessao saveSessao =  new Sessao(); 
			saveSessao =  mapper.map(sessao, Sessao.class);
			saveSessao = sessaoRepository.saveAndFlush(saveSessao);
			BeanUtils.copyProperties(saveSessao, ret);
				
			return ret;
			
		
			}catch (Exception e) {
				log.info("Erro ao salvar sessao- " + e);
	            throw ApiException.builder()
	            .statusCode(HttpStatus.BAD_REQUEST.value())
	            .code(ApiException.VALIDATION_ERROR)
	            .message("Erro ao salvar sessao")
	            .build();
				}
			}
	


	public void validaSessao(SessaoDto sessao) throws ApiException 
	{
 
		try {
			if(sessao.getDtValidade().isBefore(LocalDateTime.now())) {
	            throw ApiException.builder()
	            .statusCode(HttpStatus.BAD_REQUEST.value())
	            .code(ApiException.VALIDATION_ERROR)
	            .message("Data Informada anterior a data atual")
	            .build();
			}
			
			if ( pautaRepository.findById(sessao.getPauta().getId()).orElse(null) == null ) {
	            throw ApiException.builder()
	            .statusCode(HttpStatus.BAD_REQUEST.value())
	            .code(ApiException.VALIDATION_ERROR)
	            .message("Pauta Inexistente")
	            .build();
			}
			

			if (sessaoRepository.findByPautaValida( sessao.getPauta().getId()).orElse(null) != null  ) {
	            throw ApiException.builder()
	            .statusCode(HttpStatus.BAD_REQUEST.value())
	            .code(ApiException.VALIDATION_ERROR)
	            .message("Sessao ativa para pauta solicitada")
	            .build();
			}

			
			}catch(ApiException ae){
	            throw ApiException.builder()
	            .statusCode(ae.getStatusCode())
	            .code(ae.getCode())
	            .message(ae.getMessage())
	            .build();
			} catch (Exception e) {
				log.error("Erro ao salvar sessao- " + e);
	            throw ApiException.builder()
	            .statusCode(HttpStatus.BAD_REQUEST.value())
	            .code(ApiException.VALIDATION_ERROR)
	            .message("Erro ao salvar sessao")
	            .build();
			}
		}

	@Override
	public List<Sessao> identificaSessaoFinalizada() throws ApiException {
		try {
			
			return sessaoRepository.findBySessaoFinalizada();
			
		} catch (Exception e) {
			log.error("Erro ao identificar sessao finalizada - " + e);
            throw ApiException.builder()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .code(ApiException.VALIDATION_ERROR)
            .message("Erro ao identificar sessao finalizada")
            .build();

		}
	}
	
	@Override
	public void atualizaSessaoFinalizada(Sessao sessao) throws ApiException {
		try {
			
			sessaoRepository.saveAndFlush(sessao);
			
		} catch (Exception e) {
			log.error("Erro ao identificar sessao finalizada - " + e);
            throw ApiException.builder()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .code(ApiException.VALIDATION_ERROR)
            .message("Erro ao identificar sessao finalizada")
            .build();

		}
	}
}
