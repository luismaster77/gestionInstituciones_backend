package com.co.lep.gestion.estudiantes.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.constantes.Mensajes;
import com.co.lep.gestion.estudiantes.dto.CiudadDTO;
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
import com.co.lep.gestion.estudiantes.entity.CiudadEntity;
import com.co.lep.gestion.estudiantes.entity.DetalleEstudianteNotasEntity;
import com.co.lep.gestion.estudiantes.entity.DocenteEntity;
import com.co.lep.gestion.estudiantes.entity.EstadoEntity;
import com.co.lep.gestion.estudiantes.entity.EstudianteEntity;
import com.co.lep.gestion.estudiantes.entity.EstudianteNotasEntity;
import com.co.lep.gestion.estudiantes.entity.GradoEntity;
import com.co.lep.gestion.estudiantes.entity.GradoMateriasEntity;
import com.co.lep.gestion.estudiantes.entity.InstitucionEntity;
import com.co.lep.gestion.estudiantes.entity.MateriaEntity;
import com.co.lep.gestion.estudiantes.entity.NivelEntity;
import com.co.lep.gestion.estudiantes.entity.PeriodoElectivoEntity;
import com.co.lep.gestion.estudiantes.entity.SedeEntity;
import com.co.lep.gestion.estudiantes.entity.TipoDocumentoEntity;
import com.co.lep.gestion.estudiantes.exepciones.PasswordMismatchException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.mapper.DocenteMapper;
import com.co.lep.gestion.estudiantes.mapper.EstudianteMapper;
import com.co.lep.gestion.estudiantes.mapper.GradoMapper;
import com.co.lep.gestion.estudiantes.mapper.InstitucionMapper;
import com.co.lep.gestion.estudiantes.mapper.MateriaMapper;
import com.co.lep.gestion.estudiantes.mapper.PeriodoElectivoMapper;
import com.co.lep.gestion.estudiantes.mapper.SedeMapper;
import com.co.lep.gestion.estudiantes.mapper.UsuarioMapper;
import com.co.lep.gestion.estudiantes.repository.CiudadRepository;
import com.co.lep.gestion.estudiantes.repository.DetalleEstudiantesNotaRepository;
import com.co.lep.gestion.estudiantes.repository.DocenteRepository;
import com.co.lep.gestion.estudiantes.repository.EstadosRepository;
import com.co.lep.gestion.estudiantes.repository.EstudianteRepository;
import com.co.lep.gestion.estudiantes.repository.EstudiantesNotaRepository;
import com.co.lep.gestion.estudiantes.repository.GradoMateriasRepository;
import com.co.lep.gestion.estudiantes.repository.GradoRepository;
import com.co.lep.gestion.estudiantes.repository.InstitucionRepository;
import com.co.lep.gestion.estudiantes.repository.MateriaRepository;
import com.co.lep.gestion.estudiantes.repository.NivelesRepository;
import com.co.lep.gestion.estudiantes.repository.PeriodoElectivoRepository;
import com.co.lep.gestion.estudiantes.repository.RoleRepository;
import com.co.lep.gestion.estudiantes.repository.SedeRepository;
import com.co.lep.gestion.estudiantes.repository.TipoDocumentosRepository;
import com.co.lep.gestion.estudiantes.repository.UserRepository;
import com.co.lep.gestion.estudiantes.security.context.UserContext;
import com.co.lep.gestion.estudiantes.security.dto.UsuarioDTO;
import com.co.lep.gestion.estudiantes.security.entity.Role;
import com.co.lep.gestion.estudiantes.security.entity.User;
import com.co.lep.gestion.estudiantes.service.IAdminService;
import com.co.lep.gestion.estudiantes.utilidades.CodigosGenerator;
import com.co.lep.gestion.estudiantes.utilidades.PasswordGenerator;
import com.co.lep.gestion.estudiantes.utilidades.Utilidades;
import com.co.lep.gestion.estudiantes.utilidades.Validador;

@Service
public class AdminService extends BaseService implements IAdminService {

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
	GradoMateriasRepository materiaGradoRepository;

	@Autowired
	PeriodoElectivoRepository periodoElectivoRepository;
	
	@Autowired
	SedeRepository sedeRepository;
	
	@Autowired
	CiudadRepository ciudadRepository;
	
	@Autowired
	GradoMateriasRepository gradoMateriasRepository;

	@Autowired
	EmailService emailService;
	
	@Autowired
	EstudianteService estudianteService;
	
	@Autowired
	DocenteService docenteService;
	
	@Autowired
	MateriaService materiaService;
	
	@Autowired
	GradosService gradosService;
	
	@Autowired
	SedeService sedeService;

	@Autowired
	PasswordGenerator passwordGenerator;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	EstudiantesNotaRepository estudiantesNotaRepository;

	@Autowired
	DetalleEstudiantesNotaRepository detalleEstudiantesNotaRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public List<User> consultarUsuario(@Valid UsuarioDTO usuarioDTO) {
		try {

			User user = UsuarioMapper.INSTANCE.toEntity(usuarioDTO);

			if (Validador.objetoEsNulo(user.getTipoDocumId()) && Validador.esNuloOVacio(user.getTipoDocumId().getId())) {
				user.setTipoDocumId(new TipoDocumentoEntity());
			}

			if (Validador.objetoEsNulo(user.getInstitucionId())
					&& Validador.esNuloOVacio(user.getInstitucionId().getId())) {
				user.setInstitucionId(new InstitucionEntity());
			}

			if (Validador.objetoEsNulo(user.getEstadoId()) && Validador.esNuloOVacio(user.getEstadoId().getId())) {
				user.setEstadoId(new EstadoEntity());
			}

			if (Validador.objetoEsNulo(user.getRoleId()) && Validador.esNuloOVacio(user.getRoleId().getId())) {
				user.setRoleId(new Role());
			}

			List<User> userList = userRepository.consultarUsuario(user.getTipoDocumId().getId(), user.getCodDocum(),
					user.getPrimerNombre(), user.getInstitucionId().getId(), user.getRoleId().getId(),
					user.getEstadoId().getId());

			if (Validador.isEmpty(userList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_REGISTRO_NOT_FOUND);
			}

			return userList;
		} catch (RegistroNoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	public User consultarUsuarioById(@Valid UsuarioDTO usuarioDTO) {
		try {

			User user = userRepository.findById(usuarioDTO.getId())
					.orElseThrow(() -> new RegistroNoEncontradoException("Usuario no encontrado"));

			return user;
		} catch (RegistroNoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	public UsuarioDTO actualizarUsuario(@Valid UsuarioDTO usuarioDTO) {
		try {
			// Verificar si el ID proporcionado existe en la base de datos
			User user = userRepository.findById(usuarioDTO.getId())
					.orElseThrow(() -> new RegistroNoEncontradoException("Usuario no encontrado"));
			
			TipoDocumentoEntity tipoDocumentoEntity = tipoDocumentosRepository
					.findById(usuarioDTO.getTipoDocumId().getId()).orElseThrow(() -> new RegistroNoEncontradoException(
							"Tipo documento no encontrada con ID: " + usuarioDTO.getTipoDocumId().getNomTipoDocum()));

			InstitucionEntity institucionEntity = null;
			if(!Validador.objetoEsNulo(usuarioDTO.getInstitucionId())) {
				institucionEntity = institucionRepository.findById(usuarioDTO.getInstitucionId().getId())
						.orElseThrow(() -> new RegistroNoEncontradoException(
								"Estado no encontrada con ID: " + usuarioDTO.getInstitucionId().getNomInstitucion()));				
			}
			
			Role role = roleRepository.findById(user.getRoleId().getId())
					.orElseThrow(() -> new RegistroNoEncontradoException("Registro no encontrado"));

			EstadoEntity estadoEntity = estadosRepository.findById(usuarioDTO.getEstadoId().getId())
					.orElseThrow(() -> new RegistroNoEncontradoException(
							"Estado no encontrada con ID: " + usuarioDTO.getEstadoId().getNomValor()));

			user.setTipoDocumId(tipoDocumentoEntity);
			user.setInstitucionId(institucionEntity);
			user.setCodDocum(usuarioDTO.getCodDocum());
			user.setEmail(usuarioDTO.getEmail());
			if(!Validador.esNuloOVacio(usuarioDTO.getNuevaPassword())){
				user.setPassword(passwordEncoder.encode(usuarioDTO.getNuevaPassword()));
			}
			user.setEstadoId(estadoEntity);
			user.setRoleId(role);
			user.setFecCreacion(new Date());

			UsuarioMapper.INSTANCE.updateUsuarioFromDto(usuarioDTO, user);
			
			user.setTipoDocumId(tipoDocumentoEntity);
			user.setInstitucionId(institucionEntity);
			if(!Validador.esNuloOVacio(usuarioDTO.getNuevaPassword())){
				user.setPassword(passwordEncoder.encode(usuarioDTO.getNuevaPassword()));
			}
			user.setEstadoId(estadoEntity);
			user.setRoleId(role);
			user.setFecCreacion(new Date());
			
			
			User userActualizado = userRepository.save(user);

			if (Validador.objetoEsNulo(userActualizado)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_MODIFICAR);
			}

			return UsuarioMapper.INSTANCE.toDTO(userActualizado);
		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_EDITAR_REGISTRO);
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_EDITAR_REGISTRO);
		}
	}

	@Override
	public UsuarioDTO crearUsuarioApp(@Valid UsuarioDTO usuarioDTO) {
		try {

			EstadoEntity estadoEntity = estadosRepository.consultarEstadosByCodValor(Constantes.CD_ESTADO_USUARIO_APP,
					Constantes.CD_ESTADO_ACTIVO);

			User user = UsuarioMapper.INSTANCE.toEntity(usuarioDTO);

			// Crear usuario docente

			Role role = roleRepository.findById(usuarioDTO.getRoleId().getId())
					.orElseThrow(() -> new RegistroNoEncontradoException("Role no encontrado"));

			String securePassword = PasswordGenerator.generateSecurePassword(12);

			user.setEstadoId(estadoEntity);
			user.setRoleId(role);
			user.setPassword(passwordEncoder.encode(securePassword));
			user.setCambioPassword(true);
			user.setFecCreacion(new Date());

			User userDocente = guardarUserForDocente(user);

			// Enviar correo con credenciales para usuario login
			emailService.sendHtmlEmail(userDocente, securePassword);

			return UsuarioMapper.INSTANCE.toDTO(user);
		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			throw new RegistroNoGuardadoException(e.getMessage());
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
		}
	}

	@Override
	public PasswordDTO cambiarPasswordUsuario(@Valid PasswordDTO passwordDTO) {
		try {

			User user = userRepository.findByUsername(passwordDTO.getUsername())
					.orElseThrow(() -> new RegistroNoEncontradoException("Usuario no encontrado"));

			if (!Validador.objetoEsNulo(user)) {

				// Comparamos contraseñas actuales
				 // Comparar la contraseña actual con la almacenada en BD
	            if (!verificarContrasena(passwordDTO.getConstraseniaActual(), user.getPassword())) {
	                throw new PasswordMismatchException("La contraseña actual es incorrecta.");
	            }

	            // Validar que la nueva contraseña no sea igual a la actual
	            if (verificarContrasena(passwordDTO.getNuevaContrasenia(), user.getPassword())) {
	                throw new PasswordMismatchException("La nueva contraseña no puede ser la misma que la actual.");
	            }

	            // Validar que la nueva contraseña coincida con la confirmación
	            if (!passwordDTO.getNuevaContrasenia().equals(passwordDTO.getConfirmarContrasenia())) {
	                throw new PasswordMismatchException("La nueva contraseña y la confirmación no coinciden.");
	            }


				user.setPassword(passwordEncoder.encode(passwordDTO.getNuevaContrasenia()));
				user.setCambioPassword(false);

				userRepository.save(user);

			}
			return null;

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (PasswordMismatchException e) {
			throw new RegistroNoGuardadoException(e.getMessage());
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_MODIFICAR_PWD);
		}
	}

	public boolean verificarContrasena(String contrasenaActual, String contrasenaEncriptada) {
		return passwordEncoder.matches(contrasenaActual, contrasenaEncriptada);
	}

	@Override
	public DocenteDTO obtenerDocentePorCodigo(String codUsuario) {
		try {

			DocenteEntity docenteBuscado = docenteRepository.consultarDocentePorUsuario(codUsuario);
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

			List<DocenteEntity> docentesList = docenteRepository.findByInstitucionId(obtenerInstitucionUsuario().getId());

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
	public DocenteEntity consultarDocenteById(@Valid DocenteDTO docenteDTO) {
		try {
			DocenteEntity docenteEntity = docenteRepository.findById(docenteDTO.getId()).orElseThrow(
					() -> new RegistroNoEncontradoException(Mensajes.TXT_REGISTRO_NOT_FOUND_COD + docenteDTO.getId()));

			return docenteEntity;
		} catch (RegistroNoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO) {
		try {

			EstudianteEntity estudianteEntity = EstudianteMapper.INSTANCE.toEntity(estudianteDTO);

			EstadoEntity estadoEntity = estadosRepository.consultarEstadosByCodValor(Constantes.CD_ESTADOS_ESTUDIANTES,
					Constantes.CD_ESTADO_ACTIVO);

			InstitucionEntity institucionEntity = obtenerInstitucionUsuario();

			User user = obtenerUsuarioApp();

			estudianteEntity.setEstadoId(estadoEntity);
			estudianteEntity.setInstitucionId(institucionEntity);
			estudianteEntity.setUsuarioId(user);
			estudianteEntity.setFecCreacion(new Date());

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

	@Transactional
	public List<EstudianteEntity> consultarEstudiante(@Valid EstudianteDTO estudianteDTO) {
		try {

			EstudianteEntity estudianteEntity = EstudianteMapper.INSTANCE.toEntity(estudianteDTO);

			if (Validador.esNuloOVacio(estudianteEntity.getGradoId().getId())) {
				estudianteEntity.setGradoId(null);
			}

			if (Validador.esNuloOVacio(estudianteEntity.getTipoDocumId().getId())) {
				estudianteEntity.setTipoDocumId(null);
			}

			List<EstudianteEntity> estudiantesList = estudianteService.consultarEstudiantes(estudianteEntity);

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
			// Verificar si el ID proporcionado existe en la base de datos
			EstudianteEntity estudianteExistente = estudianteRepository.findById(estudianteDTO.getId()).orElseThrow(
					() -> new RegistroNoEncontradoException("Materia no encontrada con ID: " + estudianteDTO.getId()));

			GradoEntity gradoEntity = gradoRepository.findById(estudianteDTO.getGradoId().getId())
					.orElseThrow(() -> new RegistroNoEncontradoException(
							"Grado no encontrada con ID: " + estudianteDTO.getGradoId().getNomGrado()));

			TipoDocumentoEntity tipoDocumentoEntity = tipoDocumentosRepository
					.findById(estudianteDTO.getTipoDocumId().getId())
					.orElseThrow(() -> new RegistroNoEncontradoException("Tipo documento no encontrada con ID: "
							+ estudianteDTO.getTipoDocumId().getNomTipoDocum()));

			EstadoEntity estadoEntity = estadosRepository.findById(estudianteDTO.getEstadoId().getId())
					.orElseThrow(() -> new RegistroNoEncontradoException(
							"Estado no encontrada con ID: " + estudianteDTO.getEstadoId().getNomValor()));

			InstitucionEntity institucionEntity = obtenerInstitucionUsuario();

			User user = obtenerUsuarioApp();

			estudianteExistente.setGradoId(gradoEntity);
			estudianteExistente.setTipoDocumId(tipoDocumentoEntity);
			estudianteExistente.setEstadoId(estadoEntity);
			estudianteExistente.setUsuarioId(user);
			estudianteExistente.setInstitucionId(institucionEntity);
			estudianteExistente.setFecCreacion(new Date());

			EstudianteMapper.INSTANCE.updateEstudianteFromDto(estudianteDTO, estudianteExistente);

			estudianteExistente.setGradoId(gradoEntity);
			estudianteExistente.setTipoDocumId(tipoDocumentoEntity);
			estudianteExistente.setEstadoId(estadoEntity);
			estudianteExistente.setUsuarioId(user);
			estudianteExistente.setInstitucionId(institucionEntity);
			estudianteExistente.setFecCreacion(new Date());

			EstudianteEntity estudianteGuardado = estudianteRepository.save(estudianteExistente);

			if (Validador.objetoEsNulo(estudianteGuardado)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_MODIFICAR);
			}

			return EstudianteMapper.INSTANCE.toDTO(estudianteGuardado);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_EDITAR_REGISTRO);
		}
	}

	@Transactional
	public EstudianteEntity consultarEstudianteById(@Valid EstudianteDTO estudianteDTO) {
		try {
			EstudianteEntity estudianteEntity = estudianteRepository.findById(estudianteDTO.getId())
					.orElseThrow(() -> new RegistroNoEncontradoException(
							Mensajes.TXT_REGISTRO_NOT_FOUND_COD + estudianteDTO.getId()));

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

			EstadoEntity estadoEntity = estadosRepository.consultarEstadosByCodValor(Constantes.CD_ESTADO_USUARIO_APP,
					Constantes.CD_ESTADO_ACTIVO);

			DocenteEntity docenteEntity = DocenteMapper.INSTANCE.toEntity(docenteDTO);

			TipoDocumentoEntity tipoDocumentoEntity = tipoDocumentosRepository
					.findById(docenteDTO.getTipoDocumId().getId()).orElseThrow(() -> new RegistroNoEncontradoException(
							"Tipo documento no encontrada con ID: " + docenteDTO.getTipoDocumId().getNomTipoDocum()));
			
			InstitucionEntity institucionEntity = null;
			
			if(Validador.objetoEsNulo(docenteDTO.getInstitucionId())) {
				institucionEntity = obtenerInstitucionUsuario();
			}else {
				
				institucionEntity = institucionRepository.findById(docenteDTO.getInstitucionId().getId())
						.orElseThrow(() -> new RegistroNoEncontradoException(
								"Institucion no encontrada" + docenteDTO.getInstitucionId().getNomInstitucion()));
			}
			
			User user = obtenerUsuarioApp();

			docenteEntity.setTipoDocumId(tipoDocumentoEntity);
			docenteEntity.setEstadoId(estadoEntity);
			docenteEntity.setInstitucionId(institucionEntity);

			docenteEntity.setUsuarioId(user);
			docenteEntity.setFecCreacion(new Date());
			DocenteEntity docenteGuardado = docenteRepository.save(docenteEntity);

			if (docenteGuardado == null) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_CREAR);
			}

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
	public User guardarUserForDocente(User usuario) {
		try {
			// Crear el objeto User
			User usuarioApp = usuario;

			// Guardar el usuario asociado al docente
			userRepository.save(usuarioApp);
			return usuarioApp;
		} catch (DataIntegrityViolationException e) {
	        String errorMessage = e.getMessage().toLowerCase();
	        
	        // Verifica si el error es por email o username duplicado
	        if (errorMessage.contains("email")) {
	            throw new RegistroNoGuardadoException("El correo electrónico ya está en uso.");
	        } else if (errorMessage.contains("username")) {
	            throw new RegistroNoGuardadoException("El nombre de usuario ya está en uso.");
	        } else {
	            throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
	        }
		} catch (Exception e) {
			throw new ServiceException("Error al guardar el usuario", e);
		}
	}

	@Override
	public List<DocenteEntity> consultarDocente(@Valid DocenteDTO docenteDTO) {
		try {

			DocenteEntity docenteEntity = DocenteMapper.INSTANCE.toEntity(docenteDTO);

			if (!Validador.objetoEsNulo(docenteEntity.getInstitucionId())
					&& Validador.esNuloOVacio(docenteEntity.getInstitucionId().getId())) {
				docenteEntity.setInstitucionId(null);
			}

			if (!Validador.objetoEsNulo(docenteEntity.getTipoDocumId())
					&& Validador.esNuloOVacio(docenteEntity.getTipoDocumId().getId())) {
				docenteEntity.setTipoDocumId(null);
			}

			if (!Validador.objetoEsNulo(docenteEntity.getEstadoId())
					&& Validador.esNuloOVacio(docenteEntity.getEstadoId().getId())) {
				docenteEntity.setEstadoId(null);
			}

			if (!Validador.objetoEsNulo(docenteEntity.getUsuarioId())
					&& Validador.esNuloOVacio(docenteEntity.getUsuarioId().getId())) {
				docenteEntity.setUsuarioId(null);
			}

			List<DocenteEntity> docentesList = docenteService.consultarDocente(docenteEntity);

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
	public DocenteDTO actualizarDocente(@Valid DocenteDTO docenteDTO) {
		try {

			// Verificar si el ID proporcionado existe en la base de datos
			DocenteEntity docenteEntity = docenteRepository.findById(docenteDTO.getId()).orElseThrow(
					() -> new RegistroNoEncontradoException("Docente no encontrado con ID: " + docenteDTO.getId()));

			TipoDocumentoEntity tipoDocumentoEntity = tipoDocumentosRepository
					.findById(docenteDTO.getTipoDocumId().getId()).orElseThrow(() -> new RegistroNoEncontradoException(
							"Tipo documento no encontrada con ID: " + docenteDTO.getTipoDocumId().getNomTipoDocum()));

			User user = obtenerUsuarioApp();

			InstitucionEntity institucionEntity = institucionRepository.findById(docenteDTO.getInstitucionId().getId())
					.orElseThrow(() -> new RegistroNoEncontradoException(
							"Estado no encontrada con ID: " + docenteDTO.getInstitucionId().getNomInstitucion()));

			EstadoEntity estadoEntity = estadosRepository.findById(docenteDTO.getEstadoId().getId())
					.orElseThrow(() -> new RegistroNoEncontradoException(
							"Estado no encontrada con ID: " + docenteDTO.getEstadoId().getNomValor()));

			docenteEntity.setTipoDocumId(tipoDocumentoEntity);
			docenteEntity.setUsuarioId(user);
			docenteEntity.setInstitucionId(institucionEntity);
			docenteEntity.setEstadoId(estadoEntity);
			docenteEntity.setFecCreacion(new Date());

			DocenteMapper.INSTANCE.updateDocenteFromDto(docenteDTO, docenteEntity);

			docenteEntity.setTipoDocumId(tipoDocumentoEntity);
			docenteEntity.setUsuarioId(user);
			docenteEntity.setInstitucionId(institucionEntity);
			docenteEntity.setEstadoId(estadoEntity);
			docenteEntity.setFecCreacion(new Date());

			DocenteEntity docententeActualizado = docenteRepository.save(docenteEntity);

			if (Validador.objetoEsNulo(docententeActualizado)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_MODIFICAR);
			}

			return DocenteMapper.INSTANCE.toDTO(docententeActualizado);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_EDITAR_REGISTRO);
		}
	}

	@Transactional
	public MateriaDTO crearMateria(@Valid MateriaDTO materiaDTO) {
		try {
			MateriaEntity materiaEntity = MateriaMapper.INSTANCE.toEntity(materiaDTO);

			InstitucionEntity institucionEntity = obtenerInstitucionUsuario();

			DocenteEntity docenteEntity = docenteRepository.findById(materiaDTO.getDocenteId().getId())
					.orElseThrow(() -> new RegistroNoEncontradoException("Docente no encontrado"));

			NivelEntity nivelEntity = nivelesRepository.findById(materiaDTO.getNivelId().getId())
					.orElseThrow(() -> new RegistroNoEncontradoException("Nivel no encontrado"));

			materiaEntity.setInstitucionId(institucionEntity);
			materiaEntity.setDocenteId(docenteEntity);
			materiaEntity.setNivelId(nivelEntity);
			materiaEntity.setFecCreacion(new Date());

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

			if (materiaDTO.getNivelId() != null && Validador.esNuloOVacio(materiaEntity.getNivelId().getId())) {
				materiaEntity.setNivelId(null);
			}

			List<MateriaEntity> materiasList = materiaService.consultarMaterias(materiaEntity);

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

			if (materiaDTO.getNivelId() != null && Validador.esNuloOVacio(materiaEntity.getNivelId().getId())) {
				materiaEntity.setNivelId(null);
			}

			MateriaEntity materiaEncontrada = materiaRepository.findById(materiaDTO.getId()).orElseThrow(
					() -> new RegistroNoEncontradoException(Mensajes.TXT_REGISTRO_NOT_FOUND_COD + materiaDTO.getId()));

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
			MateriaEntity materiaExistente = materiaRepository.findById(materiaActualizadaDTO.getId()).orElseThrow(
					() -> new RuntimeException("Materia no encontrada con ID: " + materiaActualizadaDTO.getId()));

			NivelEntity nivelEntity = nivelesRepository.findById(materiaActualizadaDTO.getNivelId().getId())
					.orElseThrow(() -> new RegistroNoEncontradoException(
							"Nivel no encontrada con ID: " + materiaActualizadaDTO.getNivelId().getNomNivel()));

			DocenteEntity docenteEntity = docenteRepository.findById(materiaActualizadaDTO.getDocenteId().getId())
					.orElseThrow(() -> new RegistroNoEncontradoException("Docente no encontrado"));

			MateriaMapper.INSTANCE.updateMateriaFromDto(materiaActualizadaDTO, materiaExistente);

			materiaExistente.setNivelId(nivelEntity);
			materiaExistente.setIntensidadHoras(materiaActualizadaDTO.getIntensidadHoras());
			materiaExistente.setDocenteId(docenteEntity);
			materiaExistente.setFecCreacion(new Date());

			MateriaEntity materiaGuardada = materiaRepository.save(materiaExistente);
			if (Validador.objetoEsNulo(materiaGuardada)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_MODIFICAR);
			}
			return MateriaMapper.INSTANCE.toDTO(materiaGuardada);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_EDITAR_REGISTRO);
		}
	}

	@Transactional
	public GradoDTO crearGrado(@Valid GradoDTO gradoDTO) {
		try {

			GradoEntity gradoEntity = GradoMapper.INSTANCE.toEntity(gradoDTO);

			InstitucionEntity institucionEntity = obtenerInstitucionUsuario();

			gradoEntity.setInstitucionId(institucionEntity);
			gradoEntity.setFecCreacion(new Date());

			GradoEntity gradoGuardado = gradoRepository.save(gradoEntity);
			if (Validador.objetoEsNulo(gradoGuardado)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_CREAR);
			}
			
			
			if(!Validador.isEmpty(gradoDTO.getMateriasList())){
				for (MateriaDTO materiaDTO : gradoDTO.getMateriasList()) {
					GradoMateriasEntity gradoMateriasEntity = new GradoMateriasEntity();
					gradoMateriasEntity.setGradoId(gradoGuardado);
					gradoMateriasEntity.setInstitucionId(institucionEntity);
					
					MateriaEntity materiaEntity = materiaRepository.findById(materiaDTO.getId())
			                .orElseThrow(() -> new RegistroNoGuardadoException("Materia no encontrada con ID: " + materiaDTO.getId()));
					
					gradoMateriasEntity.setMateriaId(materiaEntity);
					gradoMateriasRepository.save(gradoMateriasEntity);
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

			GradoEntity gradoEntity = GradoMapper.INSTANCE.toEntity(gradoDTO);
			List<GradoEntity> gradosList = gradosService.consultarGrado(gradoEntity);

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
	public GradoEntity consultarGradoById(@Valid GradoDTO gradoDTO) {
		try {

			GradoEntity gradoExistente = gradoRepository.findById(gradoDTO.getId())
					.orElseThrow(() -> new RegistroNoEncontradoException("Grado no encontrado"));

			return gradoExistente;
		} catch (RegistroNoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}
	
	@Transactional
	public List<MateriaEntity> consultarMateriasGradoById(Long id) {
		try {

			List<MateriaEntity> gradoMateriasList = gradoMateriasRepository.findMateriasByGradoId(id);

			if (Validador.isEmpty(gradoMateriasList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}

			return gradoMateriasList;
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
			
			DocenteEntity docenteEntity = docenteRepository.findById(gradoDTO.getDocenteId().getId())
					.orElseThrow(() -> new RegistroNoEncontradoException("Docente no encontrado"));
			
			InstitucionEntity institucionEntity = obtenerInstitucionUsuario();
			
			gradoDTO.setInstitucionId(InstitucionMapper.INSTANCE.toDTO(institucionEntity));
			gradoDTO.getDocenteId().setInstitucionId(InstitucionMapper.INSTANCE.toDTO(institucionEntity));
			gradoDTO.getDocenteId().setUsuarioId(docenteEntity.getUsuarioId());

			GradoMapper.INSTANCE.updateGradoFromDto(gradoDTO, gradoExistente);

			gradoExistente.setDocenteId(docenteEntity);
			gradoExistente.setInstitucionId(institucionEntity);

			GradoEntity gradoGuardado = gradoRepository.save(gradoExistente);
			if (Validador.objetoEsNulo(gradoGuardado)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_MODIFICAR);
			}
			
			
			//Obtener las materias actuales del grado
	        List<GradoMateriasEntity> materiasActuales = gradoMateriasRepository.findByGradoIdId(gradoGuardado.getId());

	        //Extraer los IDs de las materias actuales
	        Set<Long> idsMateriasActuales = materiasActuales.stream()
	                .map(gm -> gm.getMateriaId().getId())
	                .collect(Collectors.toSet());

	        //Extraer los IDs de las materias nuevas
	        Set<Long> idsMateriasNuevas = gradoDTO.getMateriasList().stream()
	                .map(MateriaDTO::getId)
	                .collect(Collectors.toSet());

	        //Eliminar solo las materias que ya no están en la lista nueva
	        for (GradoMateriasEntity gm : materiasActuales) {
	            if (!idsMateriasNuevas.contains(gm.getMateriaId().getId())) {
	                gradoMateriasRepository.delete(gm);
	            }
	        }
			
	        for (MateriaDTO materiaDTO : gradoDTO.getMateriasList()) {
	            if (!idsMateriasActuales.contains(materiaDTO.getId())) { // Solo insertar si no existe
	                MateriaEntity materiaEntity = materiaRepository.findById(materiaDTO.getId())
	                        .orElseThrow(() -> new RegistroNoGuardadoException("Materia no encontrada con ID: " + materiaDTO.getId()));

	                GradoMateriasEntity gradoMateriasEntity = new GradoMateriasEntity();
	                gradoMateriasEntity.setGradoId(gradoGuardado);
	                gradoMateriasEntity.setInstitucionId(institucionEntity);
	                gradoMateriasEntity.setMateriaId(materiaEntity);

	                gradoMateriasRepository.save(gradoMateriasEntity);
	            }
	        }
	        
			return GradoMapper.INSTANCE.toDTO(gradoGuardado);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_EDITAR_REGISTRO);
		}
	}

	@Transactional
	public GradoDTO eliminarGrado(@Valid GradoDTO gradoDTO) {
		try {

			// Verificar si el ID proporcionado existe en la base de datos
			GradoEntity gradoExistente = gradoRepository.findById(gradoDTO.getId())
					.orElseThrow(() -> new RuntimeException("Grado no encontrada con ID: " + gradoDTO.getId()));

			gradoRepository.deleteById(gradoExistente.getId());
			return GradoMapper.INSTANCE.toDTO(gradoExistente);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_ELIMINAR);
		}
	}

	@Override
	public InstitucionDTO crearInstitucion(@Valid InstitucionDTO institucionDTO) {
		try {

			InstitucionEntity institucionEntity = InstitucionMapper.INSTANCE.toEntity(institucionDTO);

			institucionEntity.setFecCreacion(new Date());
			
			SedeEntity sedeEntity = obtenerSede(institucionDTO.getSedeId()); 
			CiudadEntity ciudadEntity = obtenerCiudad(institucionDTO.getCiudadId());
			
			institucionEntity.setSedeId(sedeEntity);
			institucionEntity.setCiudadId(ciudadEntity);
			
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

			List<InstitucionEntity> institucionesList = institucionRepository.consultarInstitucion(
					institucionEntity.getNomInstitucion(), institucionEntity.getNit(),
					UserContext.getcurrentInstitucionUserId());

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
					.orElseThrow(() -> new RegistroNoEncontradoException(
							Mensajes.TXT_REGISTRO_NOT_FOUND_COD + institucionDTO.getId()));

			return institucion;
		} catch (RegistroNoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Transactional
	public InstitucionDTO actualizarInstitucion(@Valid InstitucionDTO institucionDTO) {
		try {

			// Verificar si el ID proporcionado existe en la base de datos
			InstitucionEntity institucionEntity = institucionRepository.findById(institucionDTO.getId()).orElseThrow(
					() -> new RuntimeException("Institución no encontrada con ID: " + institucionDTO.getId()));
		 
			SedeEntity sedeEntity = obtenerSede(institucionDTO.getSedeId()); 
			CiudadEntity ciudadEntity = obtenerCiudad(institucionDTO.getCiudadId());

			institucionEntity.setSedeId(sedeEntity);
			institucionEntity.setCiudadId(ciudadEntity);
			
			InstitucionMapper.INSTANCE.updateInstitucionFromDto(institucionDTO, institucionEntity);

			institucionEntity.setFecCreacion(new Date());
			institucionEntity.setSedeId(sedeEntity);
			institucionEntity.setCiudadId(ciudadEntity);
			

			InstitucionEntity institucionActualizado = institucionRepository.save(institucionEntity);
			if (Validador.objetoEsNulo(institucionActualizado)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_MODIFICAR);
			}
			return InstitucionMapper.INSTANCE.toDTO(institucionActualizado);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_EDITAR_REGISTRO);
		}
	}

	@Override
	public InstitucionDTO eliminarInstitucion(@Valid InstitucionDTO institucionDTO) {
		try {

			// Verificar si el ID proporcionado existe en la base de datos
			InstitucionEntity institucionExistente = institucionRepository.findById(institucionDTO.getId()).orElseThrow(
					() -> new RuntimeException("Instituciòn no encontrada con ID: " + institucionDTO.getId()));

			institucionRepository.deleteById(institucionExistente.getId());
			return InstitucionMapper.INSTANCE.toDTO(institucionExistente);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_ELIMINAR);
		}
	}

	@Override
	public PeriodoElectivoDTO crearPeriodoElectivo(@Valid PeriodoElectivoDTO periodoElectivoDTO) {
		try {

			List<String> codPeriodos = periodoElectivoRepository.findCodPeriodoElectivos(obtenerInstitucionUsuario().getId());

			String codigoPeriodoElectivo = generarCodigo(periodoElectivoDTO.getNomPeriodoElect(), codPeriodos);

			PeriodoElectivoEntity periodoElectivoEntity = PeriodoElectivoMapper.INSTANCE.toEntity(periodoElectivoDTO);

			InstitucionEntity institucionEntity = obtenerInstitucionUsuario();

			periodoElectivoEntity.setCodPeriodoElect(codigoPeriodoElectivo);
			periodoElectivoEntity.setInstitucionId(institucionEntity);
			periodoElectivoEntity.setFecCreacion(new Date());

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

	@Transactional
	public PeriodoElectivoDTO actualizarPeriodoElectivo(@Valid PeriodoElectivoDTO periodoElectivoDTO) {
		try {

			// Verificar si el ID proporcionado existe en la base de datos
			PeriodoElectivoEntity periodoElectivoEntity = periodoElectivoRepository.findById(periodoElectivoDTO.getId())
					.orElseThrow(() -> new RuntimeException(
							"Periodo electivo no encontrado con ID: " + periodoElectivoDTO.getId()));

			PeriodoElectivoMapper.INSTANCE.updatePeriodoElectivoFromDto(periodoElectivoDTO, periodoElectivoEntity);

			PeriodoElectivoEntity peroElectivoEntity = periodoElectivoRepository.save(periodoElectivoEntity);
			if (Validador.objetoEsNulo(peroElectivoEntity)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_MODIFICAR);
			}
			return PeriodoElectivoMapper.INSTANCE.toDTO(peroElectivoEntity);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_EDITAR_REGISTRO);
		}
	}

	@Override
	public PeriodoElectivoEntity consultarPeriodoElectivoById(@Valid PeriodoElectivoDTO periodoElectivoDTO) {
		try {

			PeriodoElectivoEntity periodoElectivoEntity = periodoElectivoRepository.findById(periodoElectivoDTO.getId())
					.orElseThrow(() -> new RegistroNoEncontradoException(
							Mensajes.TXT_REGISTRO_NOT_FOUND_COD + periodoElectivoDTO.getId()));

			return periodoElectivoEntity;
		} catch (RegistroNoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	public List<PeriodoElectivoEntity> consultarPeriodoElectivo(@Valid PeriodoElectivoDTO periodoElectivoDTO) {
		try {

			List<PeriodoElectivoEntity> perList = periodoElectivoRepository.consultarPeriodoElectivo(periodoElectivoDTO.getNomPeriodoElect(), 
																									 obtenerInstitucionUsuario().getId());

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
	@Transactional
	public List<SedeEntity> consultarSede(@Valid SedeDTO sedeDTO) {
		try {

			SedeEntity sedesEntity = SedeMapper.INSTANCE.toEntity(sedeDTO);
			List<SedeEntity> sedesList = sedeService.consultarSede(sedesEntity);

			if (Validador.isEmpty(sedesList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}

			return sedesList;
		} catch (RegistroNoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	@Transactional
	public SedeEntity consultarSedeById(@Valid SedeDTO sedeDTO) {
		try {

			SedeEntity sedeExistente = sedeRepository.findById(sedeDTO.getId())
					.orElseThrow(() -> new RegistroNoEncontradoException("Sede no encontrada"));

			return sedeExistente;
		} catch (RegistroNoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	@Transactional
	public SedeDTO crearSede(@Valid SedeDTO sedeDTO) {
		try {

			SedeEntity sedeEntity = SedeMapper.INSTANCE.toEntity(sedeDTO);

			SedeEntity sedeGuardada = sedeRepository.save(sedeEntity);
			if (Validador.objetoEsNulo(sedeGuardada)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_CREAR);
			}

			return SedeMapper.INSTANCE.toDTO(sedeGuardada);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			throw new RegistroNoGuardadoException(Mensajes.TXT_CODIGO_GRADO_EXISTE);
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
		}
	}

	@Override
	@Transactional
	public SedeDTO actualizarSede(@Valid SedeDTO sedeDTO) {
		try {

			// Verificar si el ID proporcionado existe en la base de datos
			SedeEntity sedeExistente = sedeRepository.findById(sedeDTO.getId())
					.orElseThrow(() -> new RuntimeException("Sede no encontrada con ID: " + sedeDTO.getId()));

			SedeMapper.INSTANCE.updateSedeFromDto(sedeDTO, sedeExistente);

			sedeExistente.setNomSede(sedeDTO.getNomSede());
			sedeExistente.setDescSede(sedeDTO.getDescSede());

			SedeEntity sedeGuardada = sedeRepository.save(sedeExistente);
			if (Validador.objetoEsNulo(sedeGuardada)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_MODIFICAR);
			}
			return SedeMapper.INSTANCE.toDTO(sedeGuardada);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_EDITAR_REGISTRO);
		}
	}

	@Override
	@Transactional
	public SedeDTO eliminarSede(@Valid SedeDTO sedeDTO) {
		try {

			// Verificar si el ID proporcionado existe en la base de datos
			SedeEntity sedeExistente = sedeRepository.findById(sedeDTO.getId())
					.orElseThrow(() -> new RuntimeException("Sede no encontrada con ID: " + sedeDTO.getId()));

			sedeRepository.deleteById(sedeExistente.getId());
			return SedeMapper.INSTANCE.toDTO(sedeExistente);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_ELIMINAR);
		}
	}
	
	@Override
	public List<MateriaEntity> consultarMateriaByIdDocente() {
		try {
			
			List<MateriaEntity> materiasList = materiaRepository.findByDocenteIdId(obtenerUsuarioApp().getId());

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
	

	@Override
	public List<EstudianteEntity> consultarEstudiantesByGradoId(@Valid Long gradoId) {
		try {
			
			List<EstudianteEntity> estudiantesList = estudianteRepository.findByInstitucionIdIdAndGradoIdId(obtenerInstitucionUsuario().getId(),
																											gradoId);

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
	public void crearEstudiantesNota(@Valid EstudiantesNotaDTO estudiantesNotaDTO) {

		try {
			EstudianteNotasEntity estudianteNotasEntity = new EstudianteNotasEntity();
			estudianteNotasEntity.setMateriaId(estudiantesNotaDTO.getMateriaId());
			estudianteNotasEntity.setGradoId(estudiantesNotaDTO.getGradoId());
			estudianteNotasEntity.setPeriodoElectivoId(estudiantesNotaDTO.getPeriodoElectivoId());
			estudianteNotasEntity.setNomEvaluacion(estudiantesNotaDTO.getNomEvaluacion());
			estudianteNotasEntity.setTipoEvaluacionId(estudiantesNotaDTO.getTipoEvaluacionId());
			estudianteNotasEntity.setUsuarioId(obtenerUsuarioApp());
			estudianteNotasEntity.setFecEvaluacion(new Date());

			EstudianteNotasEntity estudianteNotasGuardado = estudiantesNotaRepository.save(estudianteNotasEntity);

			for (EstudianteDTO estudianteDTO : estudiantesNotaDTO.getEstudiantes()) {
				DetalleEstudianteNotasEntity detalleEstudianteNotasEntity = new DetalleEstudianteNotasEntity();
				EstudianteEntity estudiantEntity = estudianteRepository.findById(estudianteDTO.getId())
						.orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + estudianteDTO.getId()));
				detalleEstudianteNotasEntity.setEstudianteId(estudiantEntity);
				detalleEstudianteNotasEntity.setNota(estudianteDTO.getNota());
				detalleEstudianteNotasEntity.setEstudianteNotasId(estudianteNotasGuardado);
				detalleEstudianteNotasEntity.setTxtObservaciones(estudianteDTO.getTxtObservaciones());

				detalleEstudiantesNotaRepository.save(detalleEstudianteNotasEntity);
			}

		} catch (RegistroNoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}
	

	@Override
	public void actualizarEstudiantesNota(@Valid EstudiantesNotaDTO estudiantesNotaDTO) {
		try {
			
			EstudianteNotasEntity estudianteNotasEntity = estudiantesNotaRepository.findById(estudiantesNotaDTO.getId())
																				   .orElseThrow(() -> new RegistroNoEncontradoException("Registro no encontrado"));
			
			estudianteNotasEntity.setMateriaId(estudiantesNotaDTO.getMateriaId());
			estudianteNotasEntity.setGradoId(estudiantesNotaDTO.getGradoId());
			estudianteNotasEntity.setPeriodoElectivoId(estudiantesNotaDTO.getPeriodoElectivoId());
			estudianteNotasEntity.setNomEvaluacion(estudiantesNotaDTO.getNomEvaluacion());
			estudianteNotasEntity.setTipoEvaluacionId(estudiantesNotaDTO.getTipoEvaluacionId());
			estudianteNotasEntity.setUsuarioId(obtenerUsuarioApp());
			estudianteNotasEntity.setFecEvaluacion(new Date());

			EstudianteNotasEntity estudianteNotasGuardado = estudiantesNotaRepository.save(estudianteNotasEntity);
			
			List<DetalleEstudianteNotasEntity> detalleNotasList = detalleEstudiantesNotaRepository.findByEstudianteNotasIdId(estudianteNotasGuardado.getId(),
																														!Validador.objetoEsNulo(estudiantesNotaDTO.getEstudianteId()) ?
																														estudiantesNotaDTO.getEstudianteId().getId():null);
			
			// Crear un mapa para acceso rápido a los estudiantes existentes por ID
			Map<Long, DetalleEstudianteNotasEntity> detalleEstudianteNota = detalleNotasList.stream()
			        .collect(Collectors.toMap(detalle -> detalle.getEstudianteId().getId(), Function.identity()));
			
			
			// Recorrer los estudiantes que llegan en el DTO
			for (EstudianteDTO estudianteDTO : estudiantesNotaDTO.getEstudiantes()) {
			  
			    if (estudianteDTO.getId() != null) {
			    	DetalleEstudianteNotasEntity detalleExistente = detalleEstudianteNota.get(estudianteDTO.getId());

			        // Si existe el estudiante existe en los detalles de la valoración, actualizar la nota
			        if (detalleExistente != null) {
			            detalleExistente.setNota(estudianteDTO.getNota());		  
			            detalleExistente.setTxtObservaciones(estudianteDTO.getTxtObservaciones());
			            // Guardar el detalle actualizado
			            detalleEstudiantesNotaRepository.save(detalleExistente);
			        }
			    }
			}
			
		} catch (RegistroNoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	public List<EstudianteNotasEntity> consultarEstudiantesNota(EstudiantesNotaDTO estudiantesNotaDTO) {
		try {
			
			List<EstudianteNotasEntity> notasEstudiantesList = estudiantesNotaRepository.buscarNotasEstudiante(obtenerUsuarioApp().getId(), 
															   !Validador.objetoEsNulo(estudiantesNotaDTO.getEstudianteId()) ? estudiantesNotaDTO.getEstudianteId().getId():null, 
															   !Validador.objetoEsNulo(estudiantesNotaDTO.getMateriaId()) ? estudiantesNotaDTO.getMateriaId().getId():null, 
															   !Validador.objetoEsNulo(estudiantesNotaDTO.getGradoId()) ? estudiantesNotaDTO.getGradoId().getId():null, 
															   !Validador.objetoEsNulo(estudiantesNotaDTO.getPeriodoElectivoId()) ? estudiantesNotaDTO.getPeriodoElectivoId().getId():null, 
															   Utilidades.convertirDateToString(estudiantesNotaDTO.getFecEvaluacion()));
			if (Validador.isEmpty(notasEstudiantesList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}

			return notasEstudiantesList;
			
			
		} catch (RegistroNoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	@Override
	public List<DetalleEstudianteNotasEntity> consultarEstudiantesNotaById(@Valid DetalleEstudiantesNotaDTO detalleNota) {
		try {
			
			List<DetalleEstudianteNotasEntity> notasEstudiantesList = detalleEstudiantesNotaRepository.findByEstudianteNotasIdId(detalleNota.getEstudiantesNotaId().getId(), 
																																 !Validador.esNuloOVacio(detalleNota.getEstudianteId()) ?
																																 detalleNota.getEstudianteId().getId():null);
																							   
			
			if (Validador.isEmpty(notasEstudiantesList)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}
			
			return notasEstudiantesList;
			
		} catch (RegistroNoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	private CiudadEntity obtenerCiudad(CiudadDTO ciudadId) {
		CiudadEntity ciudadEntity = null;
		
		if(!Validador.objetoEsNulo(ciudadId) && !Validador.esNuloOVacio(ciudadId.getId())) {
			
			ciudadEntity = ciudadRepository.findById(ciudadId.getId()).orElseThrow(
					() -> new RuntimeException("Ciudad no encontrada con el ID: " + ciudadId.getId()));
		}
		
		return ciudadEntity;
	}

	private SedeEntity obtenerSede(SedeDTO sedeId) {
		SedeEntity sedeEntity = null;
		if(!Validador.objetoEsNulo(sedeId) && !Validador.esNuloOVacio(sedeId.getId())) {
			
			sedeEntity = sedeRepository.findById(sedeId.getId()).orElseThrow(
					() -> new RuntimeException("Sede no encontrada con el ID: " + sedeId.getId()));
		}
		return sedeEntity;
	}

	private String generarCodigo(String nombre, List<String> codList) {
		return CodigosGenerator.generateUniqueCodigo(nombre, codList);
	}

	public Long consultarTipoDocumByCodigo(String codigo) {
		return tipoDocumentosRepository.findByCodTipDocum(codigo);
	}

	public Long consultarEstadoByCodigo(String codigo) {
		return estadosRepository.findByCodEstado(codigo);
	}
}