package com.co.lep.gestion.estudiantes.userApp;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.co.lep.gestion.estudiantes.repository.UserRepository;
import com.co.lep.gestion.estudiantes.security.entity.Role;
import com.co.lep.gestion.estudiantes.security.entity.User;


@Component
public class DataLoader {

    @Autowired
	private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

	public void loadTestData() {
        // Crea una aplicación de prueba y almacénala en la base de datos
		User user = new User();
		Role role = new Role();
		role.setName("ADMIN");
		user.setPrimerNombre("Luis Eduardo");
		user.setPrimerApellido("Paredes");
		user.setUsername("prluise");
		user.setPassword(passwordEncoder.encode("Lu13edu4rd0"));
		user.setRoleId(role);
    	userRepository.save(user);
    }
	
	public void encriptarPassword(String[] args) {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword("Lu13edu4rd0@"); // Cambia por tu clave
        String encryptedPassword = encryptor.encrypt("Lu13edu4rd0");
        System.out.println("Contraseña encriptada: ENC(" + encryptedPassword + ")");
    }
}
