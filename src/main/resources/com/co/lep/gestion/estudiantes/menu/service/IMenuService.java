package com.co.flexicraftsolutions.gestion.estudiantes.menu.service;

import java.util.List;

import com.co.flexicraftsolutions.gestion.estudiantes.menu.entity.MenuEntity;

public interface IMenuService {

	List<MenuEntity> getMenusByRole(String currentUserRole);

}
