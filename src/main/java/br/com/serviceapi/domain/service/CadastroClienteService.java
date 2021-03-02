package br.com.serviceapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serviceapi.domain.exception.NegocioException;
import br.com.serviceapi.domain.model.Cliente;
import br.com.serviceapi.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepo;
	
	public Cliente salvar(Cliente cliente) {
		Cliente clienteExistente = clienteRepo.findByEmail(cliente.getEmail());
		if(clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já Existe um cliente com esse email cadastrado.");
		}
		
		
		return clienteRepo.save(cliente);
	}
	
	public void deletar(Long clienteId) {
		clienteRepo.deleteById(clienteId);
		
	}
	
}
