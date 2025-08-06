package com.co.flexicraftsolutions.gestion.estudiantes.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  private String accessToken;
  private boolean setCambioPassword;
}