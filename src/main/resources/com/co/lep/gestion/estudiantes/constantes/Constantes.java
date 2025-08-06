package com.co.lep.gestion.estudiantes.constantes;

public class Constantes {

	public static final String REQUEST_URL_AUTH = "/api/auth";

	public static final String REQUEST_TOKEN_URL = "/login";

	public static final String REQUEST_LOGOUT = "/logout";

	public static final String REQUEST_REFRESH_TOKEN = "/refresh";
	

	public static final String AUTHORITIES_KEY = "CLAIM_TOKEN";

	public static final String SIGNING_KEY = "gestionEstudiantesflexicraft2024";

	public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 28800;

	public static final String ISSUER_TOKEN = "ISSUER";

	public static final String HEADER_AUTHORIZATION_KEY = "Authorization";

	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
	
	public static final String PREFIX_ESTUDIANTE = "est-";
	
	
	//ENDPOINT APLICACION
	public static final String URL_BASE = "/api/flexicraft";
	
	public static final String ENDPOINT_CREAR_ESTUDIANTE = "/crear_estudiante";
	public static final String ENDPOINT_CREAR_DOCENTE = "/crear_docente";
	public static final String ENDPOINT_CREAR_MATERIA = "/crear_materia";
	public static final String ENDPOINT_CREAR_GRADO = "/crear_grado";
	public static final String ENDPOINT_CREAR_INSTITUCION = "/crear_institucion";
	public static final String ENDPOINT_CREAR_PERIODO_ELECTIVO = "/crear_periodo_electivo";
	
	public static final String ENDPOINT_CONSULTAR_DOCENTE_BY_CODIGO = "/consultar_docentes_por_codigo";
	public static final String ENDPOINT_CONSULTAR_DOCENTE = "/consultar_docente";
	public static final String ENDPOINT_CONSULTAR_DOCENTES_ALL = "/consultar_all_docentes";
	public static final String ENDPOINT_CONSULTAR_ESTUDIANTE = "/consultar_estudiante";
	public static final String ENDPOINT_CONSULTAR_ESTUDIANTE_BY_ID = "/consultar_estudiante_id";
	public static final String ENDPOINT_CONSULTAR_MATERIA = "/consultar_materia";
	public static final String ENDPOINT_CONSULTAR_MATERIA_BY_ID = "/consultar_materia_id";
	public static final String ENDPOINT_CONSULTAR_GRADO = "/consultar_grado";
	public static final String ENDPOINT_CONSULTAR_INSTITUCION = "/consultar_institucion";
	public static final String ENDPOINT_CONSULTAR_INSTITUCION_BY_ID = "/consultar_institucion_id";
	public static final String ENDPOINT_CONSULTAR_PERIODO_ELECTIVO_BY_ID = "/consultar_periodo_electivo_id";
	public static final String ENDPOINT_CONSULTAR_PERIODO_ELECTIVO = "/consultar_periodo_electivo";
	
	public static final String ENDPOINT_EDITAR_MATERIA = "/editar_materia";
	public static final String ENDPOINT_EDITAT_GRADO = "/editar_grado";
	public static final String ENDPOINT_EDITAR_ESTUDIANTE = "editar_estudiante";
	
	public static final String ENDPOINT_ELIMINAR_GRADO = "/eliminar_grado";
	
	//ENDPOINT LISTA VALORES
	public static final String ENDPOINT_CONSULTAR_ESTADOS_ESTUDIANTES = "/consultar_estados_estudiantes";
	public static final String ENDPOINT_CONSULTAR_ESTADOS_ESTUDIANTES_BY_CDVALOR = "/consultar_estados_estudiantes/cod_valor";
	public static final String ENDPOINT_CONSULTAR_NIVELES = "/consultar_niveles";
	public static final String ENDPOINT_CONSULTAR_TIPOS_DOCUMENTOS = "/consultar_tipos_documentos";
	public static final String ENDPOINT_CONSULTAR_PER_ELECTIVO = "/consultar_periodo_electivo";
	public static final String ENDPOINT_CONSULTAR_ROLES_APLICACION = "/consultar_roles_aplicacion";
	

	//ENDPOINT BOLETINES
	public static final String ENDPOINT_CONSULTAR_BOLETINES = "/consultar_boletines";
	public static final String ENDPOINT_CREAR_BOLETIN = "/crear_boletin";

	public static final String GENERAR_BOLETIN_PDF = "/generar_boletin_pdf";

	public static final String ENDPOINT_DESCARGAR_CSV = "/descargar_xlsx";
	

	public static final String ENDPOINT_UPLOAD_CSV = "/crear_registro";

	public static final String CD_REFERENCIA_ESTUDIANTE = "ESTUDIANTE";

	public static final String CD_ESTADOS_ESTUDIANTES = "CD_ESTADO_ESTUDIANTE";
	public static final String CD_ESTADO_ACTIVO = "A";
	
	public static final String CD_ESTADO_USUARIO_APP = "CD_ESTADO_USUARIO_APP";
	
	public static final String INSTA_DB_PPAL = "instanciaDBPrincipal";
	
	public static final String ENDPOINT_GENERA_LOGIN = "/generar_login";
	
	//ENDPOINT MENU USUARIO
	public static final String ENDPOINT_MENU_APP_USER = "/menu_list";
}
