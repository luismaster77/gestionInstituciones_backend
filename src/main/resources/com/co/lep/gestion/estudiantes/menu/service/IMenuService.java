package com.co.lep.gestion.estudiantes.menu.service;

import java.util.List;

import com.co.lep.gestion.estudiantes.menu.entity.MenuEntity;

public interface IMenuService {

	List<MenuEntity> getMenusByRole(String currentUserRole);

}
