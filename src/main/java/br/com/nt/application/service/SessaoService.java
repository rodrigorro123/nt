package br.com.nt.application.service;

import java.util.List;

import br.com.nt.application.DTO.SessaoDto;
import br.com.nt.application.exception.ApiException;
import br.com.nt.domain.entity.Sessao;


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
	
	/**
	 * busca as sessoes finalizadas
	 * @return
	 * @throws ApiException
	 */
	List<Sessao> identificaSessaoFinalizada() throws ApiException ;
	
	/***
	 * atualiza as sessoes ja finalizadas 
	 * @param sessao
	 * @throws ApiException
	 */
	public void atualizaSessaoFinalizada(Sessao sessao) throws ApiException ;
	
}
