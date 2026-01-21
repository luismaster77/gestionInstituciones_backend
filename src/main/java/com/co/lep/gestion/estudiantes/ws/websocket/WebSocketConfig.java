package com.co.lep.gestion.estudiantes.ws.websocket;

import java.security.Principal;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.http.server.ServerHttpRequest;

import com.co.lep.gestion.estudiantes.security.jwt.JwtHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Bean
	JwtHandshakeInterceptor jwtHandshakeInterceptor() {
		return new JwtHandshakeInterceptor();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic", "/queue");
		config.setApplicationDestinationPrefixes("/app");
		config.setUserDestinationPrefix("/user");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws").setHandshakeHandler(handshakeHandler()).addInterceptors(jwtHandshakeInterceptor())
				.setAllowedOriginPatterns("*");
	}

	/**
	 * HandshakeHandler capaz de resolver el principal desde los atributos que puso
	 * el interceptor.
	 */
	@Bean
	HandshakeHandler handshakeHandler() {
		return new DefaultHandshakeHandler() {
			@Override
			protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
					Map<String, Object> attributes) {
				// Buscamos las dos claves que usamos en el interceptor
				Object p = attributes.get(Principal.class.getName());
				if (p instanceof Principal) {
					return (Principal) p;
				}
				p = attributes.get("simpUser");
				if (p instanceof Principal) {
					return (Principal) p;
				}
				return super.determineUser(request, wsHandler, attributes);
			}
		};
	}

	/**
	 * Interceptor para el canal inbound: si el accessor no tiene user, lo tomamos
	 * desde sessionAttributes.
	 */
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(new ChannelInterceptor() {
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {
				StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
				if (accessor == null)
					return message;

				if (StompCommand.CONNECT.equals(accessor.getCommand())) {
					Map<String, Object> sessionAttrs = accessor.getSessionAttributes();
					if (sessionAttrs != null) {
						Object o = sessionAttrs.get(Principal.class.getName());
						if (o instanceof Principal) {
							accessor.setUser((Principal) o);
							System.out.println("🔥 Principal asignado desde sessionAttrs (Principal.class): "
									+ ((Principal) o).getName());
						} else {
							o = sessionAttrs.get("simpUser");
							if (o instanceof Principal) {
								accessor.setUser((Principal) o);
								System.out.println("🔥 Principal asignado desde sessionAttrs (simpUser): "
										+ ((Principal) o).getName());
							} else {
								Object uname = sessionAttrs.get("username");
								if (uname instanceof String) {
									// asignar Principal mínimo usando username string si no hay Principal
									// (fallback)
									accessor.setUser(() -> (String) uname);
									System.out.println("⚠️ Asignado Principal fallback con username: " + uname);
								} else {
									System.out.println("⚠️ No se encontró principal en sessionAttrs durante CONNECT");
								}
							}
						}
					} else {
						System.out.println("⚠️ sessionAttributes es null durante CONNECT");
					}
				}

				return message;
			}
		});
	}
}
