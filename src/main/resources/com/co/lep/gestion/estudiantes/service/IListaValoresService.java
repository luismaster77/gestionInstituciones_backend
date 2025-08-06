package com.co.flexicraftsolutions.gestion.estudiantes.service;

import java.util.List;

import com.co.flexicraftsolutions.gestion.estudiantes.dto.EstadoDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.EstadoEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.GradoEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.NivelEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.PeriodoElectivoEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.TipoDocumentoEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.security.entity.Role;

public interface IListaValoresService {

	List<EstadoEntity> consultarEstados(String codLista);

	List<NivelEntity> consultarNiveles();

	List<TipoDocumentoEntity> consultarTiposDocumentos();

	EstadoDTO consultarEstadosByCodValor(EstadoDTO estadoDTO);

	List<GradoEntity> consultarGrados();

	List<PeriodoElectivoEntity> consultarPeriodosElectivos();

	List<Role> consultarRolesAplicacion();
	
}
