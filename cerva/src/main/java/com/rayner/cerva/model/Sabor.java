package com.rayner.cerva.model;

public enum Sabor {

	ADOCICADA("Adocicada"),
	AMARGA("Amarga"),
	FORTE("Forte"),
	FRUTADA("Frutada"),
	SUAVE("Suave");
	
	private String descricao;
	
	Sabor(String descricacao){
		this.descricao = descricacao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	
}
