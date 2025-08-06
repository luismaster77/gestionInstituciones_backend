package com.co.lep.gestion.estudiantes.security.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.co.lep.gestion.estudiantes.entity.EstadoEntity;
import com.co.lep.gestion.estudiantes.entity.InstitucionEntity;
import com.co.lep.gestion.estudiantes.entity.TipoDocumentoEntity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class User implements UserDetails {

  private static final long serialVersionUID = 7410124076665756280L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @ManyToOne
  @JoinColumn(name = "tipo_docum_id")
  private TipoDocumentoEntity tipoDocumId;
	
  @Column(name = "cod_docum", unique = true)
  private String codDocum;
  
  @Column(name="primer_nombre")
  private String primerNombre;
  
  @Column(name="primer_apellido")
  private String primerApellido;
  
  @Column(name="celular")
  private String celular;
  
  @Column(name="email")
  private String email;
  
  @Column(name="username",unique = true)
  private String username;
  
  @Column(name="password")
  private String password;
  
  @ManyToOne
  @JoinColumn(name = "estado_id")
  private EstadoEntity estadoId;

  @ManyToOne
  @JoinColumn(name = "institucion_id", nullable = true)
  private InstitucionEntity institucionId;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role roleId;
  
  @Column(name="cambio_password")
  private Boolean cambioPassword;
  
  @Column(name="fec_creacion")
  private Date fecCreacion;

  

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
      List<SimpleGrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority(roleId.getName()));
      return authorities;
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

  public User(Long id, String primerNombre, String primerApellido, String email, String username, String password,
		EstadoEntity estadoId, InstitucionEntity institucionId, Role roleId, Boolean cambioPassword, Date fecCreacion) {
	super();
	this.id = id;
	this.primerNombre = primerNombre;
	this.primerApellido = primerApellido;
	this.email = email;
	this.username = username;
	this.password = password;
	this.estadoId = estadoId;
	this.institucionId = institucionId;
	this.roleId = roleId;
	this.cambioPassword = cambioPassword;
	this.fecCreacion = fecCreacion;
  }
}
