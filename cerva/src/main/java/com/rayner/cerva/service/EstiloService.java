package com.rayner.cerva.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rayner.cerva.model.Estilo;
import com.rayner.cerva.repository.EstiloRepository;
import com.rayner.cerva.service.exception.NomeEstiloJaCadastradoException;

@Service
public class EstiloService {

	@Autowired
	private EstiloRepository estiloRepository;

	@Transactional
	public Estilo salvar(Estilo estilo) {

		Optional<Estilo> estiloBusca = estiloRepository.findByNomeIgnoreCase(estilo.getNome());
		if (estiloBusca.isPresent()) {
			throw new NomeEstiloJaCadastradoException("Nome do estilo já está cadastrado");
		} else {
			return estiloRepository.saveAndFlush(estilo);
		}
	}

}
