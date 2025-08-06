package com.co.lep.gestion.estudiantes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.co.lep.gestion.estudiantes.dto.NivelDTO;
import com.co.lep.gestion.estudiantes.entity.NivelEntity;

@Mapper
public interface NivelMapper {

    NivelMapper INSTANCE = Mappers.getMapper(NivelMapper.class);
	
	@Mapping(target = "id", ignore = true)
	NivelEntity toEntity(NivelDTO nivelDTO);

	NivelDTO toDTO(NivelEntity nivelEntity);
}
