package com.co.lep.gestion.estudiantes.integracionIA.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.constantes.Mensajes;
import com.co.lep.gestion.estudiantes.dto.ConfigPromptDTO;
import com.co.lep.gestion.estudiantes.dto.MateriaDTO;
import com.co.lep.gestion.estudiantes.entity.EntityResponse;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.integracionIA.entity.ConfigPromptEntity;
import com.co.lep.gestion.estudiantes.integracionIA.services.IntregacionAIService;

@RestController
@RequestMapping(Constantes.URL_BASE)
public class IntegracionIAController {

	@Autowired
    private IntregacionAIService intregacionAIService;
	
    @SuppressWarnings("unchecked")
	@PostMapping(value = Constantes.ENDPOINT_GENERA_COMENTARIO)
    public ResponseEntity<String> generarObservaciones(@RequestBody Map<String, Object> datos) {
        List<Map<String, Object>> materias = (List<Map<String, Object>>) datos.get("materias");
        StringBuilder promptBuilder = new StringBuilder(datos.get("prompt").toString());

        for (Map<String, Object> materia : materias) {
            promptBuilder.append(materia.get("nombre"))
                    .append(": ")
                    .append(materia.get("nota"))
                    .append("\n");
        }

        String prompt = promptBuilder.toString();
        String observaciones = intregacionAIService.getResponseFromDeepSeek(prompt);
        return ResponseEntity.ok(observaciones);
    }
    
    @SuppressWarnings("unchecked")
	@PostMapping(value = Constantes.ENDPOINT_GENERA_COMENTARIO_v2)
    public ResponseEntity<EntityResponse<Map<String, Object>>> generarObservacionesTestPostman(@RequestBody Map<String, Object> datos) {
    	try {
		
    		List<MateriaDTO> materias = new ArrayList<>();
        	List<Map<String, Object>> materiasData = (List<Map<String, Object>>) datos.get("materias");
            for (Map<String, Object> materiaData : materiasData) {
                MateriaDTO materia = new MateriaDTO();
                materia.setNomMateria(materiaData.get("nombre").toString());
                materia.setNota(new BigDecimal(materiaData.get("nota").toString()));
                materias.add(materia);
            }
            String observaciones = intregacionAIService.getResponseFromDeepSeekV2(materias);
            Map<String, Object> response = new HashMap<>();
            response.put("observacion", observaciones);

            EntityResponse<Map<String, Object>> result = new EntityResponse<>();
            result.setSuccess(true);
            result.setData(response);
            return ResponseEntity.ok(result);
            
		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<Map<String, Object>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<Map<String, Object>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
    
    @GetMapping(value = Constantes.ENDPOINT_CONSULTAR_PROMPT)
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<EntityResponse<ConfigPromptEntity>>consultarPrompt() {
    	try {
            
    		ConfigPromptEntity configPromptEntity = intregacionAIService.consultarPromptPorUsuario();
           
    		EntityResponse<ConfigPromptEntity> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(configPromptEntity);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<ConfigPromptEntity> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<ConfigPromptEntity> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
    
    @PostMapping(value = Constantes.ENDPOINT_EDITAR_PROMPT)
    public ResponseEntity<EntityResponse<ConfigPromptDTO>> editarPrompt(@Valid @RequestBody ConfigPromptDTO configPromptDTO){
    	try {
    		
    		ConfigPromptDTO promptActualizado = intregacionAIService.editarPrompt(configPromptDTO);
    		
    		EntityResponse<ConfigPromptDTO> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(promptActualizado);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<ConfigPromptDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<ConfigPromptDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
}

