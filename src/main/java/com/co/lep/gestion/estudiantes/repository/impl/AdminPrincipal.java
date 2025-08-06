package com.co.lep.gestion.estudiantes.repository.impl;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.entity.BoletinDetalleEntity;
import com.co.lep.gestion.estudiantes.entity.BoletinEntity;
import com.co.lep.gestion.estudiantes.entity.DocenteEntity;
import com.co.lep.gestion.estudiantes.entity.EstudianteEntity;
import com.co.lep.gestion.estudiantes.entity.GradoEntity;
import com.co.lep.gestion.estudiantes.entity.MateriaEntity;
import com.co.lep.gestion.estudiantes.entity.SedeEntity;
import com.co.lep.gestion.estudiantes.repository.BaseRepository;
import com.co.lep.gestion.estudiantes.security.entity.User;
import com.co.lep.gestion.estudiantes.utilidades.UserLoginApp;
import com.co.lep.gestion.estudiantes.utilidades.Validador;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Component
public class AdminPrincipal extends BaseRepository {

	@Autowired
	private UserLoginApp userLoginApp;

	public AdminPrincipal(UserLoginApp userLoginApp) {
		super(Constantes.INSTA_DB_PPAL);
		this.userLoginApp = userLoginApp;
	}
	
	private static final Map<String, String> ALIAS_COLUMNAS = Map.ofEntries(
		    new AbstractMap.SimpleEntry<>("id", "ID"),
		    new AbstractMap.SimpleEntry<>("estudiante_id", "Estudiante"),
		    new AbstractMap.SimpleEntry<>("grado_id", "Grado"),
		    new AbstractMap.SimpleEntry<>("institucion_id", "Institución"),
		    new AbstractMap.SimpleEntry<>("docente_id", "Docente"),
		    new AbstractMap.SimpleEntry<>("periodo_electivo_id", "Período Electivo"),
		    new AbstractMap.SimpleEntry<>("usuario_id", "Usuario"),
		    new AbstractMap.SimpleEntry<>("txt_observaciones", "Observaciones"),
		    new AbstractMap.SimpleEntry<>("fec_creacion", "Fecha de Creación"),
		    new AbstractMap.SimpleEntry<>("num_horas_no_asistidas", "Horas No Asistidas"),
		    new AbstractMap.SimpleEntry<>("promovido_grado_id", "Grado Promovido"),
		    new AbstractMap.SimpleEntry<>("aprobado", "Aprobado"),
		    new AbstractMap.SimpleEntry<>("convivencia_escolar", "Convivencia Escolar"),
		    new AbstractMap.SimpleEntry<>("nro_puesto_estudiante", "Puesto del Estudiante"),
		    new AbstractMap.SimpleEntry<>("nota_convivencia_escolar", "Nota de Convivencia Escolar")
		);


	@Transactional
	public List<EstudianteEntity> consultarEstudiantes(@Valid EstudianteEntity estudianteEntity) {
		EntityManager em = getEntityManager();

		String query = "SELECT DISTINCT e FROM EstudianteEntity e " 
				+ "LEFT JOIN FETCH e.gradoId " 
				+ "LEFT JOIN FETCH e.estadoId "
				+ "LEFT JOIN FETCH e.tipoDocumId " 
				+ "WHERE (:nomEstudiante IS NULL OR e.nomEstudiante = :nomEstudiante) "
				+ "AND (:tipoDocumId IS NULL OR e.tipoDocumId.id = :tipoDocumId) "
				+ "AND (:codDocum IS NULL OR e.codDocum = :codDocum) "
				+ "AND (:gradoId IS NULL OR e.gradoId.id = :gradoId) "
				+ "AND (:usuarioId IS NULL OR e.usuarioId.id = :usuarioId) ";

		TypedQuery<EstudianteEntity> consulta = em.createQuery(query, EstudianteEntity.class);
		consulta.setParameter("nomEstudiante", estudianteEntity.getNomEstudiante());
		consulta.setParameter("tipoDocumId",
				estudianteEntity.getTipoDocumId() != null ? estudianteEntity.getTipoDocumId().getId() : null);
		consulta.setParameter("codDocum", estudianteEntity.getCodDocum());
		consulta.setParameter("gradoId",
				estudianteEntity.getGradoId() != null ? estudianteEntity.getGradoId().getId() : null);
		consulta.setParameter("usuarioId", obtenerUsuarioApp());

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
				+ "AND (:institucionId IS NULL OR e.institucionId.id = :institucionId) " 
				+ "ORDER BY e.nomMateria";

		TypedQuery<MateriaEntity> consulta = em.createQuery(query, MateriaEntity.class);
		consulta.setParameter("nomMateria",
				!Validador.esNuloOVacio(materiaEntity.getNomMateria()) ? materiaEntity.getNomMateria() : null);
		consulta.setParameter("nivelId", materiaEntity.getNivelId() != null ? materiaEntity.getNivelId().getId():null);
		consulta.setParameter("institucionId", obtenerInstitucionUsuarioApp());

		List<MateriaEntity> listaMaterias = consulta.getResultList();
		for (MateriaEntity materia : listaMaterias) {
			em.refresh(materia);
		}
		return listaMaterias;
	}

	@Transactional
	public List<DocenteEntity> consultarDocente(DocenteEntity docenteEntity) {
		EntityManager em = getEntityManager();

		String query = "SELECT DISTINCT e FROM DocenteEntity e " + "JOIN FETCH e.tipoDocumId "
				+ "JOIN FETCH e.usuarioId " + "JOIN FETCH e.institucionId " + "JOIN FETCH e.estadoId "
				+ "WHERE (:nomDocente IS NULL OR e.nomDocente = :nomDocente) "
				+ "AND (:tipoDocumId IS NULL OR e.tipoDocumId.id = :tipoDocumId) "
				+ "AND (:codDocum IS NULL OR e.codDocum = :codDocum) "
			//	+ "AND (:usuarioId IS NULL OR e.usuarioId.id = :usuarioId) "
				+ "AND (:institucionId IS NULL OR e.institucionId.id = :institucionId) "
				+ "AND (:estadoId IS NULL OR e.estadoId.id = :estadoId) " + "ORDER BY e.nomDocente";

		TypedQuery<DocenteEntity> consulta = em.createQuery(query, DocenteEntity.class);
		consulta.setParameter("nomDocente", docenteEntity.getNomDocente());
		consulta.setParameter("tipoDocumId",
				docenteEntity.getTipoDocumId() != null ? docenteEntity.getTipoDocumId().getId() : null);
		consulta.setParameter("codDocum", docenteEntity.getCodDocum());
		//consulta.setParameter("usuarioId", obtenerUsuarioApp());
		consulta.setParameter("institucionId", obtenerInstitucionUsuarioApp() != null ? obtenerInstitucionUsuarioApp() : null);
		consulta.setParameter("estadoId", docenteEntity.getEstadoId() != null ? docenteEntity.getEstadoId().getId() : null);

		List<DocenteEntity> listaDocentes = consulta.getResultList();
		for (DocenteEntity docente : listaDocentes) {
			em.refresh(docente);
		}
		return listaDocentes;
	}

	@Transactional
	public List<GradoEntity> consultarGrados(GradoEntity gradoEntity){
		EntityManager em = getEntityManager();
		
		String query = "SELECT e FROM GradoEntity e " +
		           "WHERE (:nomGrado IS NULL OR e.nomGrado = :nomGrado) " +
		           "AND (:anioElectivo IS NULL OR e.anioElectivo = :anioElectivo)" +
		           "AND (:institucionId IS NULL OR e.institucionId.id = :institucionId) " +
		           "ORDER BY e.nomGrado";

		TypedQuery<GradoEntity> consulta = em.createQuery(query, GradoEntity.class);
		consulta.setParameter("nomGrado", gradoEntity.getNomGrado());
		consulta.setParameter("anioElectivo", gradoEntity.getAnioElectivo());
		consulta.setParameter("institucionId", obtenerInstitucionUsuarioApp());
		
		List<GradoEntity> listaGrados = consulta.getResultList();
		for (GradoEntity grado : listaGrados) {
		    em.refresh(grado);
		}
		
		return listaGrados;
	}
	
	@Transactional
	public List<BoletinEntity> consultarBoletin(BoletinEntity boletinEntity){
		EntityManager em = getEntityManager();
		
		String query = "SELECT e FROM BoletinEntity e " +
				   "WHERE (:estudianteId IS NULL OR e.estudianteId.id = :estudianteId) " +
				   "AND   (:gradoId IS NULL OR e.gradoId.id = :gradoId) " +
				   "AND   (:docenteId IS NULL OR e.docenteId.id = :docenteId) " +
				   "AND   (:periodoElectivoId IS NULL OR e.periodoElectivoId.id = :periodoElectivoId) " +
				   "AND   (:usuarioId IS NULL OR e.usuarioId.id = :usuarioId) ";
		
		TypedQuery<BoletinEntity> consulta = em.createQuery(query, BoletinEntity.class);
		consulta.setParameter("estudianteId", boletinEntity.getEstudianteId() != null ? boletinEntity.getEstudianteId().getId() : null);
		consulta.setParameter("gradoId", boletinEntity.getGradoId() != null ? boletinEntity.getGradoId().getId() : null);
		consulta.setParameter("docenteId", boletinEntity.getDocenteId() != null ? boletinEntity.getDocenteId().getId() : null);
		consulta.setParameter("periodoElectivoId", boletinEntity.getPeriodoElectivoId() != null ? boletinEntity.getPeriodoElectivoId().getId() : null);
		consulta.setParameter("usuarioId", obtenerUsuarioApp());
		
		List<BoletinEntity> listaBoletines = consulta.getResultList();
		for (BoletinEntity boletin : listaBoletines) {
		    em.refresh(boletin);
		}
		return listaBoletines;
	}
	
	@Transactional
	public List<BoletinDetalleEntity> consultarBoletinesEstudiante(BoletinEntity boletinEntity){
		EntityManager em = getEntityManager();
		
		String query = "SELECT e FROM BoletinDetalleEntity e " +
				"LEFT JOIN BoletinEntity b ON b.id = e.boletinId.id " + 
				"WHERE (:estudianteId IS NULL OR b.estudianteId.id = :estudianteId) " +
				"AND   (:gradoId IS NULL OR b.gradoId.id = :gradoId) " +
				"AND   (:docenteId IS NULL OR b.docenteId.id = :docenteId) " +
				"AND   (:periodoElectivoId IS NULL OR b.periodoElectivoId.id = :periodoElectivoId) ";

		TypedQuery<BoletinDetalleEntity> consulta = em.createQuery(query, BoletinDetalleEntity.class);
		consulta.setParameter("estudianteId", boletinEntity.getEstudianteId() != null ? boletinEntity.getEstudianteId().getId() : null);
		consulta.setParameter("gradoId", boletinEntity.getGradoId() != null ? boletinEntity.getGradoId().getId() : null);
		consulta.setParameter("docenteId", boletinEntity.getDocenteId() != null ? boletinEntity.getDocenteId().getId() : null);
		consulta.setParameter("periodoElectivoId", boletinEntity.getPeriodoElectivoId() != null ? boletinEntity.getPeriodoElectivoId().getId() : null);
		
		List<BoletinDetalleEntity> listaBoletines = consulta.getResultList();
		for (BoletinDetalleEntity boletin : listaBoletines) {
		    em.refresh(boletin);
		}
		return listaBoletines;
	}
	
	@Transactional
	public List<SedeEntity> consultarSede(SedeEntity sedeEntity) {
		EntityManager em = getEntityManager();
		
		String query = "SELECT e FROM SedeEntity e " +
	               	   "WHERE (:nomSede IS NULL OR e.nomSede LIKE CONCAT('%', :nomSede, '%')) " +
	               	  "ORDER BY e.nomSede";


		TypedQuery<SedeEntity> consulta = em.createQuery(query, SedeEntity.class);
		consulta.setParameter("nomSede", sedeEntity.getNomSede());
		
		List<SedeEntity> listaSedes = consulta.getResultList();
		for (SedeEntity sede : listaSedes) {
		    em.refresh(sede);
		}
		
		return listaSedes;
	}
	
	private Long obtenerUsuarioApp() {
		Long idUsuario = null;

		User user = userLoginApp.getUsuarioInfoDTO();

		if (!Validador.objetoEsNulo(user)) {
			if (!user.getRoleId().getName().equals(Constantes.ROLE_ADMIN)) {
				idUsuario = user.getId();
			}
		}

		return idUsuario;
	}

	private Long obtenerInstitucionUsuarioApp() {
		Long idInstitucionUsuario = null;

		User user = userLoginApp.getUsuarioInfoDTO();

		if (!Validador.objetoEsNulo(user)) {
			idInstitucionUsuario = user.getInstitucionId() != null ? user.getInstitucionId().getId() : null;
		}

		return idInstitucionUsuario;
	}

	@Transactional
	public Integer consultarNumHorasNoAsistidasEstudiante(Long estudianteId, Long boletinId, Long periodoElectivoId) {
		EntityManager em = getEntityManager();
		
		String query =  "SELECT e.numHorasNoAsistidas FROM BoletinEntity e " +
		        		"WHERE e.estudianteId.id = :estudianteId " +
		        		"AND e.id = :boletinId " +
		        		"AND e.periodoElectivoId.id = :periodoElectivoId";


		TypedQuery<Integer> consulta = em.createQuery(query, Integer.class);
		consulta.setParameter("estudianteId", estudianteId);
		consulta.setParameter("boletinId", boletinId);
		consulta.setParameter("periodoElectivoId", periodoElectivoId);
		
		List<Integer> resultados = consulta.getResultList();
		Integer result = resultados.isEmpty() ? null : resultados.get(0);
		
		return result;
	}

	@Transactional
	public List<String> consultarColumnasTabla() {
		EntityManager em = getEntityManager();
		
		String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'boletin'";
	    
	    @SuppressWarnings("unchecked")
	    List<String> listaColumnas = em.createNativeQuery(query).getResultList();
		
		return listaColumnas.stream()
	            .map(columna -> ALIAS_COLUMNAS.getOrDefault(columna, columna))
	            .collect(Collectors.toList());
	}
}
