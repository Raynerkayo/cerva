package com.rayner.cerva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rayner.cerva.model.Estilo;

@Repository
public interface EstiloRepository extends JpaRepository<Estilo, Long> {

	
	
}
