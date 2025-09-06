package com.co.lep.gestion.estudiantes.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.co.lep.gestion.estudiantes.constantes.Mensajes;
import com.co.lep.gestion.estudiantes.entity.ResetPasswordTokenEntity;
import com.co.lep.gestion.estudiantes.exepciones.MaxCountExceededException;
import com.co.lep.gestion.estudiantes.exepciones.PasswordResetException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.exepciones.TokenExpiredException;
import com.co.lep.gestion.estudiantes.exepciones.TokenNotFoundException;
import com.co.lep.gestion.estudiantes.repository.ResetPasswordTokenRepository;
import com.co.lep.gestion.estudiantes.repository.UserRepository;
import com.co.lep.gestion.estudiantes.security.entity.User;
import com.co.lep.gestion.estudiantes.service.IUsuarioService;
import com.co.lep.gestion.estudiantes.utilidades.ConfiguracionAdicional;

@Service
public class UsuarioService implements IUsuarioService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ResetPasswordTokenRepository resetPasswordTokenRepository;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ConfiguracionAdicional configuracionAdicional;
	
	@Override
	public ResetPasswordTokenEntity generarResetPwdToken(String email) {
		try {
			Optional<User> user = userRepository.findByEmail(email);
		    if (user.isPresent()) {
		        String resetToken = UUID.randomUUID().toString();
		        
		        // Almacenar el token en la base de datos
		        Calendar calendar = Calendar.getInstance();
		        calendar.add(Calendar.MINUTE, 15);
		        Date expirationTime = calendar.getTime();  // Esto devuelve un objeto Date
		        
		        //Obtengo la url de la configuracion en DB y Genera url
		        String baseUrl = configuracionAdicional.getUrlBaseFrontEnd();
		        
		        String resetUrl = baseUrl + "/#/reset-contrasenia?token=" + resetToken;

		        ResetPasswordTokenEntity tokenEntity = new ResetPasswordTokenEntity();
		        tokenEntity.setUsuarioId(user.get());
		        tokenEntity.setToken(resetToken);
		        tokenEntity.setExpiraToken(expirationTime);
		        tokenEntity.setIntentosFallidos(0); // Inicializar intentos fallidos
		        tokenEntity.setIntentosPermitidos(5); // Definir número máximo de intentos
		        tokenEntity.setFecRecuperacion(new Date());
		        tokenEntity.setFecUltimoIntento(new Date());
		        tokenEntity.setUrlPasswordReset(resetUrl);

		        resetPasswordTokenRepository.save(tokenEntity);
		        // Enviar el correo electrónico con el enlace
				emailService.sendHtmlEmailPasswordReset(tokenEntity);
				
				return tokenEntity;
		     }else {
		    	 throw new RegistroNoEncontradoException("Email no encontrado");
		     }
		} catch(RegistroNoEncontradoException ex){
			throw ex;
		} catch (RegistroNoGuardadoException e) {
			throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_CREAR);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroNoGuardadoException(Mensajes.TXT_ERROR_CREAR);
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CREAR_REGISTRO);
		}
	}
	
	@Override
	public void validateResetToken(String token) throws TokenExpiredException, TokenNotFoundException {
        // Buscar el token en la base de datos
		ResetPasswordTokenEntity resetToken = resetPasswordTokenRepository.findByToken(token)
                .orElseThrow(() -> new TokenNotFoundException("Token no válido"));

        // Verificar si el token ha expirado
		Date currentDate = new Date();
	    if (resetToken.getExpiraToken().before(currentDate)) {
	        throw new TokenExpiredException("El token ha expirado.");
	    }

        // Opcional: Si deseas, puedes también validar al usuario asociado al token
        User user = resetToken.getUsuarioId();
        if (user == null) {
            throw new TokenNotFoundException("Usuario no asociado a este token");
        }
    }
	
	@Override
	public void resetPassword(String token, String newPassword) {
	    Optional<ResetPasswordTokenEntity> optionalToken = resetPasswordTokenRepository.findByToken(token);

	    if (!optionalToken.isPresent()) {
	        throw new TokenNotFoundException("Token inválido.");
	    }

	    ResetPasswordTokenEntity tokenEntity = optionalToken.get();

	    // Verificar si el token ha expirado
	    Date currentDate = new Date();
	    if (tokenEntity.getExpiraToken().before(currentDate)) {
	        throw new TokenExpiredException("El token ha expirado.");
	    }

	    // Verificar si se ha alcanzado el número máximo de intentos fallidos
	    if (tokenEntity.getIntentosFallidos() >= tokenEntity.getIntentosPermitidos()) {
	        throw new MaxCountExceededException("Se ha alcanzado el número máximo de intentos.");
	    }

	    try {
	        // Lógica para restablecer la contraseña
	    	tokenEntity.getUsuarioId().setPassword(passwordEncoder.encode(newPassword));
	    	userRepository.save(tokenEntity.getUsuarioId());

	        // Eliminar el token después de un restablecimiento exitoso
	        resetPasswordTokenRepository.delete(tokenEntity);
	    } catch (Exception e) {
	        // Incrementar el contador de intentos fallidos si falla
	        tokenEntity.setIntentosFallidos(tokenEntity.getIntentosFallidos() + 1);
	        tokenEntity.setFecUltimoIntento(new Date());  // Registrar la fecha del último intento
	        resetPasswordTokenRepository.save(tokenEntity);

	        throw new PasswordResetException("Error al restablecer la contraseña.");
	    }
	}
}
