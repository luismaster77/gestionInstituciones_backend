package com.co.flexicraftsolutions.gestion.estudiantes.impl.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.flexicraftsolutions.gestion.estudiantes.constantes.Constantes;
import com.co.flexicraftsolutions.gestion.estudiantes.constantes.Mensajes;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.DocenteDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.EstudianteDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.GradoDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.InstitucionDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.MateriaDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.MateriaGradoDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.PeriodoElectivoDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.DocenteEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.EstadoEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.EstudianteEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.GradoEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.InstitucionEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.MateriaEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.MateriaGradoEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.NivelEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.PeriodoElectivoEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.TipoDocumentoEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.flexicraftsolutions.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.flexicraftsolutions.gestion.estudiantes.mapper.DocenteMapper;
import com.co.flexicraftsolutions.gestion.estudiantes.mapper.EstudianteMapper;
import com.co.flexicraftsolutions.gestion.estudiantes.mapper.GradoMapper;
import com.co.flexicraftsolutions.gestion.estudiantes.mapper.InstitucionMapper;
import com.co.flexicraftsolutions.gestion.estudiantes.mapper.MateriaGradoMapper;
import com.co.flexicraftsolutions.gestion.estudiantes.mapper.MateriaMapper;
import com.co.flexicraftsolutions.gestion.estudiantes.mapper.PeriodoElectivoMapper;
import com.co.flexicraftsolutions.gestion.estudiantes.repository.DocenteRepository;
import com.co.flexicraftsolutions.gestion.estudiantes.repository.EstadosRepository;
import com.co.flexicraftsolutions.gestion.estudiantes.repository.EstudianteRepository;
import com.co.flexicraftsolutions.gestion.estudiantes.repository.GradoRepository;
import com.co.flexicraftsolutions.gestion.estudiantes.repository.InstitucionRepository;
import com.co.flexicraftsolutions.gestion.estudiantes.repository.MateriaGradoRepository;
import com.co.flexicraftsolutions.gestion.estudiantes.repository.MateriaRepository;
import com.co.flexicraftsolutions.gestion.estudiantes.repository.NivelesRepository;
import com.co.flexicraftsolutions.gestion.estudiantes.repository.PeriodoElectivoRepository;
import com.co.flexicraftsolutions.gestion.estudiantes.repository.TipoDocumentosRepository;
import com.co.flexicraftsolutions.gestion.estudiantes.repository.UserRepository;
import com.co.flexicraftsolutions.gestion.estudiantes.security.entity.User;
import com.co.flexicraftsolutions.gestion.estudiantes.service.IAdminService;
import com.co.flexicraftsolutions.gestion.estudiantes.utilidades.CodigosEstudiantesGenerator;
import com.co.flexicraftsolutions.gestion.estudiantes.utilidades.CodigosGenerator;
import com.co.flexicraftsolutions.gestion.estudiantes.utilidades.PasswordGenerator;
import com.co.flexicraftsolutions.gestion.estudiantes.utilidades.UserLoginApp;
import com.co.flexicraftsolutions.gestion.estudiantes.utilidades.Validador;

@Service
public class AdminService implements IAdminService {

	@Autowired
	EstudianteRepository estudianteRepository;

	@Autowired
	DocenteRepository docenteRepository;
	
	@Autowired
    MateriaRepository materiaRepository;
	
	@Autowired
	TipoDocumentosRepository tipoDocumentosRepository;
	
	@Autowired
	EstadosRepository estadosRepository;
	
	@Autowired
	GradoRepository gradoRepository;
	
	@Autowired
	InstitucionRepository institucionRepository;
	
	@Autowired
	NivelesRepository nivelesRepository;
	
	@Autowired
	MateriaGradoRepository materiaGradoRepository;
	
	@Autowired
	PeriodoElectivoRepository periodoElectivoRepository;
	
	@Autowired
	UserLoginApp userLoginApp;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	PasswordGenerator passwordGenerator;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public DocenteDTO obtenerDocentePorCodigo(String codDocente) {
		try {
			
			DocenteEntity docenteBuscado = docenteRepository.findByCodDocente(codDocente);
			if (Validador.objetoEsNulo(docenteBuscado)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_REGISTRO_NOT_FOUND_COD + codDocente);
			}
			return DocenteMapper.INSTANCE.toDTO(docenteBuscado);

		} catch (RegistroNoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_USUARIO_NOT_FOUND);
		}

	}
	
	@Override
	public List<DocenteEntity> consultarDocentes() {
		try {
			
			List<DocenteEntity> docentesList = docenteRepository.findAll();
			
			if (Validador.isEmpty(docentesList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}
			
			return docentesList;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO){
		try {
			
			List<String> codEstudiantes = estudianteRepository.findAllCodEstudiantes();
			
			String codigoEstudiante =  generarCodigosEstudiantes(estudianteDTO.getCodDocum(),codEstudiantes);
		 
			estudianteDTO.setCodEstudiante(codigoEstudiante);
			estudianteDTO.setCodDocente(userLoginApp.getCodUser());
			
			EstudianteEntity estudianteEntity = EstudianteMapper.INSTANCE.toEntity(estudianteDTO);		
			
			EstudianteEntity estudianteGuardado = estudianteRepository.save(estudianteEntity);
			
			if (Validador.objetoEsNulo(estudianteGuardado)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_CREAR);
			}
			
			return EstudianteMapper.INSTANCE.toDTO(estudianteGuardado);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			throw new RegistroNoGuardadoException(Mensajes.TXT_CODIGO_ESTUDIANTE_EXISTE);
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
		}
	}
	

	@Override
	public List<EstudianteEntity> consultarEstudiante(@Valid EstudianteDTO estudianteDTO) {
		try {
			
			estudianteDTO.setCodDocente(userLoginApp.getCodUser());

			EstudianteEntity estudianteEntity = EstudianteMapper.INSTANCE.toEntity(estudianteDTO);
			
			if(Validador.longEsNulo(estudianteEntity.getGradoId().getId())){
				estudianteEntity.setGradoId(null);
			}
			
			if(Validador.longEsNulo(estudianteEntity.getTipoDocumId().getId())) {
				estudianteEntity.setTipoDocumId(null);
			}
			
			List<EstudianteEntity> estudiantesList = estudianteRepository.consultarEstudiantes(estudianteEntity); 
			
			if (Validador.isEmpty(estudiantesList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}
			
			return estudiantesList;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Transactional
	public EstudianteDTO actualizarEstudiante(@Valid EstudianteDTO estudianteDTO) {
		try {
			
			estudianteDTO.setCodDocente(userLoginApp.getCodUser());
			 // Verificar si el ID proporcionado existe en la base de datos
			EstudianteEntity estudianteExistente = estudianteRepository.findById(estudianteDTO.getId())
			            .orElseThrow(() -> new RegistroNoEncontradoException("Materia no encontrada con ID: " + estudianteDTO.getId()));
			
			GradoEntity gradoEntity = gradoRepository.findById(estudianteDTO.getGradoId().getId())
					.orElseThrow(() -> new RegistroNoEncontradoException("Grado no encontrada con ID: " + estudianteDTO.getGradoId().getNomGrado()));
			
			TipoDocumentoEntity tipoDocumentoEntity = tipoDocumentosRepository.findById(estudianteDTO.getTipoDocumId().getId())
					.orElseThrow(() -> new RegistroNoEncontradoException("Tipo documento no encontrada con ID: " + estudianteDTO.getTipoDocumId().getNomTipoDocum()));
			
			EstadoEntity estadoEntity = estadosRepository.findById(estudianteDTO.getEstadoId().getId())
					.orElseThrow(() -> new RegistroNoEncontradoException("Estado no encontrada con ID: " + estudianteDTO.getEstadoId().getNomValor()));
			
			estudianteExistente.setGradoId(gradoEntity);
			estudianteExistente.setTipoDocumId(tipoDocumentoEntity);
			estudianteExistente.setEstadoId(estadoEntity);
			
			
		    EstudianteMapper.INSTANCE.updateEstudianteFromDto(estudianteDTO, estudianteExistente);
			
		    EstudianteEntity estudianteGardado = estudianteRepository.save(estudianteExistente);
		    
		    if (Validador.objetoEsNulo(estudianteGardado)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_MODIFICAR);
			}
		    
			return EstudianteMapper.INSTANCE.toDTO(estudianteGardado);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
		}
	}

	public EstudianteEntity consultarEstudianteById(@Valid EstudianteDTO estudianteDTO) {
		try {

			estudianteDTO.setCodDocente(userLoginApp.getCodUser());
			EstudianteEntity estudianteEntity = estudianteRepository.findById(estudianteDTO.getId()).
	        orElseThrow(() -> new RegistroNoEncontradoException(Mensajes.TXT_REGISTRO_NOT_FOUND_COD + estudianteDTO.getId()));

			return estudianteEntity;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public DocenteDTO crearCuentaDocente(@Valid DocenteDTO docenteDTO) {
	    try {
	        docenteDTO.setCodDocente(docenteDTO.getUsuarioId().getUsername());

	        EstadoEntity estadoEntity = estadosRepository.consultarEstadosByCodValor(Constantes.CD_ESTADO_USUARIO_APP, Constantes.CD_ESTADO_ACTIVO);

	        DocenteEntity docenteEntity = DocenteMapper.INSTANCE.toEntity(docenteDTO);

	        TipoDocumentoEntity tipoDocumentoEntity = tipoDocumentosRepository.findById(docenteDTO.getTipDocumId().getId())
	                .orElseThrow(() -> new RegistroNoEncontradoException("Tipo documento no encontrada con ID: " + docenteDTO.getTipDocumId().getNomTipoDocum()));

	        docenteEntity.setTipDocumId(tipoDocumentoEntity);
	        docenteEntity.setEstadoId(estadoEntity);

	        // Crear usuario docente
	        String securePassword = PasswordGenerator.generateSecurePassword(12);
	        docenteEntity.getUsuarioId().setPassword(securePassword);
	        User userDocente = guardarUserForDocente(docenteEntity); // Guardar el usuario asociado al docente

	        // Guardar el docente y el usuario asociado
	        docenteEntity.setUsuarioId(userDocente);
	        docenteEntity.setFecRegistro(new Date());
	        DocenteEntity docenteGuardado = docenteRepository.save(docenteEntity);

	        if (docenteGuardado == null) {
	            throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_CREAR);
	        }


	        // Enviar correo con credenciales para usuario login
	        emailService.sendHtmlEmail(docenteEntity, securePassword);

	        return DocenteMapper.INSTANCE.toDTO(docenteGuardado);
	    } catch (RegistroNoGuardadoException e) {
	        throw e;
	    } catch (DataIntegrityViolationException e) {
	        throw new RegistroNoGuardadoException(Mensajes.TXT_CODIGO_DOCENTE_EXISTE);
	    } catch (Exception e) {
	        throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
	    }
	}

	
	@Transactional(rollbackFor = Exception.class)
	public User guardarUserForDocente(DocenteEntity docenteGuardado) {
	    try {
	        // Crear el objeto User
	        User userDocente = new User();
	        userDocente.setPrimerNombre(docenteGuardado.getNomDocente());
	        userDocente.setPrimerApellido(docenteGuardado.getApe1Docente());
	        userDocente.setUsername(docenteGuardado.getUsuarioId().getUsername());
	        userDocente.setPassword(passwordEncoder.encode(docenteGuardado.getUsuarioId().getPassword()));
	        userDocente.setRoleId(docenteGuardado.getUsuarioId().getRoleId());
	        userDocente.setEstadoId(docenteGuardado.getEstadoId());
	        userDocente.setCambioPassword(true);
	        userDocente.setFecCreacion(new Date());

	        // Guardar el usuario asociado al docente
	        userRepository.save(userDocente);
	        return userDocente;
	    } catch (Exception e) {
	        throw new ServiceException("Error al guardar el usuario para el docente", e);
	    }
	}

	@Override
	public List<DocenteEntity> consultarDocente(@Valid DocenteDTO docenteDTO) {
		try {
			
			docenteDTO.setCodDocente(userLoginApp.getCodUser());

			DocenteEntity docenteEntity = DocenteMapper.INSTANCE.toEntity(docenteDTO);
			
			if(!Validador.objetoEsNulo(docenteEntity.getInstitucionId()) && Validador.longEsNulo(docenteEntity.getInstitucionId().getId())){
				docenteEntity.setInstitucionId(null);
			}
			
			if(!Validador.objetoEsNulo(docenteEntity.getTipDocumId()) && Validador.longEsNulo(docenteEntity.getTipDocumId().getId())) {
				docenteEntity.setTipDocumId(null);
			}
			
			if(!Validador.objetoEsNulo(docenteEntity.getEstadoId()) && Validador.longEsNulo(docenteEntity.getEstadoId().getId())) {
				docenteEntity.setEstadoId(null);
			}
			
			if(!Validador.objetoEsNulo(docenteEntity.getUsuarioId()) && Validador.longEsNulo(docenteEntity.getUsuarioId().getId())) {
				docenteEntity.setUsuarioId(null);
			}
			
			List<DocenteEntity> docentesList = docenteRepository.consultarDocente(docenteEntity); 
			
			if (Validador.isEmpty(docentesList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}
			
			return docentesList;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Transactional
	public MateriaDTO crearMateria(@Valid MateriaDTO materiaDTO) {
		try {

			List<String> codMateriasList = materiaRepository.findAllCodMateria();
			
			String codigo = generarCodigo(materiaDTO.getNomMateria(),codMateriasList);
			
			materiaDTO.setCodMateria(codigo);
			
			MateriaEntity materiaEntity = MateriaMapper.INSTANCE.toEntity(materiaDTO);

			MateriaEntity materiaGuardada = materiaRepository.save(materiaEntity);
			if (Validador.objetoEsNulo(materiaGuardada)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_CREAR);
			}
			return MateriaMapper.INSTANCE.toDTO(materiaGuardada);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			throw new RegistroNoGuardadoException(Mensajes.TXT_CODIGO_MATERIA_EXISTE);
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
		}
	}

	@Transactional
	public List<MateriaEntity> consultarMateria(@Valid MateriaDTO materiaDTO) {
		try {
			MateriaEntity materiaEntity = MateriaMapper.INSTANCE.toEntity(materiaDTO);
			
			if(materiaDTO.getNivelId() != null && Validador.longEsNulo(materiaEntity.getNivelId().getId())){
				materiaEntity.setNivelId(null);
			}
			
			List<MateriaEntity> materiasList = materiaRepository.consultarMaterias(materiaEntity);

			if (Validador.isEmpty(materiasList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}

			return materiasList;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}
	

	@Transactional
	public MateriaEntity consultarMateriaById(@Valid MateriaDTO materiaDTO) {
		try {
			MateriaEntity materiaEntity = MateriaMapper.INSTANCE.toEntity(materiaDTO);
			
			if(materiaDTO.getNivelId() != null && Validador.longEsNulo(materiaEntity.getNivelId().getId())){
				materiaEntity.setNivelId(null);
			}
			
			MateriaEntity materiaEncontrada = materiaRepository.findById(materiaDTO.getId()).
			        orElseThrow(() -> new RegistroNoEncontradoException(Mensajes.TXT_REGISTRO_NOT_FOUND_COD + materiaDTO.getId()));

			if (Validador.objetoEsNulo(materiaEncontrada)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_REGISTRO_NOT_FOUND);
			}

			return materiaEncontrada;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Transactional
	public MateriaDTO actualizarMateria(MateriaDTO materiaActualizadaDTO) {
		try {
			
			 // Verificar si el ID proporcionado existe en la base de datos
			MateriaEntity materiaExistente = materiaRepository.findById(materiaActualizadaDTO.getId())
			            .orElseThrow(() -> new RuntimeException("Materia no encontrada con ID: " + materiaActualizadaDTO.getId()));
			
			NivelEntity nivelEntity = nivelesRepository.findById(materiaActualizadaDTO.getNivelId().getId())
					.orElseThrow(() -> new RegistroNoEncontradoException("Nivel no encontrada con ID: " + materiaActualizadaDTO.getNivelId().getNomNivel()));
			
			materiaExistente.setNivelId(nivelEntity);
			
		    MateriaMapper.INSTANCE.updateMateriaFromDto(materiaActualizadaDTO, materiaExistente);

			MateriaEntity materiaGuardada = materiaRepository.save(materiaExistente);
			if (Validador.objetoEsNulo(materiaGuardada)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_MODIFICAR);
			}
			return MateriaMapper.INSTANCE.toDTO(materiaGuardada);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
		}
	 }

	@Transactional
	public GradoDTO crearGrado(@Valid GradoDTO gradoDTO) {
		try {

			List<String> codGradosList = gradoRepository.findAllCodGrados();
			
			String codigo = generarCodigo(gradoDTO.getNomGrado(),codGradosList);
			
			gradoDTO.setCodGrado(codigo);
			
			GradoEntity gradoEntity = GradoMapper.INSTANCE.toEntity(gradoDTO);

			GradoEntity gradoGuardado = gradoRepository.save(gradoEntity);
			if (Validador.objetoEsNulo(gradoGuardado)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_CREAR);
			}
			
			//Crear registros en materia-grado
			List<MateriaDTO>  materiasList = gradoDTO.getMateriasList();
			if(!Validador.isEmpty(materiasList)) {
				for (MateriaDTO materiaDTO : materiasList) {
					MateriaGradoDTO materiaGradoDTO = new MateriaGradoDTO();
					materiaGradoDTO.setIdMateria(materiaDTO.getId());
					materiaGradoDTO.setIdGrado(gradoGuardado.getId());
					materiaGradoDTO.setIdDocente(userLoginApp.getDocenteInfo().getId());
					
					MateriaGradoEntity materiaGradoEntity = MateriaGradoMapper.INSTANCE.toEntity(materiaGradoDTO);
					
					materiaGradoRepository.save(materiaGradoEntity);
				}
			}
			
			return GradoMapper.INSTANCE.toDTO(gradoGuardado);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			throw new RegistroNoGuardadoException(Mensajes.TXT_CODIGO_GRADO_EXISTE);
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
		}
	}

	@Transactional
	public List<GradoEntity> consultarGrado(@Valid GradoDTO gradoDTO) {
		try {

			List<GradoEntity> gradosList = 
					gradoRepository.findByGrados(
							gradoDTO.getNomGrado(),
							gradoDTO.getAnioElectivo(),
							gradoDTO.getId());

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

	@Transactional
	public GradoDTO actualizarGrado(@Valid GradoDTO gradoDTO) {
		try {
			
			 // Verificar si el ID proporcionado existe en la base de datos
			GradoEntity gradoExistente = gradoRepository.findById(gradoDTO.getId())
			            .orElseThrow(() -> new RuntimeException("Grado no encontrada con ID: " + gradoDTO.getId()));
			
		    GradoMapper.INSTANCE.updateGradoFromDto(gradoDTO, gradoExistente);

			GradoEntity gradoGuardado = gradoRepository.save(gradoExistente);
			if (Validador.objetoEsNulo(gradoGuardado)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_MODIFICAR);
			}
			return GradoMapper.INSTANCE.toDTO(gradoGuardado);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
		}
	}
	@Transactional
	public GradoDTO eliminarGrado(@Valid GradoDTO gradoDTO) {
		try {
			
			//Verificar sdi el ID del grado existe en materia-grado y se elimina
			MateriaGradoEntity materiaGradoEntity = materiaGradoRepository.findById(gradoDTO.getId())
		            .orElseThrow(() -> new RuntimeException("Materia grado no encontrada con ID: " + gradoDTO.getId()));
			
			if(!Validador.objetoEsNulo(materiaGradoEntity)) {
				materiaGradoRepository.delete(materiaGradoEntity);
			}
			
			// Verificar si el ID proporcionado existe en la base de datos
			GradoEntity gradoExistente = gradoRepository.findById(gradoDTO.getId())
			            .orElseThrow(() -> new RuntimeException("Grado no encontrada con ID: " + gradoDTO.getId()));
			
			gradoRepository.deleteById(gradoExistente.getId());
			return GradoMapper.INSTANCE.toDTO(gradoExistente);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
		}
	}

	@Override
	public InstitucionDTO crearInstitucion(@Valid InstitucionDTO institucionDTO) {
		try {
			
			List<String> codInstituciones = institucionRepository.findAllCodInstituciones();
			
			String codigoEstudiante =  generarCodigosEstudiantes(institucionDTO.getNomInstitucion(),codInstituciones);
		
			institucionDTO.setCodInstitucion(codigoEstudiante);
			
			InstitucionEntity institucionEntity = InstitucionMapper.INSTANCE.toEntity(institucionDTO);
			
			InstitucionEntity estudianteGuardado = institucionRepository.save(institucionEntity);
			
			if (Validador.objetoEsNulo(estudianteGuardado)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_CREAR);
			}
			return InstitucionMapper.INSTANCE.toDTO(estudianteGuardado);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_APLICACION);
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
		}
	}

	@Override
	public List<InstitucionEntity> consultarInstitucion(@Valid InstitucionDTO institucionDTO) {
		try {

			InstitucionEntity institucionEntity = InstitucionMapper.INSTANCE.toEntity(institucionDTO);
			
			List<InstitucionEntity> institucionesList = institucionRepository.consultarInstitucion(institucionEntity.getNomInstitucion(), institucionEntity.getNit()); 
			
			if (Validador.isEmpty(institucionesList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}
			
			return institucionesList;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}
	

	@Override
	public InstitucionEntity consultarInstitucionById(@Valid InstitucionDTO institucionDTO) {
		try {

			
			InstitucionEntity institucion = institucionRepository.findById(institucionDTO.getId())
					.orElseThrow(() -> new RegistroNoEncontradoException(Mensajes.TXT_REGISTRO_NOT_FOUND_COD + institucionDTO.getId()));
			
			
			return institucion;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}
	
	

//	private String generarPosibleLogin(DocenteDTO docente, List<String> codigosDocentes) {
//        return LoginGenerator.generateUniqueLogin(docente.getNomDocente(), docente.getApe1Docente(), codigosDocentes);
//	}
	
	@Override
	public PeriodoElectivoDTO crearPeriodoElectivo(@Valid PeriodoElectivoDTO periodoElectivoDTO) {
		try {
			
			List<String> codPeriodos = periodoElectivoRepository.findCodPeriodoElectivos();
			
			String codigoPeriodoElectivo =  generarCodigo(periodoElectivoDTO.getNomPeriodoElect(),codPeriodos);
		
			periodoElectivoDTO.setCodPeriodoElect(codigoPeriodoElectivo);
			
			PeriodoElectivoEntity periodoElectivoEntity = PeriodoElectivoMapper.INSTANCE.toEntity(periodoElectivoDTO);
			
			PeriodoElectivoEntity periodoElectivoGuardado = periodoElectivoRepository.save(periodoElectivoEntity);
			
			if (Validador.objetoEsNulo(periodoElectivoGuardado)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_CREAR);
			}
			return PeriodoElectivoMapper.INSTANCE.toDTO(periodoElectivoGuardado);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_APLICACION);
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
		}
	}

	@Override
	public List<PeriodoElectivoEntity> consultarPeriodoElectivo(@Valid PeriodoElectivoDTO periodoElectivoDTO) {
		try {

			PeriodoElectivoEntity periodoElectivoEntity = PeriodoElectivoMapper.INSTANCE.toEntity(periodoElectivoDTO);
			
			List<PeriodoElectivoEntity> perList = periodoElectivoRepository.consultarPeriodoElectivo(periodoElectivoEntity.getNomPeriodoElect(), periodoElectivoEntity.getCodPeriodoElect()); 
			
			if (Validador.isEmpty(perList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}
			
			return perList;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	public PeriodoElectivoEntity consultarPeriodoElectivoById(@Valid PeriodoElectivoDTO periodoElectivoDTO) {
		try {
			PeriodoElectivoEntity periodoElectivoEntity = periodoElectivoRepository.findById(periodoElectivoDTO.getId())
					.orElseThrow(() -> new RegistroNoEncontradoException(Mensajes.TXT_REGISTRO_NOT_FOUND_COD + periodoElectivoDTO.getId()));
				
			return periodoElectivoEntity;
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	private String generarCodigosEstudiantes(String codDocumEstudiante, List<String> codigosEstudiantes) {
		return CodigosEstudiantesGenerator.generateUniqueCodigoEstudiante(codDocumEstudiante, codigosEstudiantes);
	}
	
	private String generarCodigo(String nombre, List<String> codList) {
		return CodigosGenerator.generateUniqueCodigo(nombre, codList);
	}

	public Long consultarGradoByCodigo(String codigo) {
		return gradoRepository.findByCodGrado(codigo);
	}

	public Long consultarTipoDocumByCodigo(String codigo) {
		return tipoDocumentosRepository.findByCodTipDocum(codigo);
	}

	public Long consultarEstadoByCodigo(String codigo) {
		return estadosRepository.findByCodEstado(codigo);
	}
}
