package com.co.lep.gestion.estudiantes.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.co.lep.gestion.estudiantes.dto.BoletinDTO;
import com.co.lep.gestion.estudiantes.entity.BoletinEntity;

@Mapper
public interface BoletinMapper {
	
	BoletinMapper INSTANCE = Mappers.getMapper(BoletinMapper.class);
	
	@Mapping(target = "id", ignore = true)
	BoletinEntity toEntity(BoletinDTO boletinDTO);

	BoletinDTO toDTO(BoletinEntity boletinEntity);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBoletinFromDto(BoletinDTO boletinDTO, @MappingTarget BoletinEntity boletinEntity);

}
