package com.co.lep.gestion.estudiantes.service;

import java.util.List;

import com.co.lep.gestion.estudiantes.dto.BoletinDTO;
import com.co.lep.gestion.estudiantes.entity.BoletinEntity;

public interface IBoletinService {

	List<BoletinEntity> consultarBoletines(BoletinDTO boletinDTO);

	BoletinDTO crearBoletin(BoletinDTO boletinDTO);

	byte[] generarBoletinPDF(BoletinDTO boletinDTO);

}
