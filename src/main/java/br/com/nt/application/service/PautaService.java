package br.com.nt.application.service;

import br.com.nt.application.DTO.PautaDto;
import br.com.nt.application.exception.ApiException;


public interface PautaService {
	
	/**
	 * Adicionar nova pauta
	 * @param pauta
	 * @return
	 * @throws ApiException
	 */
	 PautaDto adicionar (PautaDto pauta) throws  ApiException;
	
}
