package com.co.lep.gestion.institucion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.constantes.Mensajes;
import com.co.lep.gestion.estudiantes.entity.EntityResponse;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.institucion.service.impl.CertificadoSrv;

@RestController
@RequestMapping(Constantes.URL_BASE)
public class GestionAdminController {
	
	@Autowired
	private CertificadoSrv certificadoSrv;
	
	
	@PostMapping(value= Constantes.GENERAR_CERTIFICADO_PDF)
    public ResponseEntity<EntityResponse<byte[]>> generarCertificado(@RequestBody Long estudianteId){
    	try {
			
			byte[] boletinPDF = certificadoSrv.generarCertificadoPDF(estudianteId);
			
			EntityResponse<byte[]> response = new EntityResponse<>();
	        
			response.setSuccess(true);
			response.setData(boletinPDF);
			return ResponseEntity.ok(response);
			
		} catch (RegistroNoGuardadoException ex) {
	    	EntityResponse<byte[]> errorResponse = new EntityResponse<>();
	        errorResponse.setSuccess(false);
	        errorResponse.setMessage(ex.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    } catch (Exception e) {
	    	EntityResponse<byte[]> errorResponse = new EntityResponse<>();
	        errorResponse.setSuccess(false);
	        errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
}
