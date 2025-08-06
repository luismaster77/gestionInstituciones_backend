package com.co.lep.gestion.estudiantes.service;

import java.util.List;

import javax.validation.Valid;

import com.co.lep.gestion.estudiantes.dto.DocenteDTO;
import com.co.lep.gestion.estudiantes.dto.EstudianteDTO;
import com.co.lep.gestion.estudiantes.dto.GradoDTO;
import com.co.lep.gestion.estudiantes.dto.InstitucionDTO;
import com.co.lep.gestion.estudiantes.dto.MateriaDTO;
import com.co.lep.gestion.estudiantes.dto.PeriodoElectivoDTO;
import com.co.lep.gestion.estudiantes.entity.DocenteEntity;
import com.co.lep.gestion.estudiantes.entity.EstudianteEntity;
import com.co.lep.gestion.estudiantes.entity.GradoEntity;
import com.co.lep.gestion.estudiantes.entity.InstitucionEntity;
import com.co.lep.gestion.estudiantes.entity.MateriaEntity;
import com.co.lep.gestion.estudiantes.entity.PeriodoElectivoEntity;

public interface IAdminService {

	DocenteDTO obtenerDocentePorCodigo(String codDocente);

	DocenteDTO crearCuentaDocente(@Valid DocenteDTO docenteDTO);

	EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO);

	MateriaDTO crearMateria(@Valid MateriaDTO materiaDTO);

	MateriaDTO actualizarMateria(@Valid MateriaDTO materiaDTO);

	GradoDTO crearGrado(@Valid GradoDTO gradoDTO);

	GradoDTO actualizarGrado(@Valid GradoDTO gradoDTO);

	GradoDTO eliminarGrado(@Valid GradoDTO gradoDTO);

	EstudianteEntity consultarEstudianteById(@Valid EstudianteDTO estudianteDTO);

	EstudianteDTO actualizarEstudiante(@Valid EstudianteDTO estudianteDTO);

	List<EstudianteEntity> consultarEstudiante(@Valid EstudianteDTO estudianteDTO);

	List<MateriaEntity> consultarMateria(@Valid MateriaDTO materiaDTO);

	List<GradoEntity> consultarGrado(@Valid GradoDTO gradoDTO);

	InstitucionDTO crearInstitucion(@Valid InstitucionDTO institucionDTO);

	List<InstitucionEntity> consultarInstitucion(@Valid InstitucionDTO institucionDTO);

	InstitucionEntity consultarInstitucionById(@Valid InstitucionDTO institucionDTO);

	Long consultarEstadoByCodigo(String codigo);

	Long consultarGradoByCodigo(String codigo);

	Long consultarTipoDocumByCodigo(String codigo);

	MateriaEntity consultarMateriaById(@Valid MateriaDTO materiaDTO);

	List<DocenteEntity> consultarDocentes();

	List<DocenteEntity> consultarDocente(@Valid DocenteDTO docenteDTO);

	PeriodoElectivoDTO crearPeriodoElectivo(@Valid PeriodoElectivoDTO periodoElectivoDTO);

	List<PeriodoElectivoEntity> consultarPeriodoElectivo(@Valid PeriodoElectivoDTO periodoElectivoDTO);

	PeriodoElectivoEntity consultarPeriodoElectivoById(@Valid PeriodoElectivoDTO periodoElectivoDTO);
}
