package com.co.lep.gestion.estudiantes.utilidades;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class PasswordGenerator {

    // Define los caracteres permitidos para la contraseña
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateSecurePassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("La longitud de la contraseña debe ser al menos de 8 caracteres.");
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }

        return password.toString();
    }
}

