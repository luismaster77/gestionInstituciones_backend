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
import com.co.lep.gestion.estudiantes.dto.PeriodoElectivoDTO;
import com.co.lep.gestion.estudiantes.entity.EntityResponse;
import com.co.lep.gestion.estudiantes.entity.PeriodoElectivoEntity;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.service.IAdminService;

@RestController
@RequestMapping(Constantes.URL_BASE)
public class AdminPeriodoElectivoController {

	@Autowired
	IAdminService iAdminService;

	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_PERIODO_ELECTIVO)
	public ResponseEntity<EntityResponse<List<PeriodoElectivoEntity>>> consultarPeriodoElectivon(
			@Valid @RequestBody PeriodoElectivoDTO periodoElectivoDTO) {
		try {

			List<PeriodoElectivoEntity> institucionList = iAdminService.consultarPeriodoElectivo(periodoElectivoDTO);

			EntityResponse<List<PeriodoElectivoEntity>> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setData(institucionList);
			return ResponseEntity.ok(response);

		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<List<PeriodoElectivoEntity>> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<List<PeriodoElectivoEntity>> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_PERIODO_ELECTIVO_BY_ID)
	public ResponseEntity<EntityResponse<PeriodoElectivoEntity>> consultarPeriodoElectivoById(
			@Valid @RequestBody PeriodoElectivoDTO periodoElectivoDTO) {
		try {

			PeriodoElectivoEntity institucion = iAdminService.consultarPeriodoElectivoById(periodoElectivoDTO);

			EntityResponse<PeriodoElectivoEntity> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setData(institucion);
			return ResponseEntity.ok(response);

		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<PeriodoElectivoEntity> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<PeriodoElectivoEntity> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping(value = Constantes.ENDPOINT_CREAR_PERIODO_ELECTIVO)
	public ResponseEntity<EntityResponse<PeriodoElectivoDTO>> crearPeriodoElectivo(
			@RequestBody @Valid PeriodoElectivoDTO periodoElectivoDTO) {
		try {

			iAdminService.crearPeriodoElectivo(periodoElectivoDTO);

			EntityResponse<PeriodoElectivoDTO> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setMessage(Mensajes.TXT_CREAR_REGISTRO_OK);
			return ResponseEntity.ok(response);

		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<PeriodoElectivoDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<PeriodoElectivoDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping(value = Constantes.ENDPOINT_EDITAT_PERIODO_ELECTIVO)
	public ResponseEntity<EntityResponse<PeriodoElectivoDTO>> editarPeriodoElectivo(
			@Valid @RequestBody PeriodoElectivoDTO periodoElectivoDTO) {
		try {

			iAdminService.actualizarPeriodoElectivo(periodoElectivoDTO);

			EntityResponse<PeriodoElectivoDTO> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setMessage(Mensajes.TXT_EDITAR_REGISTRO_OK);
			return ResponseEntity.ok(response);

		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<PeriodoElectivoDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<PeriodoElectivoDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

}
