package com.pais.bean;

import java.util.List;

public class AuthUsuario {
	
	private long idCodigo;
	private String cidUsuario;
	private String cidClave;
	private String cidNombre;
	private String puesto;
	private long idPuesto;
	private List<UsuarioPerfilModuloBean> usuarioPerfilModulos;

	public long getIdPuesto() {
		return idPuesto;
	}
	public void setIdPuesto(long idPuesto) {
		this.idPuesto = idPuesto;
	}
	public List<UsuarioPerfilModuloBean> getUsuarioPerfilModulos() {
		return usuarioPerfilModulos;
	}
	public void setUsuarioPerfilModulos(List<UsuarioPerfilModuloBean> usuarioPerfilModulos) {
		this.usuarioPerfilModulos = usuarioPerfilModulos;
	}
	public long getIdCodigo() {
		return idCodigo;
	}
	public void setIdCodigo(long idCodigo) {
		this.idCodigo = idCodigo;
	}
	public String getCidUsuario() {
		return cidUsuario;
	}
	public void setCidUsuario(String cidUsuario) {
		this.cidUsuario = cidUsuario;
	}
	public String getCidClave() {
		return cidClave;
	}
	public void setCidClave(String cidClave) {
		this.cidClave = cidClave;
	}
	public String getCidNombre() {
		return cidNombre;
	}
	public void setCidNombre(String cidNombre) {
		this.cidNombre = cidNombre;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	
}
