package com.co.lep.gestion.estudiantes.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.constantes.Mensajes;
import com.co.lep.gestion.estudiantes.dto.InstitucionDTO;
import com.co.lep.gestion.estudiantes.entity.EntityResponse;
import com.co.lep.gestion.estudiantes.entity.InstitucionEntity;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.service.IAdminService;

@RestController
@RequestMapping(Constantes.URL_BASE)
public class AdminInstitucionController {

	@Autowired
	IAdminService iAdminService;

	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_INSTITUCION)
	public ResponseEntity<EntityResponse<List<InstitucionEntity>>> consultarInstitucion(
			@Valid @RequestBody InstitucionDTO institucionDTO) {
		try {
			
			List<InstitucionEntity> institucionList = iAdminService.consultarInstitucion(institucionDTO);

			EntityResponse<List<InstitucionEntity>> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setData(institucionList);
			return ResponseEntity.ok(response);

		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<List<InstitucionEntity>> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<List<InstitucionEntity>> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_INSTITUCION_BY_ID)
	public ResponseEntity<EntityResponse<InstitucionEntity>> consultarInstitucionById(
			@Valid @RequestBody InstitucionDTO institucionDTO) {
		try {

			InstitucionEntity institucion = iAdminService.consultarInstitucionById(institucionDTO);

			EntityResponse<InstitucionEntity> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setData(institucion);
			return ResponseEntity.ok(response);

		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<InstitucionEntity> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<InstitucionEntity> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping(value = Constantes.ENDPOINT_CREAR_INSTITUCION)
	public ResponseEntity<EntityResponse<InstitucionDTO>> crearInstitucion(
			@Valid @RequestBody InstitucionDTO institucionDTO) {
		try {

			iAdminService.crearInstitucion(institucionDTO);

			EntityResponse<InstitucionDTO> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setMessage(Mensajes.TXT_CREAR_REGISTRO_OK);
			return ResponseEntity.ok(response);

		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<InstitucionDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<InstitucionDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping(value = Constantes.ENDPOINT_EDITAT_INSTITUCION)
	public ResponseEntity<EntityResponse<InstitucionDTO>> editarInstitucion(
			@Valid @RequestBody InstitucionDTO institucionDTO) {
		try {

			iAdminService.actualizarInstitucion(institucionDTO);

			EntityResponse<InstitucionDTO> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setMessage(Mensajes.TXT_EDITAR_REGISTRO_OK);
			return ResponseEntity.ok(response);

		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<InstitucionDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<InstitucionDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping(value = Constantes.ENDPOINT_ELIMINAR_INSTITUCION)
	public ResponseEntity<EntityResponse<InstitucionDTO>> eliminarInstitucion(
			@Valid @RequestBody InstitucionDTO institucionDTO) {
		try {

			iAdminService.eliminarInstitucion(institucionDTO);

			EntityResponse<InstitucionDTO> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setMessage(Mensajes.TXT_REGISTRO_ELIMINADO_OK);
			return ResponseEntity.ok(response);

		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<InstitucionDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<InstitucionDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

}
