package com.co.lep.gestion.estudiantes.service;

import java.util.List;
import javax.validation.Valid;

import com.co.lep.gestion.estudiantes.dto.BoletinConsultaDTO;
import com.co.lep.gestion.estudiantes.dto.BoletinDTO;
import com.co.lep.gestion.estudiantes.entity.BoletinDetalleEntity;
import com.co.lep.gestion.estudiantes.entity.BoletinEntity;

public interface IBoletinService {

	List<BoletinEntity> consultarBoletines(BoletinConsultaDTO boletinDTO);

	BoletinDTO crearBoletin(BoletinDTO boletinDTO);

	byte[] generarBoletinPDF(Long boletinId);

	List<BoletinDetalleEntity> consultarDetalleBoletines(BoletinDTO boletinDTO);

	BoletinEntity consultarBoletinById(BoletinDTO boletinDTO);

	List<BoletinDetalleEntity> consultarDetalleBoletinById(Long id);

	BoletinDTO editarBoletin(@Valid BoletinDTO boletinDTO);

	BoletinDTO eliminarBoletin(@Valid Long boletinId);

}
