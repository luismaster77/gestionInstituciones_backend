package com.co.flexicraftsolutions.gestion.estudiantes.menu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.co.flexicraftsolutions.gestion.estudiantes.menu.entity.MenuEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.menu.entity.RoleMenuEntity;

@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenuEntity, Long> {
//	@Query("SELECT DISTINCT me FROM MenuEntity me "
//			+ "INNER JOIN RoleMenuEntity rm ON rm.menu.id = me.id "
//			+ "LEFT JOIN RoleEntity rl ON rl.id = rm.role.id "
//			+ "LEFT JOIN ItemMenuEntity ima ON ima.id = me.items.id "
//			+ "WHERE rl.name = :roleName ")
	@Query("SELECT DISTINCT me, me.icon, ima FROM MenuEntity me "
	            + "INNER JOIN RoleMenuEntity rm ON rm.menu.id = me.id "
	            + "LEFT JOIN RoleEntity rl ON rl.id = rm.role.id "
	            + "LEFT JOIN me.items ima "
	            + "LEFT JOIN ima.icon "
	            + "WHERE rl.name = :roleName")
	List<MenuEntity> findRoleMenuLabelsByRoleName(@Param("roleName") String roleName);
}
