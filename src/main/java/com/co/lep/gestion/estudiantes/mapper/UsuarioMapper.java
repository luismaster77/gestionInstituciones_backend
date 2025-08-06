package com.co.lep.gestion.estudiantes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.co.lep.gestion.estudiantes.security.dto.UsuarioDTO;
import com.co.lep.gestion.estudiantes.security.entity.User;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
	
	@Mapping(target = "id", ignore = true)
	User toEntity(UsuarioDTO usuarioDTO);

	UsuarioDTO toDTO(User user);
	
	void updateUsuarioFromDto(UsuarioDTO dto, @MappingTarget User user);
}
