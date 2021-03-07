package br.com.nt.application.service;

import br.com.nt.application.exception.ApiException;

public interface MQService {
	
	/**
	 * notifica fila com finalizacao da fila 
	 * @param idSessao
	 * @return
	 */
	 boolean sendQueue(Long idSessao) throws ApiException ;
}
