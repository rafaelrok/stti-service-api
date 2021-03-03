package br.com.serviceapi.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serviceapi.domain.exception.NegocioException;
import br.com.serviceapi.domain.model.Cliente;
import br.com.serviceapi.domain.model.OrdemServico;
import br.com.serviceapi.domain.model.StatusOrdemServico;
import br.com.serviceapi.domain.repository.ClienteRepository;
import br.com.serviceapi.domain.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository osRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepo.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado."));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTO);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return osRepo.save(ordemServico);
	}
}
