package com.co.flexicraftsolutions.gestion.estudiantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.co.flexicraftsolutions.gestion.estudiantes.security.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	 Role findByName(String name);
}
