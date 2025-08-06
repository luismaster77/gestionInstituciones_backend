package com.co.flexicraftsolutions.gestion.estudiantes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.co.flexicraftsolutions.gestion.estudiantes.dto.EstadoDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.EstadoEntity;

@Mapper
public interface EstadoMapper {

    EstadoMapper INSTANCE = Mappers.getMapper(EstadoMapper.class);
	
	@Mapping(target = "id", ignore = true)
	EstadoEntity toEntity(EstadoDTO estadoDTO);

	EstadoDTO toDTO(EstadoEntity estadoEntity);
	
    void updateEstadoFromDto(EstadoDTO dto, @MappingTarget EstadoEntity entity);
}
