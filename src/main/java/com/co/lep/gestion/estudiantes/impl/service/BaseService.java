package com.co.lep.gestion.estudiantes.impl.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.co.lep.gestion.estudiantes.entity.InstitucionEntity;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.repository.InstitucionRepository;
import com.co.lep.gestion.estudiantes.repository.UserRepository;
import com.co.lep.gestion.estudiantes.security.context.UserContext;
import com.co.lep.gestion.estudiantes.security.entity.User;
import com.co.lep.gestion.estudiantes.utilidades.Validador;

public abstract class BaseService {
	
		@Autowired
		UserRepository userRepository;
		
		@Autowired
		InstitucionRepository institucionRepository;
	    
	    protected User obtenerUsuarioApp() {
			return userRepository.findById(UserContext.getCurrentUserId())
			.orElseThrow(() -> new RegistroNoEncontradoException("Usuario no encontrado"));
		}
		
	    protected InstitucionEntity obtenerInstitucionUsuario() {
			if(!Validador.esNuloOVacio(UserContext.getcurrentInstitucionUserId())) {
				return institucionRepository.findById(UserContext.getcurrentInstitucionUserId())
						.orElseThrow(() -> new RegistroNoEncontradoException("Institucion no encontrada"));			
			}
			return new InstitucionEntity();
		}
}
