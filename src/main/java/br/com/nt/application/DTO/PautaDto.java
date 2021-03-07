package br.com.nt.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class PautaDto{

	 private Long id;
	 private String titulo;
	 private String descricao;
		
}
