package com.co.flexicraftsolutions.gestion.estudiantes.mapper;

import com.co.flexicraftsolutions.gestion.estudiantes.controller.representacion.AuthorizationRequest;
import com.co.flexicraftsolutions.gestion.estudiantes.controller.representacion.UserResponse;
import com.co.flexicraftsolutions.gestion.estudiantes.security.entity.User;

public class UserMapper {

	private UserMapper() {
	}

	public static UserResponse toResponse(User user) {
		return UserResponse.builder().name(user.getUsername()).id(user.getId()).build();
	}

	public static User toDomain(AuthorizationRequest authorizationRequest) {
		return User.builder().username(authorizationRequest.getUsername()).password(authorizationRequest.getPassword())
				.build();
	}
}
