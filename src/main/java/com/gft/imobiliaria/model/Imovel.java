package com.gft.imobiliaria.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * Imovel --- represents a real estate.
 * @author    Diego da Silva Lourenco
 */

@Entity
@Table(name = "imoveis")
public class Imovel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(max = 40, message = "Nome não pode conter mais de 40 caracteres")
	private String name;
	
	@NotNull(message = "A quantidade de quartos não pode ser zero")
	@Positive
	private int bedroom;
	
	@NotBlank(message = "Endereço é obrigatório")
	@Size(max = 80, message = "Endereço não pode conter mais de 80 caracteres")
	private String address;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_id")
	@NotNull(message = "Negócio deve ser selecionado")
	private Negocio business;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@NotNull(message = "Categoria deve ser selecionada")
	private Categoria category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id")
	@NotNull(message = "Bairro deve ser selecionado")
	private Bairro district;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	@NotNull(message = "Município deve ser selecionado")
	private Municipio city;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	@NotNull(message = "Estado deve ser selecionado")
	private Estado state;
	
	@OneToMany(mappedBy = "realEstate", cascade = CascadeType.ALL)
	private List<Imagem> imagens;

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

	public int getBedroom() {
		return bedroom;
	}

	public void setBedroom(int bedroom) {
		this.bedroom = bedroom;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Negocio getBusiness() {
		return business;
	}

	public void setBusiness(Negocio business) {
		this.business = business;
	}

	public Categoria getCategory() {
		return category;
	}

	public void setCategory(Categoria category) {
		this.category = category;
	}

	public Bairro getDistrict() {
		return district;
	}

	public void setDistrict(Bairro district) {
		this.district = district;
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

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
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
		Imovel other = (Imovel) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}
