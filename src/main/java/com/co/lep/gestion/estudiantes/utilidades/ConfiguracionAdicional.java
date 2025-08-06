package com.co.lep.gestion.estudiantes.utilidades;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.constantes.Mensajes;
import com.co.lep.gestion.estudiantes.entity.ListaValorEntity;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.repository.ConfiguracionAdicionalRepository;

@Service
public class ConfiguracionAdicional {
	
	@Value("${spring.profiles.active:default}")
	private String activeProfile;
	
	@Autowired
	private ConfiguracionAdicionalRepository configuracionAdicionalRespository;
	
	public String getUrlBaseFrontEnd() {
		try {
			ListaValorEntity urlBaseFrontEnd = new ListaValorEntity();
			if ("dev".equals(activeProfile)) {
				
				urlBaseFrontEnd.setCodItem(Constantes.URL_BASE_LOCALHOST);
				
	        } else if ("prod".equals(activeProfile)) {
	        	
	        	urlBaseFrontEnd = configuracionAdicionalRespository.findByCodCampo(Constantes.COD_CAMPO_URL_BASE);
	        }
						
			if (Validador.objetoEsNulo(urlBaseFrontEnd)) {
				throw new RegistroNoEncontradoException(Mensajes.TXT_ERROR_LISTAS_VACIAS);
			}
			
			return urlBaseFrontEnd.getCodItem();
		} catch (RegistroNoEncontradoException e) {
	        throw e;
	    } catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}
	
	//Aqui implementar la configuracion de notas
	
	
}
