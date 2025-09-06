package com.co.lep.gestion.estudiantes.service;

import java.util.List;

import javax.validation.Valid;

import com.co.lep.gestion.estudiantes.dto.DetalleEstudiantesNotaDTO;
import com.co.lep.gestion.estudiantes.dto.DocenteDTO;
import com.co.lep.gestion.estudiantes.dto.EstudianteDTO;
import com.co.lep.gestion.estudiantes.dto.EstudiantesNotaDTO;
import com.co.lep.gestion.estudiantes.dto.GradoDTO;
import com.co.lep.gestion.estudiantes.dto.InstitucionDTO;
import com.co.lep.gestion.estudiantes.dto.MateriaDTO;
import com.co.lep.gestion.estudiantes.dto.PasswordDTO;
import com.co.lep.gestion.estudiantes.dto.PeriodoElectivoDTO;
import com.co.lep.gestion.estudiantes.dto.SedeDTO;
import com.co.lep.gestion.estudiantes.entity.DetalleEstudianteNotasEntity;
import com.co.lep.gestion.estudiantes.entity.DocenteEntity;
import com.co.lep.gestion.estudiantes.entity.EstudianteEntity;
import com.co.lep.gestion.estudiantes.entity.EstudianteNotasEntity;
import com.co.lep.gestion.estudiantes.entity.GradoEntity;
import com.co.lep.gestion.estudiantes.entity.InstitucionEntity;
import com.co.lep.gestion.estudiantes.entity.MateriaEntity;
import com.co.lep.gestion.estudiantes.entity.PeriodoElectivoEntity;
import com.co.lep.gestion.estudiantes.entity.SedeEntity;
import com.co.lep.gestion.estudiantes.security.dto.UsuarioDTO;
import com.co.lep.gestion.estudiantes.security.entity.User;

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

	Long consultarTipoDocumByCodigo(String codigo);

	MateriaEntity consultarMateriaById(@Valid MateriaDTO materiaDTO);

	List<DocenteEntity> consultarDocentes();

	List<DocenteEntity> consultarDocente(@Valid DocenteDTO docenteDTO);

	PeriodoElectivoDTO crearPeriodoElectivo(@Valid PeriodoElectivoDTO periodoElectivoDTO);

	PeriodoElectivoEntity consultarPeriodoElectivoById(@Valid PeriodoElectivoDTO periodoElectivoDTO);

	InstitucionDTO actualizarInstitucion(@Valid InstitucionDTO institucionDTO);

	InstitucionDTO eliminarInstitucion(@Valid InstitucionDTO institucionDTO);

	DocenteEntity consultarDocenteById(@Valid DocenteDTO docenteDTO);

	DocenteDTO actualizarDocente(@Valid DocenteDTO docenteDTO);

	PeriodoElectivoDTO actualizarPeriodoElectivo(@Valid PeriodoElectivoDTO periodoElectivoDTO);

	UsuarioDTO crearUsuarioApp(@Valid UsuarioDTO usuarioDTO);

	List<PeriodoElectivoEntity> consultarPeriodoElectivo(@Valid PeriodoElectivoDTO periodoElectivoDTO);

	PasswordDTO cambiarPasswordUsuario(@Valid PasswordDTO passwordDTO);

	GradoEntity consultarGradoById(@Valid GradoDTO gradoDTO);

	List<User> consultarUsuario(@Valid UsuarioDTO usuarioDTO);

	User consultarUsuarioById(@Valid UsuarioDTO usuarioDTO);

	UsuarioDTO actualizarUsuario(@Valid UsuarioDTO usuarioDTO);

	List<SedeEntity> consultarSede(@Valid SedeDTO sedesDTO);

	SedeEntity consultarSedeById(@Valid SedeDTO sedesDTO);

	SedeDTO crearSede(@Valid SedeDTO sedesDTO);

	SedeDTO actualizarSede(@Valid SedeDTO sedesDTO);

	SedeDTO eliminarSede(@Valid SedeDTO sedesDTO);

	List<MateriaEntity> consultarMateriasGradoById(Long id);

	List<MateriaEntity> consultarMateriaByIdDocente();

	List<EstudianteEntity> consultarEstudiantesByGradoId(@Valid Long gradoId);

	void crearEstudiantesNota(@Valid EstudiantesNotaDTO estudiantesNotaDTO);

	List<EstudianteNotasEntity> consultarEstudiantesNota(EstudiantesNotaDTO estudianteDTO);

	List<DetalleEstudianteNotasEntity> consultarEstudiantesNotaById(@Valid DetalleEstudiantesNotaDTO detalleNotas);

	void actualizarEstudiantesNota(@Valid EstudiantesNotaDTO estudiantesNotaDTO);
}
