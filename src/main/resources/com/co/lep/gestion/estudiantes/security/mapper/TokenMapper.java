package com.co.flexicraftsolutions.gestion.estudiantes.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.co.flexicraftsolutions.gestion.estudiantes.security.dto.TokenDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.security.entity.Token;

@Mapper
public interface TokenMapper {

    TokenMapper INSTANCE = Mappers.getMapper(TokenMapper.class);
	
	@Mapping(target = "id", ignore = true)
	Token toEntity(TokenDTO tokenDTO);

	TokenDTO toDTO(Token token);
}
