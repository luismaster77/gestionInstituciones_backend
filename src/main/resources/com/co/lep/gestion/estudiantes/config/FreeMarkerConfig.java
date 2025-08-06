package com.co.lep.gestion.estudiantes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import freemarker.cache.ClassTemplateLoader;

@Configuration
public class FreeMarkerConfig {

    @Bean
    freemarker.template.Configuration freeMarkerConfiguration() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_30);
        configuration.setTemplateLoader(new ClassTemplateLoader(getClass(), "/templates"));
        return configuration;
    }
}

