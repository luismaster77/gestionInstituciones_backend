package com.co.lep.gestion.estudiantes.integracionIA.entity;

import java.io.Serializable;

import com.co.lep.gestion.estudiantes.security.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "config_prompt")
@Getter
@Setter
public class ConfigPromptEntity implements Serializable{
	
	private static final long serialVersionUID = 1961091155949390382L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "txt_prompt")
	private String txtPrompt;
	
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private User usuarioId;
}