package com.co.lep.gestion.estudiantes.impl.service;

import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.co.lep.gestion.estudiantes.constantes.Mensajes;
import com.co.lep.gestion.estudiantes.entity.BoletinConfiguracionEntity;
import com.co.lep.gestion.estudiantes.entity.InstitucionEntity;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.repository.BoletinConfiguracionRepository;
import com.co.lep.gestion.estudiantes.repository.InstitucionRepository;
import com.co.lep.gestion.estudiantes.repository.impl.AdminPrincipal;
import com.co.lep.gestion.estudiantes.utilidades.Validador;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class BoletinConfiguracionService {

    @Autowired
    private BoletinConfiguracionRepository configuracionRepository;
    
    @Autowired
    private InstitucionRepository institucionRepository;
    
    @Autowired
    AdminPrincipal adminPrincipal;

    // Obtener la configuración actual con las columnas de 'boletines'
    public List<BoletinConfiguracionEntity> obtenerConfiguracionCompleta() {
    	
    	try {
    		 //sincronizarConfiguracion();
    		 List<BoletinConfiguracionEntity> configuracionList = configuracionRepository.findAll(Sort.by("orden"));

			if (Validador.isEmpty(configuracionList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}

			return configuracionList;
		} catch (RegistroNoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
    }

    // Guardar la nueva configuración desde el frontend
    @Transactional
    public void guardarConfiguracion(List<BoletinConfiguracionEntity> configuracion, Long institucionId) {
    	try {
    		
    		InstitucionEntity institucion = institucionRepository.findById(institucionId)
    	            .orElseThrow(() -> new EntityNotFoundException("Institución no encontrada"));
    		
    		 configuracionRepository.deleteByInstitucionIdId(institucionId);
    		
    		for (BoletinConfiguracionEntity campo : configuracion) {                
    			campo.setInstitucionId(institucion);
    			campo.setVisible(true);
    			configuracionRepository.save(campo);
    		}
		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_APLICACION);
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
		}
    }
    
    @Transactional
    public void sincronizarConfiguracion() {
    	try {
    		//Obtener las columnas actuales de la tabla 'boletines'
    		List<String> columnasActuales = obtenerColumnasDeBoletines();
    		
    		//Obtener los campos ya configurados
    		List<String> columnasConfiguradas = configuracionRepository.findAll()
    				.stream()
    				.map(BoletinConfiguracionEntity::getNombreCampo)
    				.collect(Collectors.toList());
    		
    		for (String columna : columnasActuales) {
    			if (!columnasConfiguradas.contains(columna)) {
    				BoletinConfiguracionEntity nuevaConfig = new BoletinConfiguracionEntity();
    				nuevaConfig.setNombreCampo(columna);
    				nuevaConfig.setTipoCampo("texto");
    				nuevaConfig.setOrden(0);
    				nuevaConfig.setVisible(true);
    				configuracionRepository.save(nuevaConfig);
    			}
    		}
    		
    		//Eliminar columnas que ya no existen en 'boletines'
    		for (String columnaConfig : columnasConfiguradas) {
    			if (!columnasActuales.contains(columnaConfig)) {
    				configuracionRepository.deleteByNombreCampo(columnaConfig);
    			}
    		}
		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_APLICACION);
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_SINCRONIZAR_CONFIGURAXION);
		}
    }
    
    private List<String> obtenerColumnasDeBoletines() {
    	
    	try {
    		 return adminPrincipal.consultarColumnasTabla();
    		 
		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_APLICACION);
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		} 
    }

	public List<BoletinConfiguracionEntity> obtenerConfiguracionPorInstitucion(Long institucionId) {
		try {
			return configuracionRepository.findByInstitucionIdId(institucionId);
		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_APLICACION);
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		} 
	}
}

