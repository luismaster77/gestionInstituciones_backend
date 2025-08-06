package com.co.lep.gestion.estudiantes.entity;

import java.util.Date;

import com.co.lep.gestion.estudiantes.security.entity.User;

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
@Table(name = "reset_password")
public class ResetPasswordTokenEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private User usuarioId;
	
	@Column(name="token")
	private String token;
	
	@Column(name="intentos_fallidos")
	private int intentosFallidos;
	
	@Column(name="intentos_permitidos")
	private int intentosPermitidos;
	
	@Column(name="url_password_reset")
	private String urlPasswordReset;
	
	@Column(name="expiracion_token")
	private Date expiraToken;
	
	@Column(name="fec_ultimo_intento")
	private Date fecUltimoIntento;
	
	@Column(name="fec_recuperacion")
	private Date fecRecuperacion;
	
}
