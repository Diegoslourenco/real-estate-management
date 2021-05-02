package com.gft.imobiliaria.repository.filter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * ImovelFilter --- represents the text in the search for a Imovel in the repository.
 * @author    Diego da Silva Lourenco
 */

public class ImovelFilter {
	
	private String text;
	
	@Enumerated(EnumType.STRING)
	private FieldsImovelFilter field;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public FieldsImovelFilter getField() {
		return field;
	}

	public void setField(FieldsImovelFilter field) {
		this.field = field;
	}
}
