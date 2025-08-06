package com.co.lep.gestion.estudiantes.tunnel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import jakarta.annotation.PreDestroy;

@Service
public class SshTunnelConfig implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private Session session;    
    private String sshHost;
    private int    sshPort;
    private String sshUser;
    private String sshPassword;
    private String remoteDbHost;
    private int    remoteDbPort;
    private int    localPort;
    
    @Autowired
    OpenSSLDecryptor openSSLDecryptor;

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        
        // Leer la ubicación del archivo desde application.properties
        String sshConfigPath = environment.getProperty("ssh.config.path");
        if (sshConfigPath == null) {
            throw new RuntimeException("No se encontró ssh.config.path en application.properties");
        }

        try {
			loadConfig(sshConfigPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        setupTunnel();
    }

    private void loadConfig(String sshConfigPath) throws Exception {
        Properties prop  = OpenSSLDecryptor.decryptPropertiesFile(sshConfigPath);
        try (FileInputStream input = new FileInputStream(sshConfigPath)) {
            prop.load(input);
            sshHost = prop.getProperty("SSH_HOST");
            sshPort = Integer.parseInt(prop.getProperty("SSH_PORT"));
            sshUser = prop.getProperty("SSH_USER");
            sshPassword = prop.getProperty("SSH_PASSWORD");
            remoteDbHost = prop.getProperty("REMOTE_DB_HOST");
            remoteDbPort = Integer.parseInt(prop.getProperty("REMOTE_DB_PORT"));
            localPort = Integer.parseInt(prop.getProperty("LOCAL_PORT"));

            System.out.println("Configuración SSH cargada correctamente desde: " + sshConfigPath);
        } catch (IOException e) {
            throw new RuntimeException("Error cargando configuración SSH", e);
        }
    }

    private void setupTunnel() {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(sshUser, sshHost, sshPort);
            session.setPassword(sshPassword);

            // Evitar problemas con la verificación de host
            session.setConfig("StrictHostKeyChecking", "no");

            System.out.println("Estableciendo conexión SSH...");
            session.connect();

            // Establecer el túnel: redirigir el puerto LOCAL_PORT a la base de datos remota
            session.setPortForwardingL(localPort, remoteDbHost, remoteDbPort);

            System.out.println("Túnel SSH establecido en localhost:" + localPort + " -> " + remoteDbHost + ":" + remoteDbPort);
        } catch (Exception e) {
            throw new RuntimeException("Error estableciendo túnel SSH", e);
        }
    }

    @PreDestroy
    public void closeTunnel() {
        if (session != null && session.isConnected()) {
            System.out.println("Cerrando túnel SSH...");
            session.disconnect();
        }
    }
}
