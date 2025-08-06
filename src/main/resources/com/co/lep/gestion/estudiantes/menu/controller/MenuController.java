package com.co.flexicraftsolutions.gestion.estudiantes.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.flexicraftsolutions.gestion.estudiantes.constantes.Constantes;
import com.co.flexicraftsolutions.gestion.estudiantes.constantes.Mensajes;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.EntityResponse;
import com.co.flexicraftsolutions.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.flexicraftsolutions.gestion.estudiantes.menu.dto.MenuDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.menu.impl.service.MenuService;

@RestController
@RequestMapping(Constantes.URL_BASE)
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping(value = Constantes.ENDPOINT_MENU_APP_USER)
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<EntityResponse<List<MenuDTO>>>getMenusForCurrentUser() {
    	try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserRole = authentication.getAuthorities().iterator().next().getAuthority().replace("ROLE_", ""); // Eliminar el prefijo "ROLE_"
            List<MenuDTO> menuRole = menuService.getMenus(currentUserRole);
           
            EntityResponse<List<MenuDTO>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(menuRole);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<List<MenuDTO>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<MenuDTO>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    	
    	
    }
}

