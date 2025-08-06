package com.co.lep.gestion.estudiantes.security.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.co.lep.gestion.estudiantes.constantes.Mensajes;
import com.co.lep.gestion.estudiantes.repository.UserRepository;
import com.co.lep.gestion.estudiantes.security.entity.User;

@Configuration
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
	  Optional<User> userOptional = userRepository.findByUsername(username);
      if (userOptional.isEmpty()) {
          throw new UsernameNotFoundException(Mensajes.TXT_USUARIO_NOT_FOUND + username);
      }
      User user = userOptional.get();
      // Construye y devuelve los detalles del usuario
      return User.builder()
              .username(user.getUsername())
              .password(user.getPassword())
              .roleId(user.getRoleId())
              .cambioPassword(user.getCambioPassword())
              .build();
  }
}
