package com.co.lep.gestion.estudiantes.impl.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.co.lep.gestion.estudiantes.security.entity.User;

public interface UserService{
	
	UserDetailsService userDetailsService();
	
	User getUser(long id);

	User save(User user);
}
