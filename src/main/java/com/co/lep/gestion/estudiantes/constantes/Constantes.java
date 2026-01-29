package com.co.lep.gestion.estudiantes.constantes;

public class Constantes {
	
	public static final String APPLICATION_NAME = "Sistema de Gestión";

	public static final String REQUEST_URL_AUTH = "/api/auth";

	public static final String REQUEST_TOKEN_URL = "/login";

	public static final String REQUEST_LOGOUT = "/logout";

	public static final String REQUEST_REFRESH_TOKEN = "/refresh";
	

	public static final String AUTHORITIES_KEY = "CLAIM_TOKEN";
	
	//Roles
	public static final String ROLE_ADMIN = "ADMIN";

	public static final String SIGNING_KEY = "gestionEstudiantesflexicraft2024";

	public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 28800;

	public static final String ISSUER_TOKEN = "ISSUER";

	public static final String HEADER_AUTHORIZATION_KEY = "Authorization";

	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
	
	public static final String PREFIX_ESTUDIANTE = "est-";
	
	
	//ENDPOINT APLICACION
	public static final String URL_BASE = "/api/devsenior";
	
	//Cambio password
	public static final String ENDPOINT_CAMBIO_PASSWORD_USUARIO_APP = "/cambiar_contrasenia_usuario";
	public static final String PASSWORD_RESET = "/solicitar_reset_pwd";
	public static final String VALIDA_PASSWORD_RESET = "/valida_token_reset_password";
	public static final String RESET_PASSWORD = "/reset_password";
	
	public static final String ENDPOINT_CREAR_USUARIO_APP = "/crear_usuario";
	public static final String ENDPOINT_CREAR_ESTUDIANTE = "/crear_estudiante";
	public static final String ENDPOINT_CREAR_DOCENTE = "/crear_docente";
	public static final String ENDPOINT_CREAR_MATERIA = "/crear_materia";
	public static final String ENDPOINT_CREAR_GRADO = "/crear_grado";
	public static final String ENDPOINT_CREAR_INSTITUCION = "/crear_institucion";
	public static final String ENDPOINT_CREAR_PERIODO_ELECTIVO = "/crear_periodo_electivo";
	public static final String ENDPOINT_CREAR_SEDE = "/crear_sede";
	
	public static final String ENDPOINT_CONSULTAR_USUARIO = "/consultar_usuario";
	public static final String ENDPOINT_CONSULTAR_USUARIO_BY_ID = "/consultar_usuario_id";
	public static final String ENDPOINT_EDITAR_USUARIO = "/editar_usuario";
	
	public static final String ENDPOINT_CONSULTAR_DOCENTE_BY_CODIGO = "/consultar_docentes_por_codigo";
	public static final String ENDPOINT_CONSULTAR_DOCENTE = "/consultar_docente";
	public static final String ENDPOINT_CONSULTAR_DOCENTE_BY_ID = "/consultar_docente_id";
	public static final String ENDPOINT_CONSULTAR_DOCENTES_ALL = "/consultar_all_docentes";
	public static final String ENDPOINT_CONSULTAR_ESTUDIANTE = "/consultar_estudiante";
	public static final String ENDPOINT_CONSULTAR_ESTUDIANTE_BY_ID = "/consultar_estudiante_id";
	public static final String ENDPOINT_CONSULTAR_MATERIA = "/consultar_materia";
	public static final String ENDPOINT_CONSULTAR_MATERIA_BY_ID = "/consultar_materia_id";
	public static final String ENDPOINT_CONSULTAR_GRADO = "/consultar_grado";
	public static final String ENDPOINT_CONSULTAR_GRADO_BY_ID = "consultar_grado_id";
	public static final String ENDPOINT_CONSULTAR_INSTITUCION = "/consultar_institucion";
	public static final String ENDPOINT_CONSULTAR_INSTITUCION_BY_ID = "/consultar_institucion_id";
	public static final String ENDPOINT_CONSULTAR_PERIODO_ELECTIVO_BY_ID = "/consultar_periodo_electivo_id";
	public static final String ENDPOINT_CONSULTAR_PERIODO_ELECTIVO = "/consultar_periodo_electivo";
	public static final String ENDPOINT_CONSULTAR_SEDE = "/consultar_sede";
	public static final String ENDPOINT_CONSULTAR_SEDE_BY_ID = "consultar_sede_id";
	
	public static final String ENDPOINT_EDITAR_MATERIA = "/editar_materia";
	public static final String ENDPOINT_EDITAT_GRADO = "/editar_grado";
	public static final String ENDPOINT_EDITAR_ESTUDIANTE = "/editar_estudiante";
	public static final String ENDPOINT_EDITAT_INSTITUCION = "/editar_institucion";
	public static final String ENDPOINT_EDITAR_DOCENTE = "/editar_docente";
	public static final String ENDPOINT_EDITAR_SEDE = "/editar_sede";
	
	public static final String ENDPOINT_ELIMINAR_GRADO = "/eliminar_grado";
	public static final String ENDPOINT_ELIMINAR_INSTITUCION = "/eliminar_institucion";
	public static final String ENDPOINT_ELIMINAR_BOLETIN = "/eliminar_boletin";
	public static final String ENDPOINT_ELIMINAR_SEDE = "/eliminar_sede";
	
	//ENDPOINT LISTA VALORES
	public static final String ENDPOINT_CONSULTAR_ESTADOS_ESTUDIANTES = "/consultar_estados_estudiantes";
	public static final String ENDPOINT_CONSULTAR_ESTADOS_ESTUDIANTES_BY_CDVALOR = "/consultar_estados_estudiantes/cod_valor";
	public static final String ENDPOINT_CONSULTAR_NIVELES = "/consultar_niveles";
	public static final String ENDPOINT_CONSULTAR_TIPOS_DOCUMENTOS = "/consultar_tipos_documentos";
	public static final String ENDPOINT_CONSULTAR_PER_ELECTIVO= "/consultar_periodo_electivo";
	public static final String ENDPOINT_CONSULTAR_ROLES_APLICACION = "/consultar_roles_aplicacion";
	public static final String ENDPOINT_CONSULTAR_ESTADOS = "/consultar_estados";
	public static final String ENDPOINT_CONSULTAR_MATERIA_BY_ID_DOCENTE = "/consultar_materias_docente";
	
	//ENDPOINT ESTUDIANTES NOTA
	public static final String ENDPOINT_CREAR_ESTUDIANTES_NOTA = "/crear_estudiantes_nota";
	public static final String ENDPOINT_CONSULTAR_ESTUDIANTES_NOTA = "/consultar_estudiantes_nota";
	public static final String ENDPOINT_EDITAR_ESTUDIANTES_NOTA = "/editar_estudiantes_nota";
	public static final String ENDPOINT_ELIMINAR_ESTUDIANTES_NOTA = "/eliminar_estudiantes_nota";
	public static final String ENDPOINT_CONSULTAR_ESTUDIANTES_NOTA_BY_ID = "/consultar_estudiantes_nota_id";
	

	//ENDPOINT BOLETINES
	public static final String ENDPOINT_CONSULTAR_BOLETINES = "/consultar_boletines";
	public static final String REQUEST_CONSULTAR_BOLETIN_BY_ID = "/consultar_boletin_id";
	public static final String ENDPOINT_CONSULTAR_DETALLE_BOLETINES = "/consultar_detalle_boletines";
	public static final String ENDPOINT_CONSULTAR_DETALLE_BOLETINES_BY_ID = "/consultar_detalle_boletin_id";
	public static final String ENDPOINT_CREAR_BOLETIN = "/crear_boletines";
	public static final String ENDPOINT_EDITAR_BOLETIN = "/editar_boletines";
	public static final String GENERAR_BOLETIN_PDF = "/generar_boletin_pdf";
	
	
	//ENDPOINT CERTIFICADOS
	public static final String GENERAR_CERTIFICADO_PDF = "/generar_certificado_pdf";

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

	public static final String ENDPOINT_EDITAT_PERIODO_ELECTIVO = "/editar_periodo_electivo";

	public static final String CD_PER_PRIMERO = "PRI";
	public static final String CD_PER_SEGUNGO = "SEG";
	public static final String CD_PER_TERCERO = "TER";
	public static final String CD_PER_CUARTO  = "CUA";
	
	public static final String ENDPOINT_GENERA_COMENTARIO = "/generar_comentario";
	public static final String ENDPOINT_GENERA_COMENTARIO_v2 = "/generar_comentario_v2";
	public static final String ENDPOINT_CONSULTAR_PROMPT = "/consultar_prompt";
	public static final String ENDPOINT_EDITAR_PROMPT = "/editar_prompt";
	public static final String ENDPOINT_CONSULTAR_CIUDAD = "/ciudad_buscar";

	public static final String SI = "SI";
	public static final String NO = "NO";

	public static final String BLANK = "";

	public static final String ENDPOINT_CONSULTAR_COLUMNAS = "/consultar_columnas";
	public static final String ENDPOINT_GUARDAR_CONFIGURACION_BOLETIN= "/guardar_configuracion";
	public static final String ENDPOINT_CONSULTAR_INFO_CONFIGURACION_BOLETIN = "/consultar_info_configuracion";

	public static final String ENDPOINT_CONSULTAR_MATERIAS_GRADO_BY_ID = "/consultar_materias_grado";
	public static final String ENDPOINT_CONSULTAR_ESTUDIANTE_BY_GRADO_ID = "/consultar_estudiantes_grado";

	public static final String VAULT_URL = "http://iotmonitev.com:8200";
    public static final String ROLE_ID   = "53840445-e7ba-f8f7-49d0-a240acbb71f2";
    public static final String SECRET_ID = "461177e5-d40b-fb6d-954b-5cd8a59ca80d";
    public static final String SECRET_PATH = "secret/data/nextcol";
    public static final String ENCRYPTOR_KEY = "encryption_key";
	public static final String ENCRYPTOR_KEY_DB = "db_password";
	public static final String GPT_MODEL_KEY = "gpt_model_key";
	public static final String DEVSENIOR_API_KEY = "devsenior_api_key";

	public static final String COD_CAMPO_URL_BASE = "URL_BASE_FRONTEND";

	public static final String URL_BASE_LOCALHOST = "http://localhost:4200";

	public static final String ENDPOINT_CONSULTAR_TIPO_EVALUACION = "/consultar_tipos_evaluacion";
}
