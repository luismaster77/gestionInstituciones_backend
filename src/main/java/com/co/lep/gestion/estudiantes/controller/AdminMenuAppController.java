package com.co.lep.gestion.estudiantes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.lep.gestion.estudiantes.service.IAdminService;
import com.co.lep.gestion.estudiantes.constantes.Constantes;

@RestController
@RequestMapping(Constantes.URL_BASE)
public class AdminMenuAppController {
	
	@Autowired
	IAdminService iAdminService;
    
}
