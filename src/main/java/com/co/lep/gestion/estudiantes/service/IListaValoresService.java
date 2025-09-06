package com.co.lep.gestion.estudiantes.service;

import java.util.List;

import com.co.lep.gestion.estudiantes.dto.EstadoDTO;
import com.co.lep.gestion.estudiantes.entity.CiudadEntity;
import com.co.lep.gestion.estudiantes.entity.EstadoEntity;
import com.co.lep.gestion.estudiantes.entity.GradoEntity;
import com.co.lep.gestion.estudiantes.entity.NivelEntity;
import com.co.lep.gestion.estudiantes.entity.PeriodoElectivoEntity;
import com.co.lep.gestion.estudiantes.entity.TipoDocumentoEntity;
import com.co.lep.gestion.estudiantes.entity.TipoEvaluacionEntity;
import com.co.lep.gestion.estudiantes.security.entity.Role;

public interface IListaValoresService {

	List<EstadoEntity> consultarEstados(String codLista);

	List<NivelEntity> consultarNiveles();

	List<TipoDocumentoEntity> consultarTiposDocumentos();

	EstadoDTO consultarEstadosByCodValor(EstadoDTO estadoDTO);

	List<GradoEntity> consultarGrados();

	List<PeriodoElectivoEntity> consultarPeriodosElectivos();

	List<Role> consultarRolesAplicacion();

	List<CiudadEntity> buscarCiudadPorNombre(String nombre);

	List<TipoEvaluacionEntity> consultarTipoEvaluacion();
}
