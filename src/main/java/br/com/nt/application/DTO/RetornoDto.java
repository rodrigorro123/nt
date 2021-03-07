package br.com.nt.application.DTO;

import java.io.Serializable;

import lombok.Data;
/**
 * Classe dto com parametros de retorno
 * @author rodrigo
 *
 */
@Data
public class RetornoDto implements  Serializable {
	
	private static final long serialVersionUID = -9124742998239073507L;

	private Long id_retorno;
	private String Descricao;
}
