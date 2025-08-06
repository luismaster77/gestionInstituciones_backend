package com.co.lep.gestion.estudiantes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.co.lep.gestion.estudiantes.dto.TipoDocumentoDTO;
import com.co.lep.gestion.estudiantes.entity.TipoDocumentoEntity;

@Mapper
public interface TipoDocumMapper {

    TipoDocumMapper INSTANCE = Mappers.getMapper(TipoDocumMapper.class);
	
	@Mapping(target = "id", ignore = true)
	TipoDocumentoEntity toEntity(TipoDocumentoDTO tipoDocumentoDTO);

	TipoDocumentoDTO toDTO(TipoDocumentoEntity tipoDocumentoEntity);
	
    void updateTipoDocumFromDto(TipoDocumentoDTO dto, @MappingTarget TipoDocumentoEntity entity);
}
