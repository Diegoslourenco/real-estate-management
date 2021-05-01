package com.gft.imobiliaria.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Bairro --- represents a district in a city.
 * @author    Diego da Silva Lourenco
 */

@Entity
@Table(name = "bairros")
public class Bairro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(max = 20, message = "Bairro não pode conter mais de 20 caracteres")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	@NotNull(message = "Município deve ser selecionado")
	private Municipio city;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	@NotNull(message = "Estado deve ser selecionado")
	private Estado state;

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
	
	public Municipio getCity() {
		return city;
	}

	public void setCity(Municipio city) {
		this.city = city;
	}

	public Estado getState() {
		return state;
	}

	public void setState(Estado state) {
		this.state = state;
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
		Bairro other = (Bairro) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
