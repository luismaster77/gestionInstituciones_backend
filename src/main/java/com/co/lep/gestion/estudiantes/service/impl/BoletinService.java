package com.co.lep.gestion.estudiantes.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.constantes.Mensajes;
import com.co.lep.gestion.estudiantes.dto.BoletinConsultaDTO;
import com.co.lep.gestion.estudiantes.dto.BoletinDTO;
import com.co.lep.gestion.estudiantes.dto.BoletinDetalleDTO;
import com.co.lep.gestion.estudiantes.dto.BoletinNotasPeriodosDTO;
import com.co.lep.gestion.estudiantes.dto.MateriaDTO;
import com.co.lep.gestion.estudiantes.entity.BoletinDetalleEntity;
import com.co.lep.gestion.estudiantes.entity.BoletinEntity;
import com.co.lep.gestion.estudiantes.entity.DocenteEntity;
import com.co.lep.gestion.estudiantes.entity.EstudianteEntity;
import com.co.lep.gestion.estudiantes.entity.GradoEntity;
import com.co.lep.gestion.estudiantes.entity.InstitucionEntity;
import com.co.lep.gestion.estudiantes.entity.MateriaEntity;
import com.co.lep.gestion.estudiantes.entity.PeriodoElectivoEntity;
import com.co.lep.gestion.estudiantes.entity.SedeEntity;
import com.co.lep.gestion.estudiantes.entity.TipoDocumentoEntity;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.mapper.BoletinMapper;
import com.co.lep.gestion.estudiantes.mapper.DocenteMapper;
import com.co.lep.gestion.estudiantes.mapper.MateriaMapper;
import com.co.lep.gestion.estudiantes.repository.BoletinDetalleRepository;
import com.co.lep.gestion.estudiantes.repository.BoletinRepository;
import com.co.lep.gestion.estudiantes.repository.DocenteRepository;
import com.co.lep.gestion.estudiantes.repository.EstudianteRepository;
import com.co.lep.gestion.estudiantes.repository.GradoRepository;
import com.co.lep.gestion.estudiantes.repository.InstitucionRepository;
import com.co.lep.gestion.estudiantes.repository.SedeRepository;
import com.co.lep.gestion.estudiantes.repository.TipoDocumentosRepository;
import com.co.lep.gestion.estudiantes.repository.UserRepository;
import com.co.lep.gestion.estudiantes.repository.impl.AdminPrincipal;
import com.co.lep.gestion.estudiantes.security.entity.User;
import com.co.lep.gestion.estudiantes.service.IBoletinService;
import com.co.lep.gestion.estudiantes.utilidades.Desempenio;
import com.co.lep.gestion.estudiantes.utilidades.Validador;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class BoletinService extends BaseService implements IBoletinService{
	
	@Autowired
	BoletinRepository boletinRepository;
	
	@Autowired
	BoletinDetalleRepository boletinDetalleRepository;
	
	@Autowired
	InstitucionRepository institucionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DocenteRepository docenteRepository;
	
	@Autowired
	TipoDocumentosRepository tipoDocumentosRepository;
	
	@Autowired
	GradoRepository gradoRepository;
	
	@Autowired
	EstudianteRepository estudianteRepository;
	
	@Autowired
	SedeRepository sedeRepository;
	
	@Autowired
	AdminPrincipal adminPrincipal;
	
	private final Configuration freeMarkerConfig;
	
	public BoletinService(Configuration freeMarkerConfig) {
		this.freeMarkerConfig = freeMarkerConfig;
	}

	@Transactional
	public List<BoletinEntity> consultarBoletines(BoletinConsultaDTO boletinCDTO) {
		try {
			
			BoletinDTO boletinDTO = new BoletinDTO();
			boletinDTO.setEstudianteId(boletinCDTO.getEstudianteId());
			boletinDTO.setGradoId(boletinCDTO.getGradoId());
			boletinDTO.setPeriodoElectivoId(boletinCDTO.getPeriodoElectivoId());
			
			BoletinEntity boletinEntity = BoletinMapper.INSTANCE.toEntity(boletinDTO);
				
			List<BoletinEntity> boletinesList = adminPrincipal.consultarBoletin(boletinEntity);
			
			if (Validador.isEmpty(boletinesList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}
			
			return boletinesList;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Transactional
	public BoletinEntity consultarBoletinById(BoletinDTO boletinDTO) {
		try {
				
			BoletinEntity boletin = boletinRepository.findById(boletinDTO.getId()).orElseThrow(() 
					-> new RuntimeException("Boletín no encontrado con ID: " + boletinDTO.getId()));
			
			if (Validador.objetoEsNulo(boletin)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}
			
			return boletin;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Transactional
	public List<BoletinDetalleEntity> consultarDetalleBoletines(BoletinDTO boletinDTO) {
		try {
				
			BoletinEntity boletinEntity = BoletinMapper.INSTANCE.toEntity(boletinDTO);
			List<BoletinDetalleEntity> boletinesList = adminPrincipal.consultarBoletinesEstudiante(boletinEntity);
			
			if (Validador.isEmpty(boletinesList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}
			
			return boletinesList;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Transactional
	public List<BoletinDetalleEntity> consultarDetalleBoletinById(Long id) {
		try {
			
			List<BoletinDetalleEntity> boletinesList = boletinDetalleRepository.findByIdBoletin(id);
			
			if (Validador.isEmpty(boletinesList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}
			
			return boletinesList;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Transactional
	public BoletinDTO crearBoletin(BoletinDTO boletinDTO) {
		try {

			BoletinEntity boletinEntity = BoletinMapper.INSTANCE.toEntity(boletinDTO);
			
			InstitucionEntity institucionEntity = obtenerInstitucionUsuario();
			
			User user = obtenerUsuarioApp();
			
			DocenteEntity docenteEntity = null;
			GradoEntity gradoPromovido = null; 
			
			if(!Validador.objetoEsNulo(boletinDTO.getPromovidoGradoId()) && 
					!Validador.esNuloOVacio(boletinDTO.getPromovidoGradoId().getId())) {
				gradoPromovido = gradoRepository.findById(boletinDTO.getPromovidoGradoId().getId())
						.orElseThrow(() -> new RuntimeException("Grado no encontrado" + boletinDTO.getPromovidoGradoId().getId()));
			}
					
			if(!user.getRoleId().getName().equals(Constantes.ROLE_ADMIN)) {
				docenteEntity = docenteRepository.findTop1ByUsuarioId_Id(user.getId())
						.orElseThrow(() -> new RegistroNoEncontradoException("Docente no encontrado"));						
				
			}
			
			boletinEntity.setInstitucionId(institucionEntity);
			boletinEntity.setUsuarioId(user);
			boletinEntity.setDocenteId(docenteEntity);
			boletinEntity.setFecCreacion(new Date());
			boletinEntity.setNumHorasNoAsistidas(boletinDTO.getNumHorasNoAsistidas());
			boletinEntity.setPromovidoGradoId(gradoPromovido);
			boletinEntity.setAprobado(boletinDTO.isAprobado());
			boletinEntity.setNroPuestoEstudiante(boletinDTO.getNroPuestoEstudiante());
			
			Desempenio convivenciaEscolar = obtenerEscalaNacional(boletinDTO.getNotaConvivenciaEscolar());
			
			boletinEntity.setConvivenciaEscolar(convivenciaEscolar != null ? convivenciaEscolar.getDescripcion() : Constantes.BLANK);
			boletinEntity.setNotaConvivenciaEscolar(boletinDTO.getNotaConvivenciaEscolar());

			BoletinEntity boletinGuardado = boletinRepository.save(boletinEntity);
			
			// Crear los detalles del boletín
	        for (MateriaDTO materiaDetalle : boletinDTO.getMateriasList()) {
	            BoletinDetalleEntity detalle = new BoletinDetalleEntity();
	            MateriaEntity materiaEntity = new MateriaEntity();
	            materiaEntity = MateriaMapper.INSTANCE.toEntity(materiaDetalle);
	            materiaEntity.setId(materiaDetalle.getId());
	            
	            detalle.setBoletinId(boletinGuardado);
	            detalle.setMateriaId(materiaEntity);
	            detalle.setNota(materiaDetalle.getNota());
	            Desempenio desempenio = obtenerEscalaNacional(materiaDetalle.getNota());
	            detalle.setEscalaNacional(desempenio.getDescripcion());
	            detalle.setFecCreacion(new Date());

	            boletinDetalleRepository.save(detalle);
	        }
	        
			if (Validador.objetoEsNulo(boletinGuardado)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_CREAR);
			}
			
			//Si es cuarto periodo se debe actualizar el grado del estudiantes si ha sido aprobado y promovido de grado
			if(!Validador.esNuloOVacio(boletinDTO.getPromovidoGradoId()) && boletinDTO.isAprobado() ) {
				
				EstudianteEntity estudianteEntity = estudianteRepository.findById(boletinGuardado.getEstudianteId().getId())
						.orElseThrow(() -> new RegistroNoEncontradoException("Estudiantes no encontrado"));
				
				GradoEntity gradoPromovidoEntity = gradoRepository.findById(boletinDTO.getPromovidoGradoId().getId())
						.orElseThrow(() -> new RegistroNoEncontradoException("Grado no encontrado"));
			
				if(!Validador.esNuloOVacio(estudianteEntity) && !Validador.esNuloOVacio(gradoPromovidoEntity)) {
					
					estudianteEntity.setGradoId(gradoPromovidoEntity);					
					estudianteRepository.save(estudianteEntity);
				}
				
			}			
			
			return BoletinMapper.INSTANCE.toDTO(boletinGuardado);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_CREAR);
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
		}
	}

	@Transactional
	public BoletinDTO editarBoletin(@Valid BoletinDTO boletinDTO) {
		try {
			
			BoletinEntity boletinEncontrado = boletinRepository.findById(boletinDTO.getId())
					.orElseThrow(()-> new RuntimeException("No se encontró boletin: "+ boletinDTO.getId()));
			
			
			boletinDTO.setDocenteId(DocenteMapper.INSTANCE.toDTO(boletinEncontrado.getDocenteId()));
			boletinDTO.setUsuarioId(boletinEncontrado.getUsuarioId());
			
			User user = boletinEncontrado.getUsuarioId();
			
			InstitucionEntity 	institucionEntity 		   = boletinEncontrado.getInstitucionId();
			TipoDocumentoEntity tipoDocumentoEntityUsuario = boletinEncontrado.getUsuarioId().getTipoDocumId();
			TipoDocumentoEntity tipoDocumentoEntityDocente = boletinEncontrado.getDocenteId().getTipoDocumId();
			
			if(Validador.objetoEsNulo(boletinDTO.getUsuarioId())){
				if(!boletinEncontrado.getUsuarioId().getRoleId().getName().equals(Constantes.ROLE_ADMIN)) {
					user = obtenerUsuarioApp();
					institucionEntity = obtenerInstitucionUsuario();
				}
			}
			
			if(!Validador.objetoEsNulo(boletinEncontrado.getDocenteId().getTipoDocumId()) && 
					!Validador.esNuloOVacio(boletinEncontrado.getDocenteId().getTipoDocumId().getId())){
				
				tipoDocumentoEntityUsuario = tipoDocumentosRepository.findById(boletinEncontrado.getDocenteId().getTipoDocumId().getId())
						.orElseThrow(() -> new RegistroNoEncontradoException("Tipo Docum no encontrada"));
			}
			
			if(!Validador.objetoEsNulo(boletinEncontrado.getUsuarioId().getTipoDocumId()) && 
					!Validador.esNuloOVacio(boletinEncontrado.getUsuarioId().getTipoDocumId().getId())){
				
				tipoDocumentoEntityDocente = tipoDocumentosRepository.findById(boletinEncontrado.getDocenteId().getTipoDocumId().getId())
						.orElseThrow(() -> new RegistroNoEncontradoException("Tipo Docum no encontrada"));
			}
			
		    GradoEntity gradoPromovido = null; 
			
			if(!Validador.objetoEsNulo(boletinDTO.getPromovidoGradoId()) && 
					!Validador.esNuloOVacio(boletinDTO.getPromovidoGradoId().getId())) {
				gradoPromovido = gradoRepository.findById(boletinDTO.getPromovidoGradoId().getId())
						.orElseThrow(() -> new RuntimeException("Grado no encontrado" + boletinDTO.getPromovidoGradoId().getId()));
			}
			 SedeEntity sede = null;
			if(!Validador.objetoEsNulo(institucionEntity) && !Validador.objetoEsNulo(institucionEntity.getSedeId())
				&& !Validador.objetoEsNulo(institucionEntity.getSedeId().getId())){
				
				sede = sedeRepository.findById(institucionEntity.getSedeId().getId())
					  .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
			}
			
			boletinEncontrado.getDocenteId().setUsuarioId(user);
			boletinEncontrado.getEstudianteId().setUsuarioId(user);
			boletinEncontrado.setUsuarioId(user);
			boletinEncontrado.setInstitucionId(institucionEntity);
			boletinEncontrado.getDocenteId().setTipoDocumId(tipoDocumentoEntityDocente);
			boletinEncontrado.getUsuarioId().setTipoDocumId(tipoDocumentoEntityUsuario);
			boletinEncontrado.getInstitucionId().setSedeId(sede);
			
			BoletinMapper.INSTANCE.updateBoletinFromDto(boletinDTO, boletinEncontrado);
			
			boletinEncontrado.getDocenteId().setUsuarioId(user);
			boletinEncontrado.getEstudianteId().setUsuarioId(user);
			boletinEncontrado.setUsuarioId(user);
			boletinEncontrado.setInstitucionId(institucionEntity);
			boletinEncontrado.setTxtObservaciones(boletinDTO.getTxtObservaciones());
			boletinEncontrado.setNumHorasNoAsistidas(boletinDTO.getNumHorasNoAsistidas());
			boletinEncontrado.setPromovidoGradoId(gradoPromovido);
			boletinEncontrado.setAprobado(boletinDTO.isAprobado());
			boletinEncontrado.setNroPuestoEstudiante(boletinDTO.getNroPuestoEstudiante());
			boletinEncontrado.getInstitucionId().setSedeId(sede);
			
			Desempenio convivenciaEscolar = obtenerEscalaNacional(boletinDTO.getNotaConvivenciaEscolar());
			
			boletinEncontrado.setConvivenciaEscolar(convivenciaEscolar != null ? convivenciaEscolar.getDescripcion() : Constantes.BLANK);
			boletinEncontrado.setNotaConvivenciaEscolar(boletinDTO.getNotaConvivenciaEscolar());
			
			boletinEncontrado.getDocenteId().setTipoDocumId(tipoDocumentoEntityDocente);
			boletinEncontrado.getUsuarioId().setTipoDocumId(tipoDocumentoEntityUsuario);
			
			BoletinEntity boletinActualizado = boletinRepository.save(boletinEncontrado);
			
			// Obtener el detalle actual del boletín (materias ya existentes)
			List<BoletinDetalleEntity> boletinDetalleEntityList = boletinDetalleRepository.findByIdBoletin(boletinActualizado.getId());

			// Crear un mapa para acceso rápido a las materias existentes por ID
			Map<Long, BoletinDetalleEntity> materiasExistentesMap = boletinDetalleEntityList.stream()
			        .collect(Collectors.toMap(detalle -> detalle.getMateriaId().getId(), Function.identity()));

			// Recorrer las materias que llegan en el DTO
			for (BoletinDetalleDTO materiaDetalleDTO : boletinDTO.getMateriasDetalleList()) {
			    // Si la nota de la materia es nula, se descarta
			    if (materiaDetalleDTO.getNota() == null) {
			        continue; // Ignorar materias sin nota
			    }

			    if (materiaDetalleDTO.getId() != null) {
			        BoletinDetalleEntity detalleExistente = materiasExistentesMap.get(materiaDetalleDTO.getMateriaId().getId());

			        // Si la materia existe en los detalles del boletín, actualizar la nota
			        if (detalleExistente != null) {
			            detalleExistente.setNota(materiaDetalleDTO.getNota());		  
						
						Desempenio desempenio = obtenerEscalaNacional(materiaDetalleDTO.getNota());

						detalleExistente.setEscalaNacional(desempenio.getDescripcion());   		    

			            // Guardar el detalle actualizado
			            boletinDetalleRepository.save(detalleExistente);
			        }
			    }
			}


			if (Validador.objetoEsNulo(boletinActualizado)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_CREAR);
			}
			
			
			//Si es cuarto periodo se debe actualizar el grado del estudiantes si ha sido aprobado y promovido de grado
			if(!Validador.esNuloOVacio(boletinDTO.getPromovidoGradoId()) && boletinDTO.isAprobado() ) {
				
				EstudianteEntity estudianteEntity = estudianteRepository.findById(boletinActualizado.getEstudianteId().getId())
						.orElseThrow(() -> new RegistroNoEncontradoException("Estudiantes no encontrado"));
				
				GradoEntity gradoPromovidoEntity = gradoRepository.findById(boletinDTO.getPromovidoGradoId().getId())
						.orElseThrow(() -> new RegistroNoEncontradoException("Grado no encontrado"));
			
				if(!Validador.esNuloOVacio(estudianteEntity) && !Validador.esNuloOVacio(gradoPromovidoEntity)) {
					
					estudianteEntity.setGradoId(gradoPromovidoEntity);					
					estudianteRepository.save(estudianteEntity);
				}
				
			}			
				
			return BoletinMapper.INSTANCE.toDTO(boletinActualizado);	
			
		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_EDITAR_REGISTRO);
		}
	}

	@Transactional
	public byte[] generarBoletinPDF(Long boletinId) {
	    try {
	        // Cargar la plantilla HTML
	        Template template = freeMarkerConfig.getTemplate("template_boletin_1/boletin_plantilla_3.html");

	        BoletinEntity boletin = boletinRepository.findById(boletinId)
	                .orElseThrow(() -> new RuntimeException("Boletín no encontrado con ID: " + boletinId));
	        
	        BigDecimal promedioPeriodoElectivo1 = null;
	        BigDecimal promedioPeriodoElectivo2	= null;
	        BigDecimal promedioPeriodoElectivo3 = null;
	        BigDecimal promedioPeriodoElectivo4 = null;
	      
	        Map<String, Object> model = new HashMap<>();
	        
		    model.putIfAbsent("numHorasNoAsistidas1", Constantes.BLANK);
		    model.putIfAbsent("numHorasNoAsistidas2", Constantes.BLANK);
		    model.putIfAbsent("numHorasNoAsistidas3", Constantes.BLANK);
		    model.putIfAbsent("numHorasNoAsistidas4", Constantes.BLANK);
	        
	        List<Integer> listNumHorasNoAsistidas = new ArrayList<>();
	        
	        List<PeriodoElectivoEntity> codPeriodosElectivosEstudiante = boletinRepository.obtenerPeriodosPorEstudiante(boletin.getEstudianteId().getId());
	        
	        if(!Validador.isEmpty(codPeriodosElectivosEstudiante)) {
	        	
	        	for (PeriodoElectivoEntity periodoElectivo : codPeriodosElectivosEstudiante) {
	        		Integer numHorasNoAsistidas = null;

	        		if(!Validador.objetoEsNulo(boletin)) {        			
	        			if(periodoElectivo.getCodPeriodoElect().equalsIgnoreCase(Constantes.CD_PER_PRIMERO)) {
	        				
	        				promedioPeriodoElectivo1 = boletinDetalleRepository.promedioTotalNotasEstudiantePorPeriodo(
	        						boletin.getEstudianteId().getId(),
	        						periodoElectivo.getId());
	        				
	        				numHorasNoAsistidas = adminPrincipal.consultarNumHorasNoAsistidasEstudiante(
	        						boletin.getEstudianteId().getId(),
	        						boletinId,
	        						periodoElectivo.getId());
	        				
	        				model.put("numHorasNoAsistidas1", (numHorasNoAsistidas != null && numHorasNoAsistidas != 0) ? numHorasNoAsistidas : "");
	        						
	        			}else if(periodoElectivo.getCodPeriodoElect().equalsIgnoreCase(Constantes.CD_PER_SEGUNGO)) {
	        				promedioPeriodoElectivo2 = boletinDetalleRepository.promedioTotalNotasEstudiantePorPeriodo(
	        						boletin.getEstudianteId().getId(),	
	        						periodoElectivo.getId()); 
	        				
	        				numHorasNoAsistidas = adminPrincipal.consultarNumHorasNoAsistidasEstudiante(
	        						boletin.getEstudianteId().getId(),
	        						boletinId,
	        						periodoElectivo.getId());
	        				
	        				model.put("numHorasNoAsistidas2", (numHorasNoAsistidas != null && numHorasNoAsistidas != 0) ? numHorasNoAsistidas : "");
	        				
	        			}else if(periodoElectivo.getCodPeriodoElect().equalsIgnoreCase(Constantes.CD_PER_TERCERO)) {
	        				promedioPeriodoElectivo3 = boletinDetalleRepository.promedioTotalNotasEstudiantePorPeriodo(
	        						boletin.getEstudianteId().getId(),
	        						periodoElectivo.getId());
	        				
	        				numHorasNoAsistidas = adminPrincipal.consultarNumHorasNoAsistidasEstudiante(
	        						boletin.getEstudianteId().getId(),
	        						boletinId,
	        						periodoElectivo.getId());

	        				model.put("numHorasNoAsistidas3", (numHorasNoAsistidas != null && numHorasNoAsistidas != 0) ? numHorasNoAsistidas : "");
	        				
	        			}else if(periodoElectivo.getCodPeriodoElect().equalsIgnoreCase(Constantes.CD_PER_CUARTO)) {
	        				promedioPeriodoElectivo4 = boletinDetalleRepository.promedioTotalNotasEstudiantePorPeriodo(
	        						boletin.getEstudianteId().getId(),	
	        						periodoElectivo.getId());
	        				
	        				numHorasNoAsistidas = adminPrincipal.consultarNumHorasNoAsistidasEstudiante(
	        						boletin.getEstudianteId().getId(),
	        						boletinId,
	        						periodoElectivo.getId());
	        				
	        				model.put("numHorasNoAsistidas4", (numHorasNoAsistidas != null && numHorasNoAsistidas != 0) ? numHorasNoAsistidas : "");
	        			}
	        		}
	        		
	        		listNumHorasNoAsistidas.add(numHorasNoAsistidas);
				}
	        }
	        
	        
	        BigDecimal promedioSuma = calcularPromedio(
	        		promedioPeriodoElectivo1,
	        		promedioPeriodoElectivo2,
	        		promedioPeriodoElectivo3,
	        		promedioPeriodoElectivo4
	            );
	        Desempenio escalaFinal = obtenerEscalaNacional(promedioSuma);
	        
	        List<BoletinNotasPeriodosDTO> boletinDetalle = boletinDetalleRepository.obtenerNotasPorPeriodo(boletin.getEstudianteId().getId());
	        
	        List<Integer> intensidadHorasList = new ArrayList<>();
	        
	        for (BoletinNotasPeriodosDTO detalle : boletinDetalle) {
	            BigDecimal promedio = calcularPromedio(
	                detalle.getNotaPrimerPeriodo(),
	                detalle.getNotaSegundoPeriodo(),
	                detalle.getNotaTercerPeriodo(),
	                detalle.getNotaCuartoPeriodo()
	            );
	            
	            Desempenio escala = obtenerEscalaNacional(promedio);
	            detalle.setEscalaNacional(escala.getDescripcion());       
	            detalle.setEiFinal(String.valueOf(promedio));	 
	            intensidadHorasList.add(detalle.getIntensidadHoras());
	        }
	        
	        int[] totalIntesidadHorasMaterias = intensidadHorasList.stream()
	        	    .filter(Objects::nonNull) // Filtra valores nulos
	        	    .mapToInt(Integer::intValue)
	        	    .toArray(); 

	        int[] totalHorasNoAsistidas = listNumHorasNoAsistidas.stream()
	        	    .filter(Objects::nonNull) // Filtra valores nulos
	        	    .mapToInt(Integer::intValue)
	        	    .toArray();
	        
	        Integer promedioTotalIntesidadHoras = calcularTotalHoras(totalIntesidadHorasMaterias);
	        Integer promedioHorasNoAsistidas = calcularTotalHoras(totalHorasNoAsistidas);

	        // Configurar datos dinámicos
	        model = prepararModeloBoletin(boletin, boletinDetalle, model);

	        // Calcular total de horas no asistidas y porcentaje
	        int totalHorasPosibles = 200; // Total de horas posibles
	       
	        BigDecimal porcentajeNumHorasNoAsistidas = calcularPorcentajeInasistencias(totalHorasPosibles, totalHorasNoAsistidas);
	        
	        // Agregar datos calculados al modelo
	        model.put("totalNumHorasNoAsistidas", 		promedioHorasNoAsistidas == 0 ? Constantes.BLANK: promedioHorasNoAsistidas);
	        model.put("porcentajeNumHorasNoAsistidas",	porcentajeNumHorasNoAsistidas);
	        model.put("promedioNotasPrimerPeriodo1", 	promedioPeriodoElectivo1 != null ? String.valueOf(promedioPeriodoElectivo1): Constantes.BLANK);
	        model.put("promedioNotasPrimerPeriodo2", 	promedioPeriodoElectivo2 != null ? String.valueOf(promedioPeriodoElectivo2): Constantes.BLANK);
	        model.put("promedioNotasPrimerPeriodo3", 	promedioPeriodoElectivo3 != null ? String.valueOf(promedioPeriodoElectivo3): Constantes.BLANK);
	        model.put("promedioNotasPrimerPeriodo4", 	promedioPeriodoElectivo4 != null ? String.valueOf(promedioPeriodoElectivo4): Constantes.BLANK);
	        model.put("escalaNacionalSuma", 		 	escalaFinal.getDescripcion() != null ? escalaFinal.getDescripcion(): Constantes.BLANK);
	        model.put("eiTotal", 		 				promedioSuma != null ? promedioSuma : Constantes.BLANK);
	        model.put("promedioIntensidadHoras", 		promedioTotalIntesidadHoras != null ? promedioTotalIntesidadHoras : Constantes.BLANK);
	        
	        // Procesar la plantilla y generar el PDF
	        StringWriter stringWriter = new StringWriter();
	        template.process(model, stringWriter);
	        String html = stringWriter.toString();

	        // Convertir HTML a PDF con Flying Saucer
	        return convertirHtmlAPdf(html);

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error al generar el PDF del boletín", e);
	    }
	}
	
	
	private BigDecimal calcularPromedio(BigDecimal... notas) {
	    List<BigDecimal> notasValidas = Arrays.stream(notas)
	        .filter(Objects::nonNull) // Filtrar valores nulos
	        .collect(Collectors.toList());

	    if (notasValidas.isEmpty()) {
	        return BigDecimal.ZERO; // Retorna 0 si no hay notas
	    }

	    BigDecimal suma = notasValidas.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
	    return suma.divide(BigDecimal.valueOf(notasValidas.size()), 2, RoundingMode.HALF_UP);
	}


	private Map<String, Object> prepararModeloBoletin(BoletinEntity boletin, List<BoletinNotasPeriodosDTO> boletinDetalle, Map<String, Object> modelParam) throws IOException {
	    Map<String, Object> model = modelParam;
	    model.put("nombreEstudiante", boletin.getEstudianteId().getNombreCompleto());
	    model.put("tipDocumEstudiante", boletin.getEstudianteId().getTipoDocumId().getCodTipoDocum());
	    model.put("codDocumEstudiante", boletin.getEstudianteId().getCodDocum());
	    model.put("anioElectivo", boletin.getGradoId().getAnioElectivo());
	    model.put("grado", boletin.getGradoId().getNomGrado().toUpperCase());
	    model.put("materias", boletinDetalle);
	    model.put("nomInstitucion", boletin.getInstitucionId().getNomInstitucion().toUpperCase());
	    
	    String nomInstitucionOracion = Arrays.stream(boletin.getInstitucionId().getNomInstitucion().toLowerCase().split(" "))
        	    .map(word -> word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1))
        	    .collect(Collectors.joining(" "));

        model.put("nomInstitucionFooter", nomInstitucionOracion);
	    
	    //En caso de que la institución tenga sedes
	    model.put("municipio", boletin.getInstitucionId().getCiudadId() != null ?  boletin.getInstitucionId().getCiudadId().getNomCompletoCiudad(): Constantes.BLANK);
	    model.put("sede", boletin.getInstitucionId().getSedeId() != null ? boletin.getInstitucionId().getSedeId().getNomSede().toUpperCase(): Constantes.BLANK);
	    model.put("sedeDesc", boletin.getInstitucionId().getSedeId() != null ? boletin.getInstitucionId().getSedeId().getDescSede(): Constantes.BLANK);
	    model.put("daneSede", boletin.getInstitucionId().getSedeId() != null ? boletin.getInstitucionId().getSedeId().getDane(): Constantes.BLANK);
	    model.put("direccionSede", boletin.getInstitucionId().getSedeId() != null ? boletin.getInstitucionId().getSedeId().getDireccion(): Constantes.BLANK);
	    //
	    
	    model.put("nit", boletin.getInstitucionId().getNit());
	    model.put("dane", boletin.getInstitucionId().getDane());
	    model.put("direccion", boletin.getInstitucionId().getDireccion());
	    model.put("email", boletin.getInstitucionId().getEmail());
	    model.put("descripcion", boletin.getInstitucionId().getDescripcion());
	    model.put("image", boletin.getInstitucionId().getLogo());  
	    model.put("nomRector", boletin.getInstitucionId().getRectorInstitucion());
	    model.put("nomDirectorGrado",boletin.getGradoId().getDocenteId().getNombreCompleto());
	    
	    if(boletin.getPeriodoElectivoId().getCodPeriodoElect().equalsIgnoreCase(Constantes.CD_PER_CUARTO)) {
	    	model.put("aprobado", boletin.isAprobado()? Constantes.SI:Constantes.NO);
	    	model.put("promovidoGrado", boletin.getPromovidoGradoId()!= null ? boletin.getPromovidoGradoId().getNomGrado(): Constantes.BLANK);	    	
	    }else {
	    	model.put("aprobado", Constantes.BLANK);
	    	model.put("promovidoGrado", Constantes.BLANK);
	    }
	    
	    model.put("notaConvivencia", boletin.getConvivenciaEscolar()!= null ? boletin.getConvivenciaEscolar() : Constantes.BLANK);
	    model.put("nroPuestoEstudiante", boletin.getNroPuestoEstudiante() != null ? boletin.getNroPuestoEstudiante() : Constantes.BLANK);
	    
	    configurarObservacionesYHoras(boletin, model);
	    return model;
	}

	private void configurarObservacionesYHoras(BoletinEntity boletin, Map<String, Object> model) {
	    String codPeriodo = boletin.getPeriodoElectivoId().getCodPeriodoElect().toUpperCase();
	    String observaciones = boletin.getTxtObservaciones() != null ? boletin.getTxtObservaciones() : Constantes.BLANK;
	    
	    model.putIfAbsent("txtObservaciones1", Constantes.BLANK);
	    model.putIfAbsent("txtObservaciones2", Constantes.BLANK);
	    model.putIfAbsent("txtObservaciones3", Constantes.BLANK);
	    model.putIfAbsent("txtObservaciones4", Constantes.BLANK);
	    
	    
	    switch (codPeriodo) {
	        case Constantes.CD_PER_PRIMERO -> {
	            model.put("txtObservaciones1", observaciones);	         
	        }
	        case Constantes.CD_PER_SEGUNGO -> {
	            model.put("txtObservaciones2", observaciones);	         
	        }
	        case Constantes.CD_PER_TERCERO -> {
	            model.put("txtObservaciones3", observaciones);         
	        }
	        case Constantes.CD_PER_CUARTO -> {
	            model.put("txtObservaciones4", observaciones);	           
	        }
	    }
	}

	private int calcularTotalHoras(int[] totalHoras) {
		if(totalHoras.length > 0) {
			return Arrays.stream(totalHoras).sum();			
		}
		return 0;
	}

	private byte[] convertirHtmlAPdf(String html) throws IOException {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ITextRenderer renderer = new ITextRenderer();
	    renderer.setDocumentFromString(html);
	    renderer.layout();
	    renderer.createPDF(baos);
	    return baos.toByteArray();
	}

	@Transactional
	public BoletinDTO eliminarBoletin(@Valid Long idBoletin) {
		try {

			// Verificar si el ID proporcionado existe en la base de datos
			BoletinEntity boletinExistente = boletinRepository.findById(idBoletin)
					.orElseThrow(() -> new RegistroNoGuardadoException("Boletin no encontrado"));
			
			List<BoletinDetalleEntity> boletinDetalleEntity = boletinDetalleRepository.findByIdBoletin(boletinExistente.getId());
			
			if(!boletinDetalleEntity.isEmpty()) {
				for (BoletinDetalleEntity boletinDetalleBorrar : boletinDetalleEntity) {
					boletinDetalleRepository.delete(boletinDetalleBorrar);
				}
			}

			boletinRepository.deleteById(boletinExistente.getId());
			return BoletinMapper.INSTANCE.toDTO(boletinExistente);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_ELIMINAR);
		}
	}

	private Desempenio obtenerEscalaNacional(BigDecimal nota) {
		if(Validador.esNuloOVacio(nota)) {
			return null;
		}
		
	    if (nota.compareTo(new BigDecimal("4.6")) >= 0) {
	        return Desempenio.SUPERIOR;
	    } else if (nota.compareTo(new BigDecimal("4.0")) >= 0) {
	        return Desempenio.ALTO;
	    } else if (nota.compareTo(new BigDecimal("3.0")) >= 0) {
	        return Desempenio.BASICO;
	    } else if (nota.compareTo(new BigDecimal("0.0")) >= 0) {
	        return Desempenio.BAJO;
	    } else {
	        throw new IllegalArgumentException("Nota fuera del rango permitido.");
	    }
	}

	
	public static BigDecimal calcularPorcentajeInasistencias(int totalHorasPosibles, int... horasNoAsistidasPorPeriodo) {
        // Calcular el total de horas no asistidas
        int totalHorasNoAsistidas = 0;
        for (int horas : horasNoAsistidasPorPeriodo) {
            totalHorasNoAsistidas += horas;
        }

        // Calcular el porcentaje
        if (totalHorasPosibles > 0) {
            BigDecimal porcentaje = new BigDecimal(totalHorasNoAsistidas)
                    .divide(new BigDecimal(totalHorasPosibles), 2, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal("100"));
            return porcentaje;
        } else {
            throw new IllegalArgumentException("El total de horas posibles debe ser mayor a cero.");
        }
    }
}
