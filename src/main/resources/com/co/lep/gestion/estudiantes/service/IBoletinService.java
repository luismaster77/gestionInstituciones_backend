package com.co.flexicraftsolutions.gestion.estudiantes.service;

import java.util.List;

import com.co.flexicraftsolutions.gestion.estudiantes.dto.BoletinDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.BoletinEntity;

public interface IBoletinService {

	List<BoletinEntity> consultarBoletines(BoletinDTO boletinDTO);

	BoletinDTO crearBoletin(BoletinDTO boletinDTO);

	byte[] generarBoletinPDF(BoletinDTO boletinDTO);

}
