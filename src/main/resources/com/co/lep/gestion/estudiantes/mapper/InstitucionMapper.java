package com.co.lep.gestion.estudiantes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.co.lep.gestion.estudiantes.dto.InstitucionDTO;
import com.co.lep.gestion.estudiantes.entity.InstitucionEntity;

@Mapper
public interface InstitucionMapper {
	
	InstitucionMapper INSTANCE = Mappers.getMapper(InstitucionMapper.class);
	
	@Mapping(target = "id", ignore = true)
	InstitucionEntity toEntity(InstitucionDTO institucionDTO);

	InstitucionDTO toDTO(InstitucionEntity institucionEntity);

	void updateInstitucionFromDto(InstitucionDTO institucionDTO, @MappingTarget InstitucionEntity institucionEntity);
}
