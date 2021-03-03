package br.com.serviceapi.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serviceapi.api.model.OrdemServicoDTO;
import br.com.serviceapi.domain.model.OrdemServico;
import br.com.serviceapi.domain.repository.OrdemServicoRepository;
import br.com.serviceapi.domain.service.OrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private OrdemServicoService osServico;
	
	@Autowired
	private OrdemServicoRepository osRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private OrdemServico criar(@Valid @RequestBody OrdemServico ordemServico) {
		return osServico.criar(ordemServico);

	}
	
	@GetMapping
	public List<OrdemServico> listar(){
	 return  osRepo.findAll();
	}
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoDTO> buscar(@PathVariable Long ordemServicoId){
		Optional<OrdemServico> ordemServico = osRepo.findById(ordemServicoId);
		
		if(ordemServico.isPresent()) {
			OrdemServicoDTO dto = modelMapper.map(ordemServico.get(), OrdemServicoDTO.class);
			return ResponseEntity.ok(dto);
		}
		return ResponseEntity.notFound().build();
	}
}
