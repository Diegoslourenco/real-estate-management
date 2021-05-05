package com.gft.imobiliaria.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ImobiliariaUserDetailService imobiliariaUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers(
				"/registrar**",
				"/resources/**",
				"/webjars/**",
				"/js/**",
				"/css/**",
				"/images/**").permitAll()
		.antMatchers(
				"/imoveis/pesquisar**").hasAnyRole("user", "admin")
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/login").permitAll()
			.and()
			.rememberMe()
		.and()
		.logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout")
			.permitAll()
			.and().csrf().disable();;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder
			.userDetailsService(imobiliariaUserDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	 public static void main(String[] args) {
		    System.out.println(new BCryptPasswordEncoder().encode("Gft2021"));
	}
}
