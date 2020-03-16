/*
 * 
 */
package com.pais.busImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pais.bean.AuthUsuario;
import com.pais.bean.ColeccionMenu;
import com.pais.bean.ContadorMenu;
import com.pais.bean.Menu;
import com.pais.bus.AccesoBus;
import com.pais.dao.AccesoDao;

@Service
public class AccesoBusImpl implements AccesoBus, UserDetailsService {

	@Autowired
	private AccesoDao accesoDao;
	
	/* Autenticacion de usuario en el token */
	/* No borrar */
	/* Inicio */
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AuthUsuario usuario = accesoDao.autenticacionUsuario(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema!");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return new User(usuario.getCidUsuario(), usuario.getCidClave(), true, true, true, true, authorities);
		
	}
	/* Fin */
	
	@Override
	@Transactional(readOnly=true)
	public AuthUsuario findByUsername(String username) {
		
		AuthUsuario usu = accesoDao.autenticacionUsuario(username);
		
		return usu;
	}

	@Override
	public List<ColeccionMenu> menu(int idUsuario) {
		
		List<Menu> menus = accesoDao.menu(idUsuario);
		
		return this.obtenerHijos(menus, 0);
		
	}
	
	private List<ColeccionMenu> obtenerHijos(List<Menu> menus, Integer idCodigo) {
		List<ColeccionMenu> hijos = new ArrayList<ColeccionMenu>();
		
		for(Menu menu : menus) {
			if (menu.getFidMenu() == idCodigo) {
				ColeccionMenu itemMenu = new ColeccionMenu();
				itemMenu.setCidCodigo(menu.getCidCodigo());
				itemMenu.setCidNombre(menu.getCidNombre());
				itemMenu.setCidUrl(menu.getCidUrl());
				itemMenu.setCidIcono(menu.getCidIcono());
				itemMenu.setHijos(this.obtenerHijos(menus, menu.getIdCodigo()));
				hijos.add(itemMenu);
			}
		}
		
		return hijos;
	}

	@Override
	public List<ContadorMenu> contadoresMenu(int idUsuario, String codigo) {
		
		return accesoDao.contadoresMenu(idUsuario, codigo);
		
	}
	
}
