package com.co.lep.gestion.estudiantes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.co.lep.gestion.estudiantes.dto.GradoDTO;
import com.co.lep.gestion.estudiantes.entity.GradoEntity;

@Mapper
public interface GradoMapper {

    GradoMapper INSTANCE = Mappers.getMapper(GradoMapper.class);
	
	@Mapping(target = "id", ignore = true)
	GradoEntity toEntity(GradoDTO gradoDTO);

	GradoDTO toDTO(GradoEntity gradoEntity);
	
    void updateGradoFromDto(GradoDTO dto, @MappingTarget GradoEntity entity);
}
