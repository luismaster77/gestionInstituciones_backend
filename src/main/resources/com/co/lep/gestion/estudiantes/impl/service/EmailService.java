package com.co.flexicraftsolutions.gestion.estudiantes.impl.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.flexicraftsolutions.gestion.estudiantes.entity.DocenteEntity;

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

    private final freemarker.template.Configuration freeMarkerConfig;
    private static final String APPLICATION_NAME = "devsenior";
    private static final String EMAIL_REMITENTE = "devseniortech@gmail.com";
    private final String apiKey = "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";

    public EmailService(freemarker.template.Configuration freeMarkerConfig) {
        this.freeMarkerConfig = freeMarkerConfig;
    }
    
    public void sendHtmlEmail(DocenteEntity docenteEntity, String securePassword) throws IOException, TemplateException {
        try {
            Template template = freeMarkerConfig.getTemplate("plantilla_envio_email_usuario/plantilla_usuario_registrado.html");

            // Procesar la plantilla con los datos dinámicos
            Map<String, Object> model = new HashMap<>();
            model.put("firstName", docenteEntity.getNomDocente());
            model.put("lastName", docenteEntity.getApe1Docente() + " " + (docenteEntity.getApe2Docente() != null ? docenteEntity.getApe2Docente() : ""));
            model.put("login", docenteEntity.getUsuarioId().getUsername());
            model.put("password", securePassword);

            StringWriter stringWriter = new StringWriter();
            template.process(model, stringWriter);
            String html = stringWriter.toString();

            ApiClient defaultClient = Configuration.getDefaultApiClient();
            ApiKeyAuth apiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
            apiKeyAuth.setApiKey(apiKey);

            TransactionalEmailsApi apiInstance = new TransactionalEmailsApi();
            SendSmtpEmailSender sender = new SendSmtpEmailSender();
            sender.setEmail(EMAIL_REMITENTE);
            sender.setName(APPLICATION_NAME);

            List<SendSmtpEmailTo> toList = new ArrayList<>();
            SendSmtpEmailTo to = new SendSmtpEmailTo();
            to.setEmail(docenteEntity.getEmail());
            to.setName(docenteEntity.getNomDocente() + " " + docenteEntity.getApe1Docente() + " " + docenteEntity.getApe2Docente());
            toList.add(to);

            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
            sendSmtpEmail.setSender(sender);
            sendSmtpEmail.setTo(toList);
            sendSmtpEmail.setSubject("Bienvenido a nuestra aplicación");
            sendSmtpEmail.setHtmlContent(html);

            apiInstance.sendTransacEmail(sendSmtpEmail);

            System.out.println("Correo enviado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

