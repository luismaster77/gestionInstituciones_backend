package com.co.flexicraftsolutions.gestion.estudiantes.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.flexicraftsolutions.gestion.estudiantes.constantes.Constantes;
import com.co.flexicraftsolutions.gestion.estudiantes.constantes.Mensajes;
import com.co.flexicraftsolutions.gestion.estudiantes.controller.representacion.FileUploadRequest;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.CabecerasDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.RegistroDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.EntityResponse;
import com.co.flexicraftsolutions.gestion.estudiantes.service.ICsvService;

@RestController
@RequestMapping(Constantes.URL_BASE)
public class CSVController {
	
	@Autowired
	ICsvService iCsvService;
	
	@PostMapping(value = Constantes.ENDPOINT_DESCARGAR_CSV)
    public ResponseEntity<EntityResponse<byte[]>> descargarCSV(@RequestBody CabecerasDTO cabecerasDTO) {
    	try {
    		
    		byte[] archivoCsv = iCsvService.generarCSVEnBlanco(cabecerasDTO.getCabeceras());
    		EntityResponse<byte[]> response = new EntityResponse<>();
    		
	        response.setSuccess(true);
			response.setData(archivoCsv);
			return ResponseEntity.ok(response);
		} 
    	catch (IOException e) {
    		EntityResponse<byte[]> errorResponse = new EntityResponse<>();
	        errorResponse.setSuccess(false);
	        errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
	
	@PostMapping(value = Constantes.ENDPOINT_UPLOAD_CSV)
    public ResponseEntity<EntityResponse<List<RegistroDTO>>> uploadCSV(@RequestBody FileUploadRequest fileUploadRequest) {
		try {
			List<RegistroDTO> registrosList = iCsvService.procesarArchivo(fileUploadRequest);
			EntityResponse<List<RegistroDTO>> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setData(registrosList);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
    		EntityResponse<List<RegistroDTO>> errorResponse = new EntityResponse<>();
	        errorResponse.setSuccess(false);
	        errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
