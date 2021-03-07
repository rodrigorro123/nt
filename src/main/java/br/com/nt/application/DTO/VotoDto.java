package br.com.nt.application.DTO;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class VotoDto{

	 @NotNull
	 private Long idSessao;
	
	 @NotNull
	 private String cpf;
	 
	 @NotNull
	 private TipoVotoEnum voto;
	
	
	
}
