package br.com.nt.application.DTO;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class SessaoDto{

	 private Long id;
		
	 private String titulo;
	
	 private String descricao;
	
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	 private LocalDateTime dtValidade;
	
	@NotNull
	 private PautaDto pauta;
	
}
