package com.co.flexicraftsolutions.gestion.estudiantes.security.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.co.flexicraftsolutions.gestion.estudiantes.entity.EstadoEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class User implements UserDetails {

  private static final long serialVersionUID = 7410124076665756280L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name="primer_nombre")
  private String primerNombre;
  
  @Column(name="primer_apellido")
  private String primerApellido;
  
  @Column(name="username",unique = true)
  private String username;
  
  @Column(name="password")
  private String password;
  
  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role roleId;
  
  @ManyToOne
  @JoinColumn(name = "estado_id")
  private EstadoEntity estadoId;
  
  @Column(name="cambio_password")
  private Boolean cambioPassword;
  
  @Column(name="fec_creacion")
  private Date fecCreacion;

  public User(String primerNombre, String primerApellido, String username, String password, Role roleId, EstadoEntity estadoId, Boolean cambioPassword, Date fecCreacion) {
      this.primerNombre = primerNombre;
      this.primerApellido = primerApellido;
      this.username = username;
      this.password = password;
      this.roleId = roleId;
      this.estadoId = estadoId;
      this.cambioPassword = cambioPassword;
      this.fecCreacion = fecCreacion;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
      List<SimpleGrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority(roleId.getName()));
      return authorities;
  }


  @Override
  public String getUsername() {
    return this.username;
  }
  
  public boolean isSetCambioPassword() {
      return cambioPassword;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
