package com.rayner.cerva.service.exception;

public class SkuJaExistente extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SkuJaExistente(String mensagem) {
		super(mensagem);
	}

}
