package com.co.flexicraftsolutions.gestion.estudiantes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.co.flexicraftsolutions.gestion.estudiantes.dto.PeriodoElectivoDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.PeriodoElectivoEntity;

@Mapper
public interface PeriodoElectivoMapper {

    PeriodoElectivoMapper INSTANCE = Mappers.getMapper(PeriodoElectivoMapper.class);
	
	@Mapping(target = "id", ignore = true)
	PeriodoElectivoEntity toEntity(PeriodoElectivoDTO periodoElectivoDTO);

	PeriodoElectivoDTO toDTO(PeriodoElectivoEntity periodoElectivoEntity);
	
}
