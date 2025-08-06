package com.co.flexicraftsolutions.gestion.estudiantes.mapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.co.flexicraftsolutions.gestion.estudiantes.constantes.Constantes;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.EstadoDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.GradoDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.RegistroDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.TipoDocumentoDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.service.IAdminService;

@Component
public class RegistroMapper {
	
	private IAdminService iAdminService;

	public RegistroMapper(IAdminService iAdminService) {
		this.iAdminService = iAdminService;
	}

	public <T> List<T> mapRegistroToDTO(List<RegistroDTO> registros, Class<T> dtoClass) throws Exception {
        List<T> dtos = new ArrayList<>();
        Map<String, String> fieldValues = new LinkedHashMap<>();
        for (RegistroDTO registro : registros) {
        	String cleanedKey = registro.getCodCampo().replaceAll("[^\\x00-\\x7F]", "");
            fieldValues.put(cleanedKey, registro.getValCampo());
            
         // Verificar si es el último campo de un registro antes de crear el DTO
            if (registro.isLastField()) {
                T dtoInstance = createDTO(dtoClass, fieldValues);
                dtos.add(dtoInstance);
                fieldValues.clear(); // Limpiar los valores del mapa para el próximo registro
            }
        }
        return dtos;
    }
	
	private <T> T createDTO(Class<T> dtoClass, Map<String, String> fieldValues) throws Exception {
        T dtoInstance = dtoClass.getDeclaredConstructor().newInstance();

        for (Field field : dtoClass.getDeclaredFields()) {
            String fieldName = field.getName();
            String value = fieldValues.get(fieldName);
            if (value != null) {
                field.setAccessible(true);
                Object fieldValue = convertValue(field, value);
                field.set(dtoInstance, fieldValue);
            }
        }

        return dtoInstance;
    }
	
	private Object convertValue(Field field, String value) throws Exception {
        Class<?> fieldType = field.getType();
        if (fieldType == String.class) {
            return value;
        } else if (fieldType == int.class || fieldType == Integer.class) {
            return Integer.parseInt(value);
        } else if (fieldType == long.class || fieldType == Long.class) {
            return Long.parseLong(value);
        } else if (fieldType == Date.class) {
            return new Date(Long.parseLong(value));
        } else if (fieldType == TipoDocumentoDTO.class) {
            return createTipoDocumentoDTO(value);
        } else if (fieldType == GradoDTO.class) {
            return createGradoDTO(value);
        } else if (fieldType == EstadoDTO.class) {
            return createEstadoDTO(value);
        }
        // Add more type conversions as needed
        throw new IllegalArgumentException("Unsupported field type: " + fieldType);
    }
	
	private TipoDocumentoDTO createTipoDocumentoDTO(String value) {
        TipoDocumentoDTO tipoDocumento = new TipoDocumentoDTO();
        String codigo = value.split("-")[0];
        Long id = iAdminService.consultarTipoDocumByCodigo(codigo);
        tipoDocumento.setId(id);
        return tipoDocumento;
    }

    private GradoDTO createGradoDTO(String value) {
        GradoDTO grado = new GradoDTO();
        String codigo = value.split("-")[0];
        Long id = iAdminService.consultarGradoByCodigo(codigo);
        grado.setId(id);
        return grado;
    }

    private EstadoDTO createEstadoDTO(String value) {
    	value = Constantes.CD_ESTADO_ACTIVO;
        EstadoDTO estado = new EstadoDTO();
        Long id = iAdminService.consultarEstadoByCodigo(value);
        estado.setId(id);
        return estado;
    }

	public IAdminService getiAdminService() {
		return iAdminService;
	}
}
