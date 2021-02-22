package br.com.serviceapi.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serviceapi.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Rafael");
		cliente1.setEmail("rafael@teste.com.br");
		cliente1.setTelefone("21 96897-3443");
		
		var cliente2 = new Cliente();	
		cliente2.setId(2L);
		cliente2.setNome("Mayara");
		cliente2.setEmail("mayara@teste.com.br");
		cliente2.setTelefone("21 99183-0116");
		
		return Arrays.asList(cliente1, cliente2);
	}

}
