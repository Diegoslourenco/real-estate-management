package com.gft.imobiliaria.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Usuario --- represents a user that has acess to the system.
 * @author    Diego da Silva Lourenco
 */

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = { "email", "username"}) )
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(max = 40, message = "Nome não pode conter mais de 40 caracteres")
	private String name;
	
	@NotBlank(message = "Email é obrigatório")
	@Size(max = 40, message = "Email não deve conter mais de 40 caracteres")
	@Email(message = "Email deve estar no formato correto")
	private String email;
	
	@NotBlank(message = "Usuário é obrigatório")
	@Size(max = 40, message = "Usuário não pode conter mais de 40 caracteres")
	private String username;
	
	@NotBlank(message = "Senha é obrigatória")
	private String password;
	
	private boolean active;

	private String permission;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
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
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", name=" + name + ", username=" + username + "]";
	}
}
