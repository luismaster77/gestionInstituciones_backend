package com.co.lep.gestion.estudiantes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.co.lep.gestion.estudiantes.dto.MateriaDTO;
import com.co.lep.gestion.estudiantes.entity.MateriaEntity;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MateriaMapper {

    MateriaMapper INSTANCE = Mappers.getMapper(MateriaMapper.class);
	
	@Mapping(target = "id", ignore = true)
	MateriaEntity toEntity(MateriaDTO materiaDTO);
	
	MateriaDTO toDTO(MateriaEntity materiaEntity);
	
    void updateMateriaFromDto(MateriaDTO dto, @MappingTarget MateriaEntity entity);
}
