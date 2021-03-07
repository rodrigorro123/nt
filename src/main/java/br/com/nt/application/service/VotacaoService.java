package br.com.nt.application.service;

import java.util.Map;

import br.com.nt.application.DTO.VotoDto;
import br.com.nt.application.exception.ApiException;


public interface VotacaoService {
	
	/**
	 * Realiza votacao
	 * @param sessao
	 * @return
	 * @throws ApiException
	 */
	String adicionaVoto (VotoDto voto) throws  ApiException;
	

	/**
	 * realiza pre-validação
	 * @param voto
	 * @return
	 * @throws ApiException
	 */
	Boolean validaVotacao(VotoDto voto) throws ApiException;
	
	/**
	 * informa qtde de votos da sessao
	 * @return
	 * @throws ApiException
	 */
	Map<String, Long> getVotacao(Long idSessao) throws ApiException;
	
	 
}
