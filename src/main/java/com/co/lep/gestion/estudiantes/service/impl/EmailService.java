package com.co.lep.gestion.estudiantes.service.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.constantes.Mensajes;
import com.co.lep.gestion.estudiantes.entity.ResetPasswordTokenEntity;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.security.entity.User;
import com.co.lep.gestion.estudiantes.utilidades.ConfiguracionAdicional;
import com.devsenior.vault.util.VaultUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import sendinblue.ApiClient;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailSender;
import sibModel.SendSmtpEmailTo;

@Service
public class EmailService {

    @Autowired
    EmailTemplateService emailTemplateService;
    
	@Autowired
	ConfiguracionAdicional configuracionAdicional;

    private final freemarker.template.Configuration freeMarkerConfig;

    @Value("${devsenior.email.remitente}")
    private String EMAIL_REMITENTE;
    
    private String apiKey;
    
    public EmailService(freemarker.template.Configuration freeMarkerConfig) {
        this.freeMarkerConfig = freeMarkerConfig;
        
        VaultUtil vaultUtil = new VaultUtil(Constantes.VAULT_URL, 
				Constantes.ROLE_ID, 
				Constantes.SECRET_ID, 
				Constantes.SECRET_PATH,
				new RestTemplate(),
				new ObjectMapper());

        apiKey = vaultUtil.getSecretValue(Constantes.DEVSENIOR_API_KEY);
    }
    
    public void sendHtmlEmail(User usuarioDTO, String securePassword) throws IOException, TemplateException {
        try {
            Template template = freeMarkerConfig.getTemplate("plantilla_envio_email_usuario/plantilla_usuario_registrado.html");
            
            

            // Procesar la plantilla con los datos dinámicos
            Map<String, Object> model = new HashMap<>();
            model.put("firstName", usuarioDTO.getPrimerNombre());
            model.put("lastName", usuarioDTO.getPrimerApellido());
            model.put("login", usuarioDTO.getUsername());
            model.put("password", securePassword);
            model.put("urlBase", configuracionAdicional.getUrlBaseFrontEnd());

            StringWriter stringWriter = new StringWriter();
            template.process(model, stringWriter);
            String html = stringWriter.toString();

            ApiClient defaultClient = Configuration.getDefaultApiClient();
            ApiKeyAuth apiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
            apiKeyAuth.setApiKey(apiKey);

            TransactionalEmailsApi apiInstance = new TransactionalEmailsApi();
            SendSmtpEmailSender sender = new SendSmtpEmailSender();
            sender.setEmail(EMAIL_REMITENTE);
            sender.setName(Constantes.APPLICATION_NAME + " " +usuarioDTO.getInstitucionId().getNomInstitucion());

            List<SendSmtpEmailTo> toList = new ArrayList<>();
            SendSmtpEmailTo to = new SendSmtpEmailTo();
            to.setEmail(usuarioDTO.getEmail());
            to.setName(usuarioDTO.getPrimerNombre() + " " + usuarioDTO.getPrimerApellido());
            toList.add(to);

            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
            sendSmtpEmail.setSender(sender);
            sendSmtpEmail.setTo(toList);
            sendSmtpEmail.setSubject(Mensajes.TXT_BIENVENIDO_EMAIL);
            sendSmtpEmail.setHtmlContent(html);

            apiInstance.sendTransacEmail(sendSmtpEmail);

        } catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			throw new RegistroNoGuardadoException(e.getMessage());
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
		}
    }
    
    public void sendHtmlEmailPasswordReset(ResetPasswordTokenEntity tokenEntity) throws IOException, TemplateException {
        try {
            Template template = freeMarkerConfig.getTemplate("plantilla_envio_email_usuario/plantilla_usuario_reset_password.html");

            // Procesar la plantilla con los datos dinámicos
            Map<String, Object> model = new HashMap<>();
            model.put("firstName", tokenEntity.getUsuarioId().getPrimerNombre());
            model.put("lastName", tokenEntity.getUsuarioId().getPrimerApellido());
            model.put("urlParametros", tokenEntity.getUrlPasswordReset());

            StringWriter stringWriter = new StringWriter();
            template.process(model, stringWriter);
            String html = stringWriter.toString();

            ApiClient defaultClient = Configuration.getDefaultApiClient();
            ApiKeyAuth apiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
            apiKeyAuth.setApiKey(apiKey);

            TransactionalEmailsApi apiInstance = new TransactionalEmailsApi();
            SendSmtpEmailSender sender = new SendSmtpEmailSender();
            sender.setEmail(EMAIL_REMITENTE);
            sender.setName(Constantes.APPLICATION_NAME);

            List<SendSmtpEmailTo> toList = new ArrayList<>();
            SendSmtpEmailTo to = new SendSmtpEmailTo();
            to.setEmail(tokenEntity.getUsuarioId().getEmail());
            to.setName(tokenEntity.getUsuarioId().getPrimerNombre() + " " + tokenEntity.getUsuarioId().getPrimerApellido());
            toList.add(to);

            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
            sendSmtpEmail.setSender(sender);
            sendSmtpEmail.setTo(toList);
            sendSmtpEmail.setSubject(Mensajes.TXT_PWD_RESET_EMAIL);
            sendSmtpEmail.setHtmlContent(html);

            apiInstance.sendTransacEmail(sendSmtpEmail);
        } catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			throw new RegistroNoGuardadoException(e.getMessage());
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
		}
    }
}