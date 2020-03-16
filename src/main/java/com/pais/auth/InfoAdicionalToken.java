/*
 * 
 */
package com.pais.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.pais.bean.AuthUsuario;
import com.pais.bus.AccesoBus;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private AccesoBus accesoBus;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		AuthUsuario usu = accesoBus.findByUsername(authentication.getName());
		
		Map<String, Object> info = new HashMap<>();
		
		info.put("id_usuario", 		usu.getIdCodigo());
		info.put("username", 		usu.getCidUsuario());
		info.put("nombres", 		usu.getCidNombre());
		info.put("puesto", 			usu.getPuesto());
		info.put("id_puesto",		usu.getIdPuesto());
		info.put("lista_perfil_modulo", 			usu.getUsuarioPerfilModulos());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
