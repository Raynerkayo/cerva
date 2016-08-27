package com.rayner.cerva.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.rayner.cerva.service.CervejaService;
import com.rayner.cerva.storage.FotoStorage;
import com.rayner.cerva.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CervejaService.class)
public class ServiceConfig {

	@Bean
	public FotoStorage fotoStorageLocal(){
		return new FotoStorageLocal();
	}
	
}
