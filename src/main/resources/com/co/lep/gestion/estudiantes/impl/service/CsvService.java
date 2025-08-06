package com.co.lep.gestion.estudiantes.impl.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.controller.representacion.FileUploadRequest;
import com.co.lep.gestion.estudiantes.dto.EstadoDTO;
import com.co.lep.gestion.estudiantes.dto.EstudianteDTO;
import com.co.lep.gestion.estudiantes.dto.RegistroDTO;
import com.co.lep.gestion.estudiantes.entity.GradoEntity;
import com.co.lep.gestion.estudiantes.entity.TipoDocumentoEntity;
import com.co.lep.gestion.estudiantes.mapper.RegistroMapper;
import com.co.lep.gestion.estudiantes.service.IAdminService;
import com.co.lep.gestion.estudiantes.service.ICsvService;
import com.co.lep.gestion.estudiantes.service.IListaValoresService;
import com.co.lep.gestion.estudiantes.utilidades.UserLoginApp;
import com.co.lep.gestion.estudiantes.utilidades.Validador;

@Service
public class CsvService implements ICsvService {

	@Autowired
	IListaValoresService iListaValoresService;
	
	@Autowired
	IAdminService iAdminService;
	
	@Autowired
	UserLoginApp userLoginApp;

	private List<TipoDocumentoEntity> tiposIdentificacionList;
	private List<GradoEntity> gradosList;

	public byte[] generarCSVEnBlanco(List<String> cabeceras) throws IOException {
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				Workbook workbook = generarExcelConListasDesplegables(cabeceras)) {
			workbook.write(outputStream);
			return outputStream.toByteArray();
		}
	}

	public Workbook generarExcelConListasDesplegables(List<String> cabeceras) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Hoja1");
		
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < cabeceras.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(cabeceras.get(i));
		}

		String[] tiposIdentificacionList = consultarTiposIdentificacion();
		String[] gradosList = consultarGrados();

		if (tiposIdentificacionList.length > 0) {
			DataValidationHelper dvHelperTiposDocum = sheet.getDataValidationHelper();
			DataValidationConstraint dvConstraint = dvHelperTiposDocum
					.createExplicitListConstraint(tiposIdentificacionList);
			CellRangeAddressList addressList = new CellRangeAddressList(1, 100, 0, 0); // Celdas A1:A1001
			DataValidation validation = dvHelperTiposDocum.createValidation(dvConstraint, addressList);
			sheet.addValidationData(validation);
		}
		if (gradosList.length > 0) {
			DataValidationHelper dvHelperGrados = sheet.getDataValidationHelper();
			DataValidationConstraint dvConstraintGrados = dvHelperGrados.createExplicitListConstraint(gradosList);
			CellRangeAddressList address1List = new CellRangeAddressList(1, 99, 7, 7); // Celdas A8:A1001
			DataValidation validationGrados = dvHelperGrados.createValidation(dvConstraintGrados, address1List);
			sheet.addValidationData(validationGrados);
		}

		return workbook;
	}
	

	@Override
	public List<RegistroDTO> procesarArchivo(FileUploadRequest fileUploadRequest) {
		// Validar extensión del archivo
		List<RegistroDTO> registrosList = new ArrayList<>();
		String fileName = fileUploadRequest.getFileName();
        String fileContent = fileUploadRequest.getFileContent();
        String referencia = fileUploadRequest.getReferencia();
        
        
        if (fileName == null || !fileName.endsWith(".csv")) {
            throw new ServiceException("Extensión de archivo no válida. Por favor, suba un archivo CSV.");
        }
        
        byte[] decodedBytes = Base64.getDecoder().decode(fileContent);
        
        // Leer y validar el contenido del archivo
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(decodedBytes)))) {
        	String headerLine = br.readLine();
            if (headerLine == null) {
            	throw new ServiceException("El archivo está vacío.");
            }

            if (headerLine.matches("[^\\w\\d\\s;,-]")) {
                throw new ServiceException("El archivo contiene caracteres no válidos en las cabeceras.");
            }

            String line;
            boolean hasData = false;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    hasData = true;
                    if (line.matches("[^\\w\\d\\s;,-]")) {
                        throw new ServiceException("El archivo contiene caracteres no válidos.");
                    }
                }
            }

            if (!hasData) {
                throw new ServiceException("El archivo solo contiene cabeceras y no tiene datos.");
            }
        } catch (IOException e) {
        	throw new ServiceException("Error al leer el archivo.");
        }
        
        
        try {
        	registrosList = procesarArchivoCSV(decodedBytes);
        	
        	// Aqui se debe procesar el guardado del registro con la referencia que llega por parametro
        	if(!Validador.isEmpty(registrosList)) {
        		RegistroMapper mapper = new RegistroMapper(iAdminService);
        		if(referencia.equals(Constantes.CD_REFERENCIA_ESTUDIANTE)) {
        			List<EstudianteDTO> estudianteList = mapper.mapRegistroToDTO(registrosList, EstudianteDTO.class);
        			for (EstudianteDTO estudiante : estudianteList) {
        				estudiante.setFecRegistro(new Date());
        				String value = Constantes.CD_ESTADO_ACTIVO;
        				EstadoDTO estado = new EstadoDTO();
        				Long id = iAdminService.consultarEstadoByCodigo(value);
        				estado.setId(id);
        				estudiante.setEstadoId(estado);
        				iAdminService.crearEstudiante(estudiante);
					}
        		}	
        	}
            
        } catch (Exception e) {
            throw new ServiceException("Error al procesar el archivo.");
        } 
        return registrosList;
	}
	
	private List<RegistroDTO> procesarArchivoCSV(byte[] decodedBytes) throws IOException {
        List<RegistroDTO> registros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(decodedBytes)))) {
            String headerLine = br.readLine();
            String[] headers = headerLine.split(";");

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                for (int i = 0; i < values.length; i++) {
                	RegistroDTO registro = new RegistroDTO();
                	registro.setCodCampo(headers[i]);
                	registro.setValCampo(values[i]);
                	registro.setLastField(i == values.length - 1);
                    registros.add(registro);
                }
            }
        }
        return registros;
    }

	private String[] consultarGrados() {
		gradosList = iListaValoresService.consultarGrados();
		String[] grados = new String[gradosList.size()];
		for (int i = 0; i < gradosList.size(); i++) {
			grados[i] = gradosList.get(i).getCodGrado() + "-" + gradosList.get(i).getNomGrado();
		}
		return grados;
	}

	private String[] consultarTiposIdentificacion() {
		tiposIdentificacionList = iListaValoresService.consultarTiposDocumentos();
		String[] tiposIdentificacion = new String[tiposIdentificacionList.size()];
		for (int i = 0; i < tiposIdentificacionList.size(); i++) {
			tiposIdentificacion[i] = tiposIdentificacionList.get(i).getCodTipoDocum() + "-"
					+ tiposIdentificacionList.get(i).getNomTipoDocum();
		}
		return tiposIdentificacion;
	}
}