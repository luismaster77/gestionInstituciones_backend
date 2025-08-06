package com.co.lep.gestion.estudiantes.service;

import com.co.lep.gestion.estudiantes.entity.ResetPasswordTokenEntity;

public interface IUsuarioService {

	ResetPasswordTokenEntity generarResetPwdToken(String email);

	void validateResetToken(String token);

	void resetPassword(String token, String newPassword);
}
