package br.com.nt.application.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.nt.application.DTO.UserDto;

@FeignClient(name = "UsersClient", url = "${integration.user.baseUrl}")
public interface UserClient {
	
	@GetMapping(value = "/users/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	 UserDto getUserPermission(@PathVariable(value = "cpf") String cpf);

}
