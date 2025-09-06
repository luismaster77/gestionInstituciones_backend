package com.co.lep.gestion.estudiantes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.constantes.Mensajes;
import com.co.lep.gestion.estudiantes.entity.BoletinConfiguracionEntity;
import com.co.lep.gestion.estudiantes.entity.EntityResponse;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.service.impl.BoletinConfiguracionService;

@RestController
@RequestMapping(value = Constantes.URL_BASE)
public class BoletinConfiguracionController {

    @Autowired
    private BoletinConfiguracionService configuracionService;
    
    public static class ConfiguracionRequest {
        public Long institucionId;
        public List<BoletinConfiguracionEntity> configuracion;
    }

	// Obtener todas las columnas de la tabla boletines
	@GetMapping(value = Constantes.ENDPOINT_CONSULTAR_COLUMNAS)
	public  ResponseEntity<EntityResponse<List<BoletinConfiguracionEntity>>> obtenerColumnas() {
		try {

			List<BoletinConfiguracionEntity> configuracionesList = configuracionService.obtenerConfiguracionCompleta();

			EntityResponse<List<BoletinConfiguracionEntity>> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setData(configuracionesList);
			return ResponseEntity.ok(response);

		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<List<BoletinConfiguracionEntity>> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<List<BoletinConfiguracionEntity>> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@GetMapping(value = Constantes.ENDPOINT_CONSULTAR_INFO_CONFIGURACION_BOLETIN)
	public ResponseEntity<EntityResponse<List<BoletinConfiguracionEntity>>> obtenerConfiguracion(@RequestParam Long institucionId) {
	    try {
	        List<BoletinConfiguracionEntity> configuracion = configuracionService.obtenerConfiguracionPorInstitucion(institucionId);

	        EntityResponse<List<BoletinConfiguracionEntity>> response = new EntityResponse<>();
	        response.setSuccess(true);
	        response.setData(configuracion);
	        return ResponseEntity.ok(response);

	    } catch (Exception e) {
	        EntityResponse<List<BoletinConfiguracionEntity>> errorResponse = new EntityResponse<>();
	        errorResponse.setSuccess(false);
	        errorResponse.setMessage("Error al obtener la configuración");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	    }
	}

    @PutMapping(value = Constantes.ENDPOINT_GUARDAR_CONFIGURACION_BOLETIN)
    public ResponseEntity<EntityResponse<String>> guardarConfiguracion(@RequestBody ConfiguracionRequest request) {
    	try {

    		configuracionService.guardarConfiguracion(request.configuracion, request.institucionId);

			EntityResponse<String> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setData(null);
			response.setMessage(Mensajes.TXT_CREAR_REGISTRO_OK);
			return ResponseEntity.ok(response);

		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<String> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<String> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
}

