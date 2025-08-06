package com.co.lep.gestion.estudiantes.tunnel;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.devsenior.vault.util.VaultUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OpenSSLDecryptor {

	public static byte[] decryptOpenSSL(byte[] encrypted, String password) throws Exception {
        // Validar encabezado OpenSSL
        byte[] saltHeader = Arrays.copyOfRange(encrypted, 0, 8);
        if (!new String(saltHeader, StandardCharsets.US_ASCII).equals("Salted__")) {
            throw new IllegalArgumentException("El archivo no tiene encabezado 'Salted__'.");
        }

        byte[] salt = Arrays.copyOfRange(encrypted, 8, 16);
        byte[] ciphertext = Arrays.copyOfRange(encrypted, 16, encrypted.length);

        // Derivar clave y IV con PBKDF2
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 10000, 384); // 32 bytes key + 16 bytes IV
        byte[] keyAndIv = factory.generateSecret(spec).getEncoded();

        byte[] aesKey = Arrays.copyOfRange(keyAndIv, 0, 32);     // 256 bits
        byte[] iv = Arrays.copyOfRange(keyAndIv, 32, 48);        // 128 bits

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(aesKey, "AES"), new IvParameterSpec(iv));
        return cipher.doFinal(ciphertext);
    }

	public static Properties decryptPropertiesFile(String path) {
        try {
        	
        	VaultUtil vaultUtil = new VaultUtil(Constantes.VAULT_URL, 
        										Constantes.ROLE_ID, 
        										Constantes.SECRET_ID, 
        										Constantes.SECRET_PATH,
        										new RestTemplate(),
        										new ObjectMapper());
        	
        	String clave = vaultUtil.getSecretValue(Constantes.ENCRYPTOR_KEY);
            byte[] encrypted = Files.readAllBytes(Paths.get(path));
            byte[] decrypted = decryptOpenSSL(encrypted, clave);

            Properties props = new Properties();
            try (InputStream in = new ByteArrayInputStream(decrypted)) {
                props.load(in);
            }
            return props;
        } catch (Exception e) {
            throw new RuntimeException("Error al desencriptar el archivo: " + e.getMessage(), e);
        } 
    }
}
