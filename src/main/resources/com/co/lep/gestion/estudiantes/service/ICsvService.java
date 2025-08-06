package com.co.flexicraftsolutions.gestion.estudiantes.service;

import java.io.IOException;
import java.util.List;

import com.co.flexicraftsolutions.gestion.estudiantes.controller.representacion.FileUploadRequest;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.RegistroDTO;

public interface ICsvService {

	byte[] generarCSVEnBlanco(List<String> cabeceras) throws IOException;

	List<RegistroDTO> procesarArchivo(FileUploadRequest file);

}
