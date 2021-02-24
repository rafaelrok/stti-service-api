package br.com.serviceapi.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serviceapi.domain.model.Cliente;
import br.com.serviceapi.domain.repository.ClienteRepositoty;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepositoty clienteRepo;
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepo.findAll();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
	   Optional<Cliente> cliente = clienteRepo.findById(clienteId);
	   
	   if(cliente.isPresent()) {
		   return ResponseEntity.ok(cliente.get());
	   }
	   
	   return ResponseEntity.notFound().build();		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente Adiconar(@Valid @RequestBody Cliente cliente) {
		return clienteRepo.save(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId,
		@RequestBody Cliente cliente){
		
		if(!clienteRepo.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		cliente = clienteRepo.save(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if(!clienteRepo.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteRepo.deleteById(clienteId);
		
		return ResponseEntity.noContent().build();
	}
	

}
