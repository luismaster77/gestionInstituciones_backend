package com.co.lep.gestion.estudiantes.integracionIA.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.co.lep.gestion.estudiantes.dto.ConfigPromptDTO;
import com.co.lep.gestion.estudiantes.integracionIA.entity.ConfigPromptEntity;

@Mapper
public interface ConfigPromptMapper {

    ConfigPromptMapper INSTANCE = Mappers.getMapper(ConfigPromptMapper.class);
	
	@Mapping(target = "id", ignore = true)
	ConfigPromptEntity toEntity(ConfigPromptDTO configPromptDTO);

	ConfigPromptDTO toDTO(ConfigPromptEntity configPromptEntity);
	
	void updateConfigPromptFromDto(ConfigPromptDTO configPromptDTO, @MappingTarget ConfigPromptEntity configPromptEntity);
}
