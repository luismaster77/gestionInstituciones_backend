package com.co.flexicraftsolutions.gestion.estudiantes.security.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "accesotoken")
public class Token implements Serializable{

  private static final long serialVersionUID = -2915381945776441141L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name="token")
  private String token;
  
  @Column(name="expiradatetoken")
  private Date expiradatetoken;
  
  @Column(name = "fec_acceso")
  private Date fecAcceso;
  
  @Column(name = "usuario")
  private String usuario;
}
