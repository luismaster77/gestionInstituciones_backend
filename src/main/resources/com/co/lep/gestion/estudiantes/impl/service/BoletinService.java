package com.co.flexicraftsolutions.gestion.estudiantes.impl.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.co.flexicraftsolutions.gestion.estudiantes.constantes.Mensajes;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.BoletinDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.BoletinEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.flexicraftsolutions.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.flexicraftsolutions.gestion.estudiantes.mapper.BoletinMapper;
import com.co.flexicraftsolutions.gestion.estudiantes.repository.BoletinRepository;
import com.co.flexicraftsolutions.gestion.estudiantes.service.IBoletinService;
import com.co.flexicraftsolutions.gestion.estudiantes.utilidades.UserLoginApp;
import com.co.flexicraftsolutions.gestion.estudiantes.utilidades.Validador;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class BoletinService  implements IBoletinService{
	
	@Autowired
	BoletinRepository boletinRepository;
	
	@Autowired
	UserLoginApp userLoginApp;
	
	private final Configuration freeMarkerConfig;
	
	public BoletinService(Configuration freeMarkerConfig) {
		this.freeMarkerConfig = freeMarkerConfig;
	}

	public List<BoletinEntity> consultarBoletines(BoletinDTO boletinDTO) {
		try {
			
			boletinDTO.getDocente().setCodDocente(userLoginApp.getCodUser());
				
			List<BoletinEntity> boletinesList = boletinRepository.findAll();
			
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

	@Override
	public BoletinDTO crearBoletin(BoletinDTO boletinDTO) {
		try {

			BoletinEntity boletinEntity = BoletinMapper.INSTANCE.toEntity(boletinDTO);

			BoletinEntity boletinGuardado = boletinRepository.save(boletinEntity);

			if (Validador.objetoEsNulo(boletinGuardado)) {
				throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_CREAR);
			}
			return BoletinMapper.INSTANCE.toDTO(boletinGuardado);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			throw new RegistroNoGuardadoException(Mensajes.TXT_CODIGO_ESTUDIANTE_EXISTE);
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
		}
	}
	
	public byte[] generarBoletinPDF(BoletinDTO boletinDTO) {
		 // Cargar la plantilla HTML desde el archivo
		try {
        Template template = freeMarkerConfig.getTemplate("template_boletin_1/boletin_plantilla_2.html");

        // Procesar la plantilla con los datos dinámicos
        Map<String, Object> model = new HashMap<>();
        // Aquí agregas los datos dinámicos que deseas reemplazar en la plantilla
        model.put("nombreEstudiante", boletinDTO.getEstudiante().getNomEstudiante());
        model.put("tipDocumEstudiante", boletinDTO.getEstudiante().getTipoDocumId().getNomTipoDocum());
        model.put("codDocumEstudiante", boletinDTO.getEstudiante().getCodDocum());
        model.put("image", boletinDTO.getInstitucion().getLogo());

        // Procesar la plantilla con los datos
        StringWriter stringWriter = new StringWriter();
		template.process(model, stringWriter);
        String html = stringWriter.toString();

        // Convertir HTML a PDF con Flying Saucer
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(baos);
        
        // Configurar la respuesta HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "boletin_escolar.pdf");

        return baos.toByteArray();
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
