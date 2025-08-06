package com.co.lep.gestion.estudiantes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.co.lep.gestion.estudiantes.dto.SedeDTO;
import com.co.lep.gestion.estudiantes.entity.SedeEntity;

@Mapper
public interface SedeMapper {

    SedeMapper INSTANCE = Mappers.getMapper(SedeMapper.class);
	
	@Mapping(target = "id", ignore = true)
	SedeEntity toEntity(SedeDTO sedesDTO);

	SedeDTO toDTO(SedeEntity sedeEntity);
	
    void updateSedeFromDto(SedeDTO dto, @MappingTarget SedeEntity entity);
}
