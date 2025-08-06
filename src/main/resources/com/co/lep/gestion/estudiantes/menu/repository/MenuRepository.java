package com.co.flexicraftsolutions.gestion.estudiantes.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.co.flexicraftsolutions.gestion.estudiantes.menu.entity.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity, Long>{

	@Query("SELECT m FROM MenuEntity m LEFT JOIN FETCH m.items")
	List<MenuEntity> findAllWithItems();

}
