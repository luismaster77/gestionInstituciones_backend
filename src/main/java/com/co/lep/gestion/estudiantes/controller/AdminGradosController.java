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
import com.co.lep.gestion.estudiantes.dto.GradoDTO;
import com.co.lep.gestion.estudiantes.entity.EntityResponse;
import com.co.lep.gestion.estudiantes.entity.GradoEntity;
import com.co.lep.gestion.estudiantes.entity.MateriaEntity;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.service.IAdminService;


@RestController
@RequestMapping(Constantes.URL_BASE)
public class AdminGradosController {

	@Autowired
	IAdminService iAdminService;

	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_GRADO)
	public ResponseEntity<EntityResponse<List<GradoEntity>>> consultarGrado(@Valid @RequestBody GradoDTO gradoDTO) {
		try {

			List<GradoEntity> gradosList = iAdminService.consultarGrado(gradoDTO);

			EntityResponse<List<GradoEntity>> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setData(gradosList);
			return ResponseEntity.ok(response);

		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<List<GradoEntity>> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<List<GradoEntity>> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_GRADO_BY_ID)
	public ResponseEntity<EntityResponse<GradoEntity>> consultarGradoById(@Valid @RequestBody GradoDTO gradoDTO) {
		try {

			GradoEntity gradoExistente = iAdminService.consultarGradoById(gradoDTO);

			EntityResponse<GradoEntity> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setData(gradoExistente);
			return ResponseEntity.ok(response);

		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<GradoEntity> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<GradoEntity> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_MATERIAS_GRADO_BY_ID)
	public ResponseEntity<EntityResponse<List<MateriaEntity>>> consultarMateriasGradoById(@Valid @RequestBody GradoDTO gradoDTO) {
		try {

			List<MateriaEntity> gradoMateriasList = iAdminService.consultarMateriasGradoById(gradoDTO.getId());

			EntityResponse<List<MateriaEntity>> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setData(gradoMateriasList);
			return ResponseEntity.ok(response);

		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<List<MateriaEntity>> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<List<MateriaEntity>> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping(value = Constantes.ENDPOINT_CREAR_GRADO)
	public ResponseEntity<EntityResponse<Object>> crearGrado(@Valid @RequestBody GradoDTO gradoDTO) {
		try {

			iAdminService.crearGrado(gradoDTO);

			EntityResponse<Object> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setMessage(Mensajes.TXT_CREAR_REGISTRO_OK);
			return ResponseEntity.ok(response);

		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<Object> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<Object> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping(value = Constantes.ENDPOINT_EDITAT_GRADO)
	public ResponseEntity<EntityResponse<Object>> editarGrado(@Valid @RequestBody GradoDTO gradoDTO) {
		try {

			iAdminService.actualizarGrado(gradoDTO);

			EntityResponse<Object> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setMessage(Mensajes.TXT_EDITAR_REGISTRO_OK);
			return ResponseEntity.ok(response);

		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<Object> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<Object> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping(value = Constantes.ENDPOINT_ELIMINAR_GRADO)
	public ResponseEntity<EntityResponse<Object>> eliminarGrado(@Valid @RequestBody GradoDTO gradoDTO) {
		try {

			iAdminService.eliminarGrado(gradoDTO);

			EntityResponse<Object> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setMessage(Mensajes.TXT_REGISTRO_ELIMINADO_OK);
			return ResponseEntity.ok(response);

		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<Object> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<Object> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

}
