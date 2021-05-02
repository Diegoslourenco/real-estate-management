package com.gft.imobiliaria.repository.filter;

/**
 * FieldsImovelFilter --- contains the possible fields to filter for a real estate.
 * @author    Diego da Silva Lourenco
 */

public enum FieldsImovelFilter {
	
	NAME("Nome"),
	BEDROOM("Quarto"),
	BUSINESS("Negócio"),
	CATEGORY("Categoria"),
	ADDRESS("Endereço"),
	DISTRICT("Bairro"),
	CITY("Município"),
	STATE("Estado");
	
	private String description;
	
	FieldsImovelFilter(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
