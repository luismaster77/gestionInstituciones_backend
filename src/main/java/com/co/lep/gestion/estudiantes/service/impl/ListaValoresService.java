package com.co.lep.gestion.estudiantes.service.impl;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.lep.gestion.estudiantes.constantes.Mensajes;
import com.co.lep.gestion.estudiantes.dto.EstadoDTO;
import com.co.lep.gestion.estudiantes.entity.CiudadEntity;
import com.co.lep.gestion.estudiantes.entity.EstadoEntity;
import com.co.lep.gestion.estudiantes.entity.GradoEntity;
import com.co.lep.gestion.estudiantes.entity.NivelEntity;
import com.co.lep.gestion.estudiantes.entity.PeriodoElectivoEntity;
import com.co.lep.gestion.estudiantes.entity.TipoDocumentoEntity;
import com.co.lep.gestion.estudiantes.entity.TipoEvaluacionEntity;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.mapper.EstadoMapper;
import com.co.lep.gestion.estudiantes.repository.CiudadRepository;
import com.co.lep.gestion.estudiantes.repository.EstadosRepository;
import com.co.lep.gestion.estudiantes.repository.GradoRepository;
import com.co.lep.gestion.estudiantes.repository.NivelesRepository;
import com.co.lep.gestion.estudiantes.repository.PeriodoElectivoRepository;
import com.co.lep.gestion.estudiantes.repository.RoleRepository;
import com.co.lep.gestion.estudiantes.repository.TipoDocumentosRepository;
import com.co.lep.gestion.estudiantes.repository.TipoEvaluacionRepository;
import com.co.lep.gestion.estudiantes.security.entity.Role;
import com.co.lep.gestion.estudiantes.service.IListaValoresService;
import com.co.lep.gestion.estudiantes.utilidades.Validador;

@Service
public class ListaValoresService extends BaseService implements IListaValoresService{

	
	@Autowired
	EstadosRepository estadosRepository;
	
	@Autowired
	NivelesRepository nivelesRepository; 
	
	@Autowired
	TipoDocumentosRepository tipoDocumentosRepository;
	
	@Autowired
	GradoRepository gradoRepository;
	
	@Autowired
	PeriodoElectivoRepository periodoElectivoRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	CiudadRepository ciudadRepository;
	
	@Autowired
	TipoEvaluacionRepository tipoEvaluacionRepository;
	
	public List<EstadoEntity> consultarEstados(String codLista) {
		try {

			List<EstadoEntity> estadosList = estadosRepository.consultarEstadosApp(codLista);

			if (Validador.isEmpty(estadosList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS + codLista);
			}

			return estadosList;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	public EstadoDTO consultarEstadosByCodValor(EstadoDTO estadoDTO) {
		try {

			EstadoEntity estado = estadosRepository.consultarEstadosByCodValor(estadoDTO.getCodCampo(),estadoDTO.getCodValor());

			if (Validador.objetoEsNulo(estado)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS + estado.getNomValor());
			}

			return EstadoMapper.INSTANCE.toDTO(estado);
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	public List<NivelEntity> consultarNiveles() {
		try {

			List<NivelEntity> nivelesList = nivelesRepository.findAll();

			if (Validador.isEmpty(nivelesList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}

			return nivelesList;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	public List<TipoDocumentoEntity> consultarTiposDocumentos() {
		try {

			List<TipoDocumentoEntity> tipoDocumentosList = tipoDocumentosRepository.findAll();
		    if (Validador.isEmpty(tipoDocumentosList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}

			return tipoDocumentosList;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	public List<GradoEntity> consultarGrados() {
		try {

			List<GradoEntity> gradosList = gradoRepository.findAll();
		    if (Validador.isEmpty(gradosList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}

			return gradosList;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	public List<PeriodoElectivoEntity> consultarPeriodosElectivos() {
		try {
			List<PeriodoElectivoEntity> periList = periodoElectivoRepository.consularPeriodoElectivo(obtenerInstitucionUsuario().getId());
		    if (Validador.isEmpty(periList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}

			return periList;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	public List<Role> consultarRolesAplicacion() {
		try {

			List<Role> rolesList = roleRepository.findAll();
		    if (Validador.isEmpty(rolesList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}

			return rolesList;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	public List<CiudadEntity> buscarCiudadPorNombre(String nombre) {
		try {

			List<CiudadEntity> ciudadesList = ciudadRepository.findByMunicipioContainingIgnoreCase(nombre);
			
			
			return ciudadesList;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	public List<TipoEvaluacionEntity> consultarTipoEvaluacion() {
		try {
			List<TipoEvaluacionEntity> tipoEvaluacionList = tipoEvaluacionRepository.consultarTipoEvaluacion(obtenerInstitucionUsuario().getId());
		    if (Validador.isEmpty(tipoEvaluacionList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}

			return tipoEvaluacionList;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}
	
}