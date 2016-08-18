package com.rayner.cerva.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.rayner.cerva.service.CervejaService;

@Configuration
@ComponentScan(basePackageClasses = CervejaService.class)
public class ServiceConfig {

	
	
}
