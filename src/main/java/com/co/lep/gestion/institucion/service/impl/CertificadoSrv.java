package com.co.lep.gestion.institucion.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.dto.BoletinNotasPeriodosDTO;
import com.co.lep.gestion.estudiantes.entity.BoletinEntity;
import com.co.lep.gestion.estudiantes.entity.PeriodoElectivoEntity;
import com.co.lep.gestion.estudiantes.exepciones.BoletinNoEncontradoException;
import com.co.lep.gestion.estudiantes.repository.BoletinDetalleRepository;
import com.co.lep.gestion.estudiantes.repository.BoletinRepository;
import com.co.lep.gestion.estudiantes.repository.EstudianteRepository;
import com.co.lep.gestion.estudiantes.repository.impl.AdminPrincipal;
import com.co.lep.gestion.estudiantes.service.impl.BaseService;
import com.co.lep.gestion.estudiantes.utilidades.Desempenio;
import com.co.lep.gestion.estudiantes.utilidades.Validador;
import com.co.lep.gestion.institucion.service.ICertificadoSrv;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class CertificadoSrv  extends BaseService implements ICertificadoSrv{
	
	private final Configuration freeMarkerConfig;
	
	@Autowired
	EstudianteRepository estudianteRepository;
	
	@Autowired
	BoletinRepository boletinRepository;
	
	@Autowired
	BoletinDetalleRepository boletinDetalleRepository;
	
	@Autowired
	AdminPrincipal adminPrincipal;
	
	public CertificadoSrv(Configuration freeMarkerConfig) {
		this.freeMarkerConfig = freeMarkerConfig;
	}

	public byte[] generarCertificadoPDF(Long estudianteId) {
		try {
	        // Cargar la plantilla HTML
	        Template template = freeMarkerConfig.getTemplate("certificados/certificado.html");

	        BoletinEntity boletinEstudiante = boletinRepository.findByEstudianteIdIdAndPeriodoElectivoIdId(estudianteId,1L)
	                .orElseThrow(() -> new BoletinNoEncontradoException("El estudiante no tiene boletines registrados"));
	        
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
	        
	        List<PeriodoElectivoEntity> codPeriodosElectivosEstudiante = boletinRepository.obtenerPeriodosPorEstudiante(boletinEstudiante.getEstudianteId().getId());
	        
	        if(!Validador.isEmpty(codPeriodosElectivosEstudiante)) {
	        	
	        	for (PeriodoElectivoEntity periodoElectivo : codPeriodosElectivosEstudiante) {
	        		Integer numHorasNoAsistidas = null;

	        		if(!Validador.objetoEsNulo(boletinEstudiante)) {        			
	        			if(periodoElectivo.getCodPeriodoElect().equalsIgnoreCase(Constantes.CD_PER_PRIMERO)) {
	        				
	        				promedioPeriodoElectivo1 = boletinDetalleRepository.promedioTotalNotasEstudiantePorPeriodo(
	        						boletinEstudiante.getEstudianteId().getId(),
	        						periodoElectivo.getId());
	        				
	        				numHorasNoAsistidas = adminPrincipal.consultarNumHorasNoAsistidasEstudiante(
	        						boletinEstudiante.getEstudianteId().getId(),
	        						boletinEstudiante.getId(),
	        						periodoElectivo.getId());
	        				
	        				model.put("numHorasNoAsistidas1", (numHorasNoAsistidas != null && numHorasNoAsistidas != 0) ? numHorasNoAsistidas : "");
	        						
	        			}else if(periodoElectivo.getCodPeriodoElect().equalsIgnoreCase(Constantes.CD_PER_SEGUNGO)) {
	        				promedioPeriodoElectivo2 = boletinDetalleRepository.promedioTotalNotasEstudiantePorPeriodo(
	        						boletinEstudiante.getEstudianteId().getId(),	
	        						periodoElectivo.getId()); 
	        				
	        				numHorasNoAsistidas = adminPrincipal.consultarNumHorasNoAsistidasEstudiante(
	        						boletinEstudiante.getEstudianteId().getId(),
	        						boletinEstudiante.getId(),
	        						periodoElectivo.getId());
	        				
	        				model.put("numHorasNoAsistidas2", (numHorasNoAsistidas != null && numHorasNoAsistidas != 0) ? numHorasNoAsistidas : "");
	        				
	        			}else if(periodoElectivo.getCodPeriodoElect().equalsIgnoreCase(Constantes.CD_PER_TERCERO)) {
	        				promedioPeriodoElectivo3 = boletinDetalleRepository.promedioTotalNotasEstudiantePorPeriodo(
	        						boletinEstudiante.getEstudianteId().getId(),
	        						periodoElectivo.getId());
	        				
	        				numHorasNoAsistidas = adminPrincipal.consultarNumHorasNoAsistidasEstudiante(
	        						boletinEstudiante.getEstudianteId().getId(),
	        						boletinEstudiante.getId(),
	        						periodoElectivo.getId());

	        				model.put("numHorasNoAsistidas3", (numHorasNoAsistidas != null && numHorasNoAsistidas != 0) ? numHorasNoAsistidas : "");
	        				
	        			}else if(periodoElectivo.getCodPeriodoElect().equalsIgnoreCase(Constantes.CD_PER_CUARTO)) {
	        				promedioPeriodoElectivo4 = boletinDetalleRepository.promedioTotalNotasEstudiantePorPeriodo(
	        						boletinEstudiante.getEstudianteId().getId(),	
	        						periodoElectivo.getId());
	        				
	        				numHorasNoAsistidas = adminPrincipal.consultarNumHorasNoAsistidasEstudiante(
	        						boletinEstudiante.getEstudianteId().getId(),
	        						boletinEstudiante.getId(),
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
	        
	        List<BoletinNotasPeriodosDTO> boletinDetalle = boletinDetalleRepository.obtenerNotasPorPeriodo(boletinEstudiante.getEstudianteId().getId());
	        
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
	        model = prepararModeloBoletin(boletinEstudiante, boletinDetalle, model);

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

	    } catch (BoletinNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error interno al generar el certificado", e);
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
	    model.put("dpto", boletin.getInstitucionId().getCiudadId().getDpto());
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
