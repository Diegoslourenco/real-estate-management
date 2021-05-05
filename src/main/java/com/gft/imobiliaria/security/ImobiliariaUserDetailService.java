package com.gft.imobiliaria.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gft.imobiliaria.model.Usuario;
import com.gft.imobiliaria.repository.UsuariosRepository;

@Component
public class ImobiliariaUserDetailService implements UserDetailsService {
	
	@Autowired
	private UsuariosRepository usuarios;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarios.findByUsername(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		return new UsuarioSistema(usuario.getName(), usuario.getEmail(), usuario.getUsername(), usuario.getPassword(), authorities(usuario));
	}
	
	public Collection<? extends GrantedAuthority> authorities(Usuario usuario) {
		
		Collection<GrantedAuthority> auths = new ArrayList<>();
		
		auths.add(new SimpleGrantedAuthority("ROLE_" + usuario.getPermission()));
		
		return auths;
	}
}
