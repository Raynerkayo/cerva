package com.rayner.cerva.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rayner.cerva.model.Cerveja;
import com.rayner.cerva.repository.CervejaRepository;
import com.rayner.cerva.service.exception.SkuJaExistente;

@Service
public class CervejaService {

	@Autowired
	private CervejaRepository cervejas;

	@Transactional
	public void salvar(Cerveja cerveja) {

		Optional<Cerveja> buscaSku = cervejas.findBySkuIgnoreCase(cerveja.getSku());
		if (buscaSku.isPresent()) {
			throw new SkuJaExistente("SKU já cadastrado");
		} else {
			cervejas.save(cerveja);
		}
	}

}
