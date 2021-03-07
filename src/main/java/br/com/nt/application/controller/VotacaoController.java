package br.com.nt.application.controller;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.nt.application.DTO.Error;
import br.com.nt.application.DTO.VotoDto;
import br.com.nt.application.exception.ApiException;
import br.com.nt.application.service.VotacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(value = "Java dev backend")
@CrossOrigin
@Slf4j
@RequestMapping("/votacao")
@RequiredArgsConstructor
@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Registro processado com sucesso"),
		@ApiResponse(code = 201, message = "Registro criado com sucesso"),
		@ApiResponse(code = 400, message = "Requisiçao invalida"),
		@ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado"),
		@ApiResponse(code = 500, message = "Erro interno")
	     })
public class VotacaoController {

	private final VotacaoService votoService;


	/**
	 * metodo para adicionar registro no banco
	 * @param voto
	 * @return
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "metodo para adicionar registro no banco")
	@Produces(value=MediaType.APPLICATION_JSON)
	public ResponseEntity realizarVotacao (@Valid @RequestBody VotoDto voto) {
			try 
			{
				if(votoService.validaVotacao(voto)) {
					return ResponseEntity
							.status(HttpStatus.CREATED)
							.body(votoService.adicionaVoto(voto));
					
				}else {
					return ResponseEntity
							.status(HttpStatus.BAD_REQUEST)
							.body("Votacao não realizada");
				}
				
		    } catch (ApiException ex) {
		    	log.error(ex.getMessage());
	            return ResponseEntity.status(ex.getStatusCode()).body(Error.builder()
                        .code(ex.getStatusCode().toString())
                        .message(ex.getCode())
                        .description(ex.getMessage())
                        .build());
		    }
	}
	
	@GetMapping(path = "/resultado")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "metodo para adicionar registro no banco")
	@Produces(value=MediaType.APPLICATION_JSON)
	public ResponseEntity getResultVotacao (@RequestParam(required = true, name = "idSessao") Long idSessao) {
			try 
			{
	
					return ResponseEntity
							.status(HttpStatus.OK)
							.body( votoService.getVotacao(idSessao) );
				
		    } catch (ApiException ex) {
		    	log.error(ex.getMessage());
	            return ResponseEntity.status(ex.getStatusCode()).body(Error.builder()
                        .code(ex.getStatusCode().toString())
                        .message(ex.getCode())
                        .description(ex.getMessage())
                        .build());
		    }
	}	
	
}
