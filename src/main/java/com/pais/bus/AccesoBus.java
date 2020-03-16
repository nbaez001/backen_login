/*
 * 
 */
package com.pais.bus;

import java.util.List;

import com.pais.bean.AuthUsuario;
import com.pais.bean.ColeccionMenu;
import com.pais.bean.ContadorMenu;

public interface AccesoBus {

	public AuthUsuario findByUsername(String username);
	
	public List<ColeccionMenu> menu(int idUsuario);

	public List<ContadorMenu> contadoresMenu(int idUsuario, String codigo);

}
