package com.co.lep.gestion.estudiantes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.co.lep.gestion.estudiantes.dto.DocenteDTO;
import com.co.lep.gestion.estudiantes.entity.DocenteEntity;

@Mapper
public interface DocenteMapper {

    DocenteMapper INSTANCE = Mappers.getMapper(DocenteMapper.class);
	
	@Mapping(target = "id", ignore = true)
	DocenteEntity toEntity(DocenteDTO docenteDTO);

	DocenteDTO toDTO(DocenteEntity docenteEntity);
	
	void updateDocenteFromDto(DocenteDTO docenteDTO, @MappingTarget DocenteEntity docenteExistente);
}
