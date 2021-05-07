package com.gft.imobiliaria.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Imagem --- represents a image from a real estate.
 * @author    Diego da Silva Lourenco
 */

@Entity
@Table(name = "imagens")
public class Imagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String location;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "real_estate_id")
	private Imovel realEstate;
	
	public Imagem() {}

	public Imagem(String name, String location, Imovel realEstate) {
		this.name = name;
		this.location = location;
		this.realEstate = realEstate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Imovel getRealState() {
		return realEstate;
	}

	public void setRealState(Imovel realState) {
		this.realEstate = realState;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Imagem other = (Imagem) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
