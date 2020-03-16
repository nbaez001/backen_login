/*
 * 
 */
package com.pais.dao;

import java.util.List;

import com.pais.bean.AuthUsuario;
import com.pais.bean.ContadorMenu;
import com.pais.bean.Menu;

public interface AccesoDao {

	public AuthUsuario autenticacionUsuario(String username);
	
	public List<Menu> menu(int idUsuario);
	
	public List<ContadorMenu> contadoresMenu(int idUsuario, String codigo);

}
