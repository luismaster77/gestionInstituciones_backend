package com.co.flexicraftsolutions.gestion.estudiantes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.co.flexicraftsolutions.gestion.estudiantes.dto.MateriaGradoDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.DocenteEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.GradoEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.MateriaEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.MateriaGradoEntity;

@Mapper(componentModel = "spring")
public interface MateriaGradoMapper {
	
	MateriaGradoMapper INSTANCE = Mappers.getMapper(MateriaGradoMapper.class);
	
	@Mapping(target = "id", ignore = true)
    @Mapping(source = "idMateria", target = "idMateria", qualifiedByName = "longToMateriaEntity")
    @Mapping(source = "idGrado", target = "idGrado", qualifiedByName = "longToGradoEntity")
    @Mapping(source = "idDocente", target = "idDocente", qualifiedByName = "longToDocenteEntity")
    MateriaGradoEntity toEntity(MateriaGradoDTO materiaGradoDTO);

    @Mapping(source = "idMateria.id", target = "idMateria")
    @Mapping(source = "idGrado.id", target = "idGrado")
    @Mapping(source = "idDocente.id", target = "idDocente")
    MateriaGradoDTO toDTO(MateriaGradoEntity materiaGradoEntity);

    @Mapping(source = "idMateria", target = "idMateria", qualifiedByName = "longToMateriaEntity")
    @Mapping(source = "idGrado", target = "idGrado", qualifiedByName = "longToGradoEntity")
    @Mapping(source = "idDocente", target = "idDocente", qualifiedByName = "longToDocenteEntity")
    void updateMateriaGradoFromDto(MateriaGradoDTO dto, @MappingTarget MateriaGradoEntity entity);

    @Named("longToMateriaEntity")
    default MateriaEntity longToMateriaEntity(Long id) {
        if (id == null) {
            return null;
        }
        MateriaEntity materiaEntity = new MateriaEntity();
        materiaEntity.setId(id);
        return materiaEntity;
    }

    @Named("materiaEntityToLong")
    default Long materiaEntityToLong(MateriaEntity materiaEntity) {
        return materiaEntity != null ? materiaEntity.getId() : null;
    }

    @Named("longToGradoEntity")
    default GradoEntity longToGradoEntity(Long id) {
        if (id == null) {
            return null;
        }
        GradoEntity gradoEntity = new GradoEntity();
        gradoEntity.setId(id);
        return gradoEntity;
    }

    @Named("gradoEntityToLong")
    default Long gradoEntityToLong(GradoEntity gradoEntity) {
        return gradoEntity != null ? gradoEntity.getId() : null;
    }

    @Named("longToDocenteEntity")
    default DocenteEntity longToDocenteEntity(Long id) {
        if (id == null) {
            return null;
        }
        DocenteEntity docenteEntity = new DocenteEntity();
        docenteEntity.setId(id);
        return docenteEntity;
    }

    @Named("docenteEntityToLong")
    default Long docenteEntityToLong(DocenteEntity docenteEntity) {
        return docenteEntity != null ? docenteEntity.getId() : null;
    }
}
