package com.co.lep.gestion.estudiantes.menu.impl.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.lep.gestion.estudiantes.menu.dto.ItemMenuDTO;
import com.co.lep.gestion.estudiantes.menu.dto.MenuDTO;
import com.co.lep.gestion.estudiantes.menu.entity.MenuEntity;
import com.co.lep.gestion.estudiantes.menu.repository.RoleMenuRepository;
import com.co.lep.gestion.estudiantes.menu.service.IMenuService;
import com.co.lep.gestion.estudiantes.repository.RoleRepository;
import com.co.lep.gestion.estudiantes.security.entity.Role;

@Service
public class MenuService implements IMenuService{

	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
	private RoleMenuRepository roleMenuRepository;
	
	
	public List<MenuEntity> getMenusByRole(String roleName) {
		 Role role = roleRepository.findByName(roleName);
	        if (role != null) {
	            return role.getMenus();
	        }
	        return null;
    }
	
	public List<MenuEntity> findMenusByRoleName(String roleName) {
		 List<MenuEntity> role = roleMenuRepository.findRoleMenuLabelsByRoleName(roleName);
	        if (role != null) {
	            return role;
	        }
	        return null;
   }
	
	public List<MenuDTO> getMenus(String roleName) {
		 List<MenuEntity> menus = roleMenuRepository.findRoleMenuLabelsByRoleName(roleName);

	    // Agrupar por idOpcion
	     Map<String, List<MenuEntity>> groupedMenus = menus.stream()
	            .collect(Collectors.groupingBy(MenuEntity::getIdOpcion));

	    // Convertir a DTO
	     return groupedMenus.entrySet().stream()
	    		.sorted(Map.Entry.comparingByKey())
	            .map(entry -> convertToDTO(entry.getValue()))
	            .collect(Collectors.toList());
    }
	
	private MenuDTO convertToDTO(List<MenuEntity> menuEntities) {
        // Suponemos que todas las entidades en la lista tienen la misma información del menú padre
        MenuEntity menuEntity = menuEntities.get(0);

        MenuDTO dto = new MenuDTO();
        dto.setId(menuEntity.getId());
        dto.setIdOpcion(menuEntity.getIdOpcion());
        dto.setLabel(menuEntity.getLabel());
        dto.setIcon(menuEntity.getIcon().getCodIcon());
        dto.setDescripcion(menuEntity.getDescripcion());
        dto.setOrdenMenu(menuEntity.getOrdenMenu());

        // Obtener los items del menú correspondientes
        List<ItemMenuDTO> items = menuEntities.stream()
                .map(this::convertToItemDTO)
                .collect(Collectors.toList());

        dto.setItems(items);
        return dto;
    }

    private ItemMenuDTO convertToItemDTO(MenuEntity menuEntity) {
        ItemMenuDTO dto = new ItemMenuDTO();
        dto.setId(menuEntity.getItems().getId());
        dto.setLabel(menuEntity.getItems().getLabel());
        dto.setIcon(menuEntity.getItems().getIcon().getCodIcon());
        dto.setRouterLink(menuEntity.getItems().getRouterLink());
        return dto;
    }
}
