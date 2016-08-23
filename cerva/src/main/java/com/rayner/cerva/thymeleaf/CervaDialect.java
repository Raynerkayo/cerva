package com.rayner.cerva.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.rayner.cerva.thymeleaf.processor.ClassForErrorAttributeTagProcessor;

public class CervaDialect extends AbstractProcessorDialect{

	public CervaDialect() {
		super("Rayner Cerva", "cerva", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

	
	
}
