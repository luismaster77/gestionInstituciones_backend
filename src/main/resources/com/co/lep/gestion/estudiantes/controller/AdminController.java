package com.co.lep.gestion.estudiantes.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.co.lep.gestion.estudiantes.dto.DocenteDTO;
import com.co.lep.gestion.estudiantes.dto.EstudianteDTO;
import com.co.lep.gestion.estudiantes.dto.GradoDTO;
import com.co.lep.gestion.estudiantes.dto.InstitucionDTO;
import com.co.lep.gestion.estudiantes.dto.MateriaDTO;
import com.co.lep.gestion.estudiantes.dto.PeriodoElectivoDTO;
import com.co.lep.gestion.estudiantes.entity.DocenteEntity;
import com.co.lep.gestion.estudiantes.entity.EntityResponse;
import com.co.lep.gestion.estudiantes.entity.EstudianteEntity;
import com.co.lep.gestion.estudiantes.entity.GradoEntity;
import com.co.lep.gestion.estudiantes.entity.InstitucionEntity;
import com.co.lep.gestion.estudiantes.entity.MateriaEntity;
import com.co.lep.gestion.estudiantes.entity.PeriodoElectivoEntity;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.service.IAdminService;

@RestController
@RequestMapping(Constantes.URL_BASE)
public class AdminController {
	
	@Autowired
	IAdminService iAdminService;

	@GetMapping(value = Constantes.ENDPOINT_CONSULTAR_DOCENTE_BY_CODIGO)
	public ResponseEntity<EntityResponse<DocenteDTO>> buscarDocente(@RequestParam String codDocente) {
        try {
            DocenteDTO docenteDTO = iAdminService.obtenerDocentePorCodigo(codDocente);
           
            EntityResponse<DocenteDTO> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(docenteDTO);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<DocenteDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<DocenteDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
	
	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_DOCENTE)
	public ResponseEntity<EntityResponse<List<DocenteEntity>>> consultarDocente(@Valid @RequestBody DocenteDTO docenteDTO) {
        try {
        	List<DocenteEntity> docentesList = iAdminService.consultarDocente(docenteDTO);
           
            EntityResponse<List<DocenteEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(docentesList);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<List<DocenteEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<DocenteEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
	
	@GetMapping(value = Constantes.ENDPOINT_CONSULTAR_DOCENTES_ALL)
	public ResponseEntity<EntityResponse<List<DocenteEntity>>> consultarDocentes() {
        try {
            List<DocenteEntity> docentesList = iAdminService.consultarDocentes();
           
            EntityResponse<List<DocenteEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(docentesList);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<List<DocenteEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<DocenteEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
	
	@PostMapping(value = Constantes.ENDPOINT_CREAR_DOCENTE)
    public ResponseEntity<EntityResponse<DocenteDTO>> crearCuentaDocente(@Valid @RequestBody DocenteDTO docenteDTO){
    	try {
    		
    		DocenteDTO nuevoDocente = iAdminService.crearCuentaDocente(docenteDTO);
         
            EntityResponse<DocenteDTO> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(nuevoDocente);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoGuardadoException ex) {
        	EntityResponse<DocenteDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<DocenteDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
	
    @PostMapping(value = Constantes.ENDPOINT_CREAR_ESTUDIANTE)
    public ResponseEntity<EntityResponse<EstudianteDTO>> crearEstudiante(@Valid @RequestBody EstudianteDTO estudianteDTO){
    	try {
    		
    		EstudianteDTO nuevoEstudiante = iAdminService.crearEstudiante(estudianteDTO);
         
            EntityResponse<EstudianteDTO> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(nuevoEstudiante);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoGuardadoException ex) {
        	EntityResponse<EstudianteDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<EstudianteDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
    
    @PostMapping(value = Constantes.ENDPOINT_CONSULTAR_ESTUDIANTE)
    public ResponseEntity<EntityResponse<List<EstudianteEntity>>> consultarEstudiante(@Valid @RequestBody EstudianteDTO estudianteDTO){
    	try {
    		
    		List<EstudianteEntity> estudiantesList = iAdminService.consultarEstudiante(estudianteDTO);
          
    		EntityResponse<List<EstudianteEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(estudiantesList);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<List<EstudianteEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<EstudianteEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
    
    @PostMapping(value = Constantes.ENDPOINT_CONSULTAR_ESTUDIANTE_BY_ID)
    public ResponseEntity<EntityResponse<EstudianteEntity>> consultarEstudianteById(@Valid @RequestBody EstudianteDTO estudianteDTO){
    	try {
    		
    		EstudianteEntity estudiantes = iAdminService.consultarEstudianteById(estudianteDTO);
         
    		EntityResponse<EstudianteEntity> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(estudiantes);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<EstudianteEntity> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<EstudianteEntity> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
    
    @PostMapping(value = Constantes.ENDPOINT_EDITAR_ESTUDIANTE)
    public ResponseEntity<EntityResponse<EstudianteDTO>> editarEstudiante(@Valid @RequestBody EstudianteDTO estudianteDTO){
    	try {
    		
            EstudianteDTO estudianteActualizado = iAdminService.actualizarEstudiante(estudianteDTO);
    		
    		EntityResponse<EstudianteDTO> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(estudianteActualizado);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<EstudianteDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<EstudianteDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
    
    @PostMapping(value = Constantes.ENDPOINT_CREAR_MATERIA)
    public ResponseEntity<EntityResponse<MateriaDTO>> crearMateria(@Valid @RequestBody MateriaDTO materiaDTO){
    	try {
    		
    		MateriaDTO nuevaMateria = iAdminService.crearMateria(materiaDTO);
         
            EntityResponse<MateriaDTO> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(nuevaMateria);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoGuardadoException ex) {
        	EntityResponse<MateriaDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<MateriaDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
    
    
    @PostMapping(value = Constantes.ENDPOINT_CONSULTAR_MATERIA)
    public ResponseEntity<EntityResponse<List<MateriaEntity>>> consultarMateria(@Valid @RequestBody MateriaDTO materiaDTO){
    	try {
    		
    		List<MateriaEntity> materiasList = iAdminService.consultarMateria(materiaDTO);
         
    		EntityResponse<List<MateriaEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(materiasList);
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
    
    @PostMapping(value = Constantes.ENDPOINT_CONSULTAR_MATERIA_BY_ID)
    public ResponseEntity<EntityResponse<MateriaEntity>> consultarMateriaById(@Valid @RequestBody MateriaDTO materiaDTO){
    	try {
    		
    		MateriaEntity materias = iAdminService.consultarMateriaById(materiaDTO);
         
    		EntityResponse<MateriaEntity> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(materias);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<MateriaEntity> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<MateriaEntity> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
    
    @PostMapping(value = Constantes.ENDPOINT_EDITAR_MATERIA)
    public ResponseEntity<EntityResponse<MateriaDTO>> editarMateria(@Valid @RequestBody MateriaDTO materiaDTO){
    	try {
    		
            MateriaDTO materiaActualiza = iAdminService.actualizarMateria(materiaDTO);
    		
    		EntityResponse<MateriaDTO> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(materiaActualiza);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<MateriaDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<MateriaDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
    
    @PostMapping(value = Constantes.ENDPOINT_CREAR_GRADO)
    public ResponseEntity<EntityResponse<GradoDTO>> crearGrado(@Valid @RequestBody GradoDTO gradoDTO){
    	try {
    		
    		GradoDTO nuevoGrado = iAdminService.crearGrado(gradoDTO);
         
            EntityResponse<GradoDTO> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(nuevoGrado);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoGuardadoException ex) {
        	EntityResponse<GradoDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<GradoDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
    
    @PostMapping(value = Constantes.ENDPOINT_CONSULTAR_GRADO)
    public ResponseEntity<EntityResponse<List<GradoEntity>>> consultarGrado(@Valid @RequestBody GradoDTO gradoDTO){
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
    
    @PostMapping(value = Constantes.ENDPOINT_EDITAT_GRADO)
    public ResponseEntity<EntityResponse<GradoDTO>> editarGrado(@Valid @RequestBody GradoDTO gradoDTO){
    	try {
    		
    		GradoDTO gradoActualizado = iAdminService.actualizarGrado(gradoDTO);
    		
    		EntityResponse<GradoDTO> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(gradoActualizado);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<GradoDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<GradoDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
    
    @PostMapping(value = Constantes.ENDPOINT_ELIMINAR_GRADO)
    public ResponseEntity<EntityResponse<GradoDTO>> eliminarGrado(@Valid @RequestBody GradoDTO gradoDTO){
    	try {
    		
    		GradoDTO gradoActualizado = iAdminService.eliminarGrado(gradoDTO);
    		
    		EntityResponse<GradoDTO> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(gradoActualizado);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<GradoDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<GradoDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
    
    @PostMapping(value = Constantes.ENDPOINT_CREAR_INSTITUCION)
    public ResponseEntity<EntityResponse<InstitucionDTO>> crearInstitucion(@Valid @RequestBody InstitucionDTO institucionDTO){
    	try {
    		
    		InstitucionDTO nuevaInstitucion = iAdminService.crearInstitucion(institucionDTO);
         
            EntityResponse<InstitucionDTO> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(nuevaInstitucion);
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
    
    @PostMapping(value = Constantes.ENDPOINT_CONSULTAR_INSTITUCION)
    public ResponseEntity<EntityResponse<List<InstitucionEntity>>> consultarInstitucion(@Valid @RequestBody InstitucionDTO institucionDTO){
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
    public ResponseEntity<EntityResponse<InstitucionEntity>> consultarInstitucionById(@Valid @RequestBody InstitucionDTO institucionDTO){
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
    
    @PostMapping(value = Constantes.ENDPOINT_CREAR_PERIODO_ELECTIVO)
    public ResponseEntity<EntityResponse<PeriodoElectivoDTO>> crearPeriodoElectivo(@Valid @RequestBody PeriodoElectivoDTO periodoElectivoDTO){
    	try {
    		
    		PeriodoElectivoDTO nuevoPeriodoElectivo = iAdminService.crearPeriodoElectivo(periodoElectivoDTO);
         
            EntityResponse<PeriodoElectivoDTO> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(nuevoPeriodoElectivo);
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
    
    @PostMapping(value = Constantes.ENDPOINT_CONSULTAR_PERIODO_ELECTIVO)
    public ResponseEntity<EntityResponse<List<PeriodoElectivoEntity>>> consultarPeriodoElectivon(@Valid @RequestBody PeriodoElectivoDTO periodoElectivoDTO){
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
    public ResponseEntity<EntityResponse<PeriodoElectivoEntity>> consultarPeriodoElectivoById(@Valid @RequestBody PeriodoElectivoDTO periodoElectivoDTO){
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
}
