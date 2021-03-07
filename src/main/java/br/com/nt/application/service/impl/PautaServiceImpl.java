package br.com.nt.application.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.nt.application.DTO.PautaDto;
import br.com.nt.application.exception.ApiException;
import br.com.nt.application.service.PautaService;
import br.com.nt.domain.entity.Pauta;
import br.com.nt.domain.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class PautaServiceImpl implements PautaService {
	
	private final PautaRepository pauta;
	private final ModelMapper mapper;
	
	 
	/**
	 * Metodo para verificar se ja existe uuma pauta, caso nao cria uma nova
	 * @param pauta
	 * @return pautaDto
	 * @throws ApiException
	 */
	public PautaDto adicionar (PautaDto pautas) throws  ApiException {

		try {
			PautaDto ret =  new PautaDto();
			Pauta existePauta = pauta.findByTitulo(pautas.getTitulo()).orElse(null);
			
			if(existePauta != null ) {
				BeanUtils.copyProperties(existePauta, ret);
			}else {
				Pauta savePauta = pauta.saveAndFlush( mapper.map(pautas, Pauta.class) );
				BeanUtils.copyProperties(savePauta, ret);
			}
			return ret;
		} catch (Exception e) {
			log.info("Erro ao salvar pauta - " + e);
            throw ApiException.builder()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .code(ApiException.VALIDATION_ERROR)
            .message("Erro ao salvar Pauta")
            .build();
		}
	}

}