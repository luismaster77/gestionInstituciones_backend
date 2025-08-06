package com.co.lep.gestion.estudiantes.integracionIA.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.constantes.Mensajes;
import com.co.lep.gestion.estudiantes.dto.ConfigPromptDTO;
import com.co.lep.gestion.estudiantes.dto.MateriaDTO;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.integracionIA.entity.ConfigPromptEntity;
import com.co.lep.gestion.estudiantes.integracionIA.mapper.ConfigPromptMapper;
import com.co.lep.gestion.estudiantes.integracionIA.repository.ConfigPromptRepository;
import com.co.lep.gestion.estudiantes.security.entity.User;
import com.co.lep.gestion.estudiantes.utilidades.UserLoginApp;
import com.co.lep.gestion.estudiantes.utilidades.Validador;
import com.devsenior.vault.util.VaultUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class IntregacionAIService {

	private String apiDSKey;

	@Value("${gpt.ds.api.url}")
	private String apiDSUrl;
	
	@Autowired
	private ConfigPromptRepository configPromptRepository;
	
	@Autowired
	UserLoginApp userLoginApp;

	public String getResponseFromDeepSeek(String prompt) {
		RestTemplate restTemplate = new RestTemplate();

		// Crear el cuerpo de la solicitud
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + apiDSKey);
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Cuerpo de la petición
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("inputs", prompt);
		requestBody.put("parameters", Map.of("max_new_tokens", 300));

		HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

		try {
			ResponseEntity<String> response = restTemplate.exchange(apiDSUrl, HttpMethod.POST, requestEntity,
					String.class);
			return extraerObservacion(response.getBody());
		} catch (Exception e) {
			return "Error al procesar la solicitud: " + e.getMessage();
		}
	}

	public String extraerObservacion(String jsonResponse) {
		try {
			String[] parts = jsonResponse.split("Observación:", 2);

			// Si hay una segunda parte, esa es la observación
			if (parts.length > 1) {

				int indice = parts[1].trim().indexOf("Observación:");
				if (indice != -1) {
					return parts[1].trim().substring(indice + "Observación:".length()).trim().replaceAll("[\"}\\]]+$",
							"");
				}
			}

			return "No se encontró la observación en el texto.";

			// return jsonResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error procesando la respuesta";
		}
	}

	public String getResponseFromDeepSeekV2(List<MateriaDTO> materias) {
	    RestTemplate restTemplate = new RestTemplate();
	    
	    VaultUtil vaultUtil = new VaultUtil(Constantes.VAULT_URL, 
				Constantes.ROLE_ID, 
				Constantes.SECRET_ID, 
				Constantes.SECRET_PATH,
				new RestTemplate(),
				new ObjectMapper());

        String apiKey = vaultUtil.getSecretValue(Constantes.GPT_MODEL_KEY);

	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", "Bearer " + apiKey);
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    // Construcción del prompt con las materias y notas
	    //Consultamos la configuración del prompt que se realiza en el front, si no existe, se de
	    //Por defecto prompt standar.
	    
	    ConfigPromptEntity configPromptEntity = configPromptRepository.findByIdUsuario(userLoginApp.getUsuarioInfoDTO().getId());
	    
	    StringBuilder promptBuilder = new StringBuilder();
	    if(Validador.objetoEsNulo(configPromptEntity)) {
	    	promptBuilder.append("Genera una observación concisa sobre el desempeño del estudiante. ");
	    	promptBuilder.append("Analiza todas las materias y notas proporcionadas, sin repetirlas, ");
	    	promptBuilder.append("y proporciona un comentario breve y detallado en un máximo de 500 caracteres.\n\n");
	    	promptBuilder.append("Materias y notas del estudiante:\n");	    	
	    }else {
	    	promptBuilder.append(configPromptEntity.getTxtPrompt());
	    }
	    										;   
	    for (MateriaDTO materia : materias) {
	        promptBuilder.append("- ").append(materia.getNomMateria()).append(": ").append(materia.getNota()).append("\n");
	    }
	    
	    promptBuilder.append("\nObservación:");

	    List<Map<String, String>> messages = new ArrayList<>();
	    Map<String, String> userMessage = new HashMap<>();
	    userMessage.put("role", "user");
	    userMessage.put("content", promptBuilder.toString());
	    messages.add(userMessage);

	    Map<String, Object> requestBody = new HashMap<>();
	    requestBody.put("model", "meta-llama/Llama-3.3-70B-Instruct-Turbo");
	    requestBody.put("messages", messages);
	    requestBody.put("max_tokens", 300);

	    HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

	    try {
	        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
	            apiDSUrl,
	            requestEntity,
	            String.class
	        );
	        return extraerContenido(responseEntity.getBody());
	    } catch (HttpClientErrorException e) {
	        return "Error HTTP: " + e.getStatusCode() + " - " + e.getResponseBodyAsString();
	    } catch (Exception e) {
	        return "Error inesperado: " + e.getMessage();
	    }
	}

	
	public String extraerContenido(String jsonResponse) {
	    try {
	        JSONObject jsonObject = new JSONObject(jsonResponse);
	        JSONArray choices = jsonObject.getJSONArray("choices");

	        if (choices.length() > 0) {
	            JSONObject firstChoice = choices.getJSONObject(0);
	            JSONObject message = firstChoice.getJSONObject("message");
	            return message.getString("content").trim();
	        }

	        return "No se encontró contenido en la respuesta.";
	    } catch (Exception e) {
	        return "Error procesando la respuesta: " + e.getMessage();
	    }
	}

	public ConfigPromptEntity consultarPromptPorUsuario() {
		try {
			ConfigPromptEntity configPromptEntity = configPromptRepository.findByIdUsuario(userLoginApp.getUsuarioInfoDTO().getId());
			
			return configPromptEntity;
			
		} catch (RegistroNoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_CONSULTAR_REGISTRO);
		}
	}

	public ConfigPromptDTO editarPrompt(@Valid ConfigPromptDTO configPromptDTO) {
		try {

			// Verificar si el ID proporcionado existe en la base de datos
			ConfigPromptEntity configPromptEntity = configPromptRepository.findByIdUsuario(userLoginApp.getUsuarioInfoDTO().getId());
			ConfigPromptEntity configPromptResponse = new ConfigPromptEntity();
			
			User user = userLoginApp.getUsuarioInfoDTO();
			
			if(!Validador.objetoEsNulo(configPromptEntity)) {
				ConfigPromptMapper.INSTANCE.updateConfigPromptFromDto(configPromptDTO, configPromptEntity);
				
				configPromptEntity.setUsuarioId(user);
				configPromptEntity.setTxtPrompt(configPromptDTO.getTxtPrompt());
				
				configPromptResponse = configPromptRepository.save(configPromptEntity);
			}else {
				configPromptEntity = ConfigPromptMapper.INSTANCE.toEntity(configPromptDTO);
				configPromptEntity.setUsuarioId(user);
				configPromptResponse = configPromptRepository.save(configPromptEntity);
			}
			
			return ConfigPromptMapper.INSTANCE.toDTO(configPromptResponse);

		} catch (RegistroNoGuardadoException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(Mensajes.TXT_ERROR_EDITAR_REGISTRO);
		}
	}
}
