package com.co.flexicraftsolutions.gestion.estudiantes.impl.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class EmailTemplateService {

    public String readTemplate(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return contentBuilder.toString();
    }

    public String replacePlaceholders(String template, Map<String, String> placeholders) {
        String processedTemplate = template;
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            processedTemplate = processedTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return processedTemplate;
    }
}
