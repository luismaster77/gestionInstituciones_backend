package com.co.lep.gestion.estudiantes.menu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.lep.gestion.estudiantes.menu.entity.ItemMenuEntity;

public interface ItemMenuRepository extends JpaRepository<ItemMenuEntity, Long>{
	Optional<ItemMenuEntity> findById(Long menuId);
	
	List<ItemMenuEntity> findByIdIn(List<Long> ids);
}
