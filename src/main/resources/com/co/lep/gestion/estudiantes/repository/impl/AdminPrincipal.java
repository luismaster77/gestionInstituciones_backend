package com.co.flexicraftsolutions.gestion.estudiantes.repository.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;

import com.co.flexicraftsolutions.gestion.estudiantes.entity.DocenteEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.EstudianteEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.MateriaEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.repository.BaseRepository;
import com.co.flexicraftsolutions.gestion.estudiantes.utilidades.Validador;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class AdminPrincipal extends BaseRepository {
	
	public AdminPrincipal(String persistenceUnitName) {
		super(persistenceUnitName);
	}
	
	@Transactional
	public List<EstudianteEntity> consultarEstudiantes(@Valid EstudianteEntity estudianteEntity){
	    EntityManager em = getEntityManager();
	    
	    String query = "SELECT DISTINCT e FROM EstudianteEntity e "
	            + "JOIN FETCH e.gradoId "
	            + "JOIN FETCH e.estadoId "
	            + "JOIN FETCH e.tipoDocumId "
	            + "WHERE (:nomEstudiante IS NULL OR e.nomEstudiante = :nomEstudiante) "
	            + "AND (:tipoDocumId IS NULL OR e.tipoDocumId.id = :tipoDocumId) "
	            + "AND (:codDocum IS NULL OR e.codDocum = :codDocum) "
	            + "AND (:gradoId IS NULL OR e.gradoId.id = :gradoId) "
	            + "AND (:codDocente IS NULL OR e.codDocente = :codDocente) ";
	    
	    TypedQuery<EstudianteEntity> consulta = em.createQuery(query, EstudianteEntity.class);
	    consulta.setParameter("nomEstudiante", estudianteEntity.getNomEstudiante());
	    consulta.setParameter("tipoDocumId", estudianteEntity.getTipoDocumId() != null ? estudianteEntity.getTipoDocumId().getId() : null);
	    consulta.setParameter("codDocum", estudianteEntity.getCodDocum());
	    consulta.setParameter("gradoId", estudianteEntity.getGradoId());
	    consulta.setParameter("codDocente", estudianteEntity.getCodDocente());
	    
	    List<EstudianteEntity> listaEstudiantes = consulta.getResultList();
	    for (EstudianteEntity estudiante : listaEstudiantes) {
	        em.refresh(estudiante);
	    }
	    return listaEstudiantes;
	}

	@Transactional
	public List<MateriaEntity> consultarMaterias(@Valid MateriaEntity materiaEntity) {
		EntityManager em = getEntityManager();
	    
	    String query = "SELECT DISTINCT e FROM MateriaEntity e "
	            + "JOIN FETCH e.nivelId "
	            + "WHERE (:nomMateria IS NULL OR e.nomMateria = :nomMateria) "
	            + "AND (:nivelId IS NULL OR e.nivelId.id = :nivelId) "
	            + "ORDER BY e.nomMateria";
	    
	    TypedQuery<MateriaEntity> consulta = em.createQuery(query, MateriaEntity.class);
	    consulta.setParameter("nomMateria", !Validador.cadenaEstaVaciaONula(materiaEntity.getNomMateria()) ? materiaEntity.getNomMateria():null) ;
	    consulta.setParameter("nivelId", materiaEntity.getNivelId());
		
	    List<MateriaEntity> listaMaterias = consulta.getResultList();
	    for (MateriaEntity materia : listaMaterias) {
	        em.refresh(materia);
	    }
	    return listaMaterias;
	}

	public List<DocenteEntity> consultarDocente(DocenteEntity docenteEntity) {
		EntityManager em = getEntityManager();
	    
	    String query = "SELECT DISTINCT e FROM DocenteEntity e "
	            + "JOIN FETCH e.tipDocumId "
	            + "JOIN FETCH e.usuarioId "
	            + "JOIN FETCH e.institucionId "
	            + "JOIN FETCH e.estadoId "
	            + "WHERE (:nomDocente IS NULL OR e.nomDocente = :nomDocente) "
	            + "AND (:tipoDocumId IS NULL OR e.tipDocumId.id = :tipoDocumId) "
	            + "AND (:codDocum IS NULL OR e.codDocum = :codDocum) "
	            + "AND (:usuarioId IS NULL OR e.usuarioId.id = :usuarioId) "
	            + "AND (:institucionId IS NULL OR e.institucionId.id = :institucionId) "
	            + "AND (:estadoId IS NULL OR e.estadoId.id = :estadoId) "
	            + "ORDER BY e.nomDocente";
	    
	    TypedQuery<DocenteEntity> consulta = em.createQuery(query, DocenteEntity.class);
	    consulta.setParameter("nomDocente", docenteEntity.getNomDocente());
	    consulta.setParameter("tipoDocumId", docenteEntity.getTipDocumId() != null ? docenteEntity.getTipDocumId().getId() : null);
	    consulta.setParameter("codDocum", docenteEntity.getCodDocum());
	    consulta.setParameter("usuarioId", docenteEntity.getUsuarioId() != null ? docenteEntity.getUsuarioId().getId(): null);
	    consulta.setParameter("institucionId", docenteEntity.getInstitucionId() != null ? docenteEntity.getInstitucionId().getId(): null);
	    consulta.setParameter("estadoId", docenteEntity.getEstadoId() != null ? docenteEntity.getEstadoId().getId(): null);
		
	    List<DocenteEntity> listaDocentes = consulta.getResultList();
	    for (DocenteEntity docente : listaDocentes) {
	        em.refresh(docente);
	    }
	    return listaDocentes;
	}

}
