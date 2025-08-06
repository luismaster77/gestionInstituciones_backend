package com.co.lep.gestion.estudiantes.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.co.lep.gestion.estudiantes.security.entity.User;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	public Optional<User> findById(long id);

	public List<User> findAll();
	
	@SuppressWarnings("unchecked")
	public User save(User user);

	@Query("SELECT e FROM User e WHERE "
			+ "(:tipoDocum IS NULL OR e.tipoDocumId.id = :tipoDocum) "
			+ "AND (:codDocum IS NULL OR e.codDocum = :codDocum) "
			+ "AND (:nomUsuario IS NULL OR e.primerNombre LIKE CONCAT('%', :nomUsuario, '%')) "
			+ "AND (:institucion IS NULL OR e.institucionId.id = :institucion) "
			+ "AND (:role IS NULL OR e.roleId.id = :role) "
			+ "AND (:estado IS NULL OR e.estadoId.id = :estado)")
	List<User> consultarUsuario(
			@Param("tipoDocum") Long tipoDocum,
			@Param("codDocum") String codDocum, 
			@Param("nomUsuario") String primerNombre, 
			@Param("institucion")Long institucion, 
			@Param("role") Long role,
			@Param("estado") Long estado);

	Optional<User> findByEmail(String email);
}
