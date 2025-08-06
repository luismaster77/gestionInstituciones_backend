package com.co.flexicraftsolutions.gestion.estudiantes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.co.flexicraftsolutions.gestion.estudiantes.dto.EstudianteDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.EstudianteEntity;

@Mapper
public interface EstudianteMapper {
	
	EstudianteMapper INSTANCE = Mappers.getMapper(EstudianteMapper.class);
	
	@Mapping(target = "id", ignore = true)
	EstudianteEntity toEntity(EstudianteDTO estudianteDTO);

	EstudianteDTO toDTO(EstudianteEntity estudianteEntity);

	void updateEstudianteFromDto(EstudianteDTO estudianteDTO, @MappingTarget EstudianteEntity estudianteExistente);

}
