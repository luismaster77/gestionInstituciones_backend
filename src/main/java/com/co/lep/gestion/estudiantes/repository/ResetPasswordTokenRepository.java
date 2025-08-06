package com.co.lep.gestion.estudiantes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.lep.gestion.estudiantes.entity.ResetPasswordTokenEntity;

public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordTokenEntity, Long>{

	Optional<ResetPasswordTokenEntity> findByToken(String token);}
