package com.co.lep.gestion.estudiantes.security.context.interceptor;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.security.context.UserContext;
import com.co.lep.gestion.estudiantes.security.entity.User;

@Aspect
@Component
public class UserIdAspect {

    @Before("execution(* com.co.flexicraftsolutions.gestion.estudiantes.service.*.*(..))")
    public void setUserIdInThreadLocal() {
        Long usuarioId = getCurrentUserId();
        Long institucionIdUsuario = getCurrentUserIdInstitucion();
        boolean isAdmin = getCurrentRoleUserId();
        UserContext.setCurrentUserId(usuarioId);
        UserContext.setcurrentInstitucionUserId(institucionIdUsuario);
        UserContext.setcurrentUserAdmin(isAdmin);
    }

	private Long getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            return ((User) userDetails).getId();
        }

        return null;
    }
	
	private boolean getCurrentRoleUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 if (principal instanceof UserDetails) {
	            UserDetails userDetails = (UserDetails) principal;
	            return ((User) userDetails).getRoleId().getName().equals(Constantes.ROLE_ADMIN) ? true : false;
	        }
		return false;
	}
    
    private Long getCurrentUserIdInstitucion() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            return ((User) userDetails).getInstitucionId() != null ? ((User) userDetails).getInstitucionId().getId() : null;
        }

        return null;
    }
}
