package com.co.lep.gestion.estudiantes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.constantes.Mensajes;
import com.co.lep.gestion.estudiantes.dto.EstadoDTO;
import com.co.lep.gestion.estudiantes.entity.CiudadEntity;
import com.co.lep.gestion.estudiantes.entity.EntityResponse;
import com.co.lep.gestion.estudiantes.entity.EstadoEntity;
import com.co.lep.gestion.estudiantes.entity.NivelEntity;
import com.co.lep.gestion.estudiantes.entity.PeriodoElectivoEntity;
import com.co.lep.gestion.estudiantes.entity.TipoDocumentoEntity;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.security.entity.Role;
import com.co.lep.gestion.estudiantes.service.IListaValoresService;

@RestController
@RequestMapping(Constantes.URL_BASE)
public class ListaValoresController {

	@Autowired
	IListaValoresService iListaValoresService;
	
	@GetMapping(value = Constantes.ENDPOINT_CONSULTAR_ESTADOS_ESTUDIANTES)
	public ResponseEntity<EntityResponse<List<EstadoEntity>>> consultarEstados(@RequestParam String codLista) {
		try {         
            List<EstadoEntity> estadosList = iListaValoresService.consultarEstados(codLista);
          
            EntityResponse<List<EstadoEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(estadosList);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<List<EstadoEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<EstadoEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@GetMapping(value = Constantes.ENDPOINT_CONSULTAR_ESTADOS)
	public ResponseEntity<EntityResponse<List<EstadoEntity>>> consultarEstadosApp(@RequestParam String codLista) {
		try {         
            List<EstadoEntity> estadosList = iListaValoresService.consultarEstados(codLista);
          
            EntityResponse<List<EstadoEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(estadosList);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<List<EstadoEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<EstadoEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_ESTADOS_ESTUDIANTES_BY_CDVALOR)
	public ResponseEntity<EntityResponse<EstadoDTO>> consultarEstadosByCodValor(@RequestBody EstadoDTO estadoDTO) {
		try {         
            EstadoDTO estado = iListaValoresService.consultarEstadosByCodValor(estadoDTO);
          
            EntityResponse<EstadoDTO> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(estado);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<EstadoDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<EstadoDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@GetMapping(value = Constantes.ENDPOINT_CONSULTAR_NIVELES)
	public ResponseEntity<EntityResponse<List<NivelEntity>>> consultarNiveles() {
		try {         
            List<NivelEntity> nivelesList = iListaValoresService.consultarNiveles();
          
            EntityResponse<List<NivelEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(nivelesList);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<List<NivelEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<NivelEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@GetMapping(value = Constantes.ENDPOINT_CONSULTAR_TIPOS_DOCUMENTOS)
	public ResponseEntity<EntityResponse<List<TipoDocumentoEntity>>> consultarTiposDocumentos() {
		try {         
            List<TipoDocumentoEntity> tipoDocumentosList = iListaValoresService.consultarTiposDocumentos();
          
            EntityResponse<List<TipoDocumentoEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(tipoDocumentosList);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<List<TipoDocumentoEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<TipoDocumentoEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@GetMapping(value = Constantes.ENDPOINT_CONSULTAR_PER_ELECTIVO)
	public ResponseEntity<EntityResponse<List<PeriodoElectivoEntity>>> consultarPeriodosElectivos() {
		try {         
            List<PeriodoElectivoEntity> periList = iListaValoresService.consultarPeriodosElectivos();
          
            EntityResponse<List<PeriodoElectivoEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(periList);
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
	
	@GetMapping(value = Constantes.ENDPOINT_CONSULTAR_ROLES_APLICACION)
	public ResponseEntity<EntityResponse<List<Role>>> consultarRolesAplicacion() {
		try {         
            List<Role> rolesList = iListaValoresService.consultarRolesAplicacion();
          
            EntityResponse<List<Role>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(rolesList);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<List<Role>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<Role>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

    @GetMapping(value = Constantes.ENDPOINT_CONSULTAR_CIUDAD)
    public ResponseEntity<EntityResponse<List<CiudadEntity>>> buscarCiudades(@RequestParam String nombre) {
    	try {         
            List<CiudadEntity> ciudadesList = iListaValoresService.buscarCiudadPorNombre(nombre);
          
            EntityResponse<List<CiudadEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(ciudadesList);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<List<CiudadEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<CiudadEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
}
