package com.co.lep.gestion.estudiantes.security.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.co.lep.gestion.estudiantes.security.jwt.JwtTokenFilter;

import jakarta.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig{
	
	  private final JwtTokenFilter jwtAuthenticationFilter;
	  
	  private final  UserDetailsService customUserDetailsService;

	  public WebSecurityConfig(
		  JwtTokenFilter jwtAuthenticationFilter,
	      UserDetailsService customUserDetailsService) {

	    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	    this.customUserDetailsService = customUserDetailsService;
	  }
	  
	  @Bean
      DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder()); 
        return authProvider;
      }

	  @Bean
	  PasswordEncoder passwordEncoder() {
		  return new BCryptPasswordEncoder();
	  }

	  @SuppressWarnings({ "deprecation" })
	  @Bean
	  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.cors(cors -> cors.configurationSource(request -> {
				CorsConfiguration corsConfiguration = new CorsConfiguration();
				corsConfiguration.setAllowedOrigins(List.of(
						"209.145.57.79"
						,"https://devseniortech.com" 
						,"https://gestionboletines.devseniortech.com"
						,"http://localhost:4200"
						));
				corsConfiguration.setAllowedMethods(List.of(
						"HEAD", 
						"GET",
						"POST", 
						"PUT", 
						"DELETE"));
				corsConfiguration.setAllowCredentials(true);
				corsConfiguration.addExposedHeader("Message");
				corsConfiguration.setAllowedHeaders(List.of(
						"Authorization", 
		                "Cache-control", 
		                "Content-Type",
		                "Accept",
		                "Origin",
		                "X-Requested-With"));
				return corsConfiguration;
			})).csrf(csrf -> csrf.disable()).authorizeRequests((authorizeRequests) -> {
				try {
					authorizeRequests
                            .requestMatchers(
                            		"/api/auth/login", 
                            		"/api/auth/logout", 
                            		"/api/auth/refresh",
                            		"/api/auth/solicitar_reset_pwd",
                            		"/api/auth/valida_token_reset_password",
                            		"/api/auth/reset_password",
                            		"/ws/**",
                                    "/ws",
                                    "/ws/info")
                            .permitAll().anyRequest().authenticated()
                            .and()
                            .sessionManagement(management -> management
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                            .exceptionHandling(handling -> handling
                                    .authenticationEntryPoint(
                                            (request, response, authException)
                                                    -> response.sendError(
                                                            HttpServletResponse.SC_UNAUTHORIZED,
                                                            authException.getLocalizedMessage()
                                                    )
                                    ))
                            .authenticationProvider(authenticationProvider())
                            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			return http.build();
		}
	  
	  @Autowired
	  public void configure(AuthenticationManagerBuilder builder) {
			builder.eraseCredentials(false);
	  }
	  
	  @Bean
	  AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	  }
}
