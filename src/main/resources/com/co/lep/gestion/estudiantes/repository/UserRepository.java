package com.co.lep.gestion.estudiantes.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.co.lep.gestion.estudiantes.security.entity.User;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	public Optional<User> findById(long id);

	public List<User> findAll();
	
	@SuppressWarnings("unchecked")
	public User save(User user);
}
