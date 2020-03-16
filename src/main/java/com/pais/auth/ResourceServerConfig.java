/*
 * 
 */
package com.pais.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//Agregando reglas por el lado de oauth
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Value("${jwt.originUrl}")
    private String url;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		/*
		.antMatchers(HttpMethod.GET, 
				"/api/status/**", "/actuator/**")
		.permitAll()
		*/
		.antMatchers(HttpMethod.GET, 
				"/actuator/**")
		.permitAll()
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
		
		
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/uti/gest-serv/actualizar-estado")
		.permitAll()
		.antMatchers(HttpMethod.GET, "/pruebas/view/**", "/reportes/exportar-prefactibilidad", "/reportes/exportar-plataformas", "/reportes/download/**", "/actuator/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
		
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://vulcano.pais.gob.pe", "http://app.pais.gob.pe", "http://backend.pais.gob.pe"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "idUsuario", "urlBase"));
		config.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	
}