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
import com.co.lep.gestion.estudiantes.dto.SedeDTO;
import com.co.lep.gestion.estudiantes.entity.EntityResponse;
import com.co.lep.gestion.estudiantes.entity.SedeEntity;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.service.IAdminService;

@RestController
@RequestMapping(Constantes.URL_BASE)
public class AdminSedesController {

	@Autowired
	IAdminService iAdminService;

	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_SEDE)
	public ResponseEntity<EntityResponse<List<SedeEntity>>> consultarSede(@Valid @RequestBody SedeDTO sedesDTO) {
		try {

			List<SedeEntity> sedesList = iAdminService.consultarSede(sedesDTO);

			EntityResponse<List<SedeEntity>> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setData(sedesList);
			return ResponseEntity.ok(response);

		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<List<SedeEntity>> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<List<SedeEntity>> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_SEDE_BY_ID)
	public ResponseEntity<EntityResponse<SedeEntity>> consultarSedeById(@Valid @RequestBody SedeDTO sedesDTO) {
		try {

			SedeEntity sedeExistente = iAdminService.consultarSedeById(sedesDTO);

			EntityResponse<SedeEntity> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setData(sedeExistente);
			return ResponseEntity.ok(response);

		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<SedeEntity> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<SedeEntity> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping(value = Constantes.ENDPOINT_CREAR_SEDE)
	public ResponseEntity<EntityResponse<SedeDTO>> crearSede(@Valid @RequestBody SedeDTO sedesDTO) {
		try {

			iAdminService.crearSede(sedesDTO);

			EntityResponse<SedeDTO> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setMessage(Mensajes.TXT_CREAR_REGISTRO_OK);
			return ResponseEntity.ok(response);

		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<SedeDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<SedeDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping(value = Constantes.ENDPOINT_EDITAR_SEDE)
	public ResponseEntity<EntityResponse<SedeDTO>> editarSede(@Valid @RequestBody SedeDTO sedesDTO) {
		try {

			iAdminService.actualizarSede(sedesDTO);

			EntityResponse<SedeDTO> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setMessage(Mensajes.TXT_EDITAR_REGISTRO_OK);
			return ResponseEntity.ok(response);

		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<SedeDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<SedeDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping(value = Constantes.ENDPOINT_ELIMINAR_SEDE)
	public ResponseEntity<EntityResponse<SedeDTO>> eliminarSede(@Valid @RequestBody SedeDTO sedesDTO) {
		try {

			SedeDTO sedeEliminada = iAdminService.eliminarSede(sedesDTO);

			EntityResponse<SedeDTO> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setData(sedeEliminada);
			return ResponseEntity.ok(response);

		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<SedeDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<SedeDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

}
