package br.com.nt.application.service;

import br.com.nt.application.DTO.SessaoDto;
import br.com.nt.application.exception.ApiException;


public interface SessaoService {
	
	/**
	 * Abrir nova Sessao
	 * @param sessao
	 * @return
	 * @throws ApiException
	 */
	SessaoDto adicionar (SessaoDto sessao) throws  ApiException;
	
	/**
	 * validar sessao
	 * @param sessao
	 * @return
	 * @throws ApiException
	 */
	void validaSessao(SessaoDto sessao) throws ApiException ;
	
}
