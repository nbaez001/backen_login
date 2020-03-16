package com.pais.bean;

public class UsuarioPerfilModuloBean {
	private Long idAplicacion;
	private String nomAplicacion;
	private Long idModulo;
	private String nomModulo;
	private Long idUnidad;
	private String codUnidad;
	private String nomUnidad;
	private Long idPerfil;
	private String nomPerfil;
	private String cidCodigoPerfil;
	private Long idPerfilModulo;
	private Long idUsuarioPerfilModulo;
	//private Long idUsuario;
	private short flgActivoUsuPerMod;
	private Long idUterritorial;
	private String nomUTerritorial;
	private Long idPlataforma;
	private String nomPlataforma;
	
	private Long idArea;
	private String codArea;
	private String nomArea;
	private Long idEntidad;
	private String nomEntidad;
	private Long idSector;
	private String nomCortoSector;
	private String nomLargoSector;
	private Long idTipoGobierno;
	private String codTipoGobierno;
	private String nomTipoGobierno;

	/*private Long idUterritorialPlat;
	private String nomUTerritorialPlat;*/
	
	
	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	public String getCodArea() {
		return codArea;
	}

	public void setCodArea(String codArea) {
		this.codArea = codArea;
	}

	public String getNomArea() {
		return nomArea;
	}

	public void setNomArea(String nomArea) {
		this.nomArea = nomArea;
	}

	public Long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(Long idEntidad) {
		this.idEntidad = idEntidad;
	}

	public String getNomEntidad() {
		return nomEntidad;
	}

	public void setNomEntidad(String nomEntidad) {
		this.nomEntidad = nomEntidad;
	}

	public Long getIdSector() {
		return idSector;
	}

	public void setIdSector(Long idSector) {
		this.idSector = idSector;
	}

	public String getNomCortoSector() {
		return nomCortoSector;
	}

	public void setNomCortoSector(String nomCortoSector) {
		this.nomCortoSector = nomCortoSector;
	}

	public String getNomLargoSector() {
		return nomLargoSector;
	}

	public void setNomLargoSector(String nomLargoSector) {
		this.nomLargoSector = nomLargoSector;
	}

	public Long getIdTipoGobierno() {
		return idTipoGobierno;
	}

	public void setIdTipoGobierno(Long idTipoGobierno) {
		this.idTipoGobierno = idTipoGobierno;
	}

	public String getCodTipoGobierno() {
		return codTipoGobierno;
	}

	public void setCodTipoGobierno(String codTipoGobierno) {
		this.codTipoGobierno = codTipoGobierno;
	}

	public String getNomTipoGobierno() {
		return nomTipoGobierno;
	}

	public void setNomTipoGobierno(String nomTipoGobierno) {
		this.nomTipoGobierno = nomTipoGobierno;
	}

	public String getCodUnidad() {
		return codUnidad;
	}

	public void setCodUnidad(String codUnidad) {
		this.codUnidad = codUnidad;
	}

	public String getNomUnidad() {
		return nomUnidad;
	}

	public void setNomUnidad(String nomUnidad) {
		this.nomUnidad = nomUnidad;
	}
/*
	public Long getIdUterritorialPlat() {
		return idUterritorialPlat;
	}

	public void setIdUterritorialPlat(Long idUterritorialPlat) {
		this.idUterritorialPlat = idUterritorialPlat;
	}

	public String getNomUTerritorialPlat() {
		return nomUTerritorialPlat;
	}

	public void setNomUTerritorialPlat(String nomUTerritorialPlat) {
		this.nomUTerritorialPlat = nomUTerritorialPlat;
	}
*/
	public UsuarioPerfilModuloBean() {
		
	}

	public UsuarioPerfilModuloBean(Long idAplicacion, String nomAplicacion, Long idModulo, String nomModulo,
			Long idUnidad, Long idPerfil, String nomPerfil, String cidCodigoPerfil, Long idPerfilModulo,
			Long idUsuarioPerfilModulo //, Long idUsuario
			, short flgActivoUsuPerMod) {
		this.idAplicacion = idAplicacion;
		this.nomAplicacion = nomAplicacion;
		this.idModulo = idModulo;
		this.nomModulo = nomModulo;
		this.idUnidad = idUnidad;
		this.idPerfil = idPerfil;
		this.nomPerfil = nomPerfil;
		this.cidCodigoPerfil = cidCodigoPerfil;
		this.idPerfilModulo = idPerfilModulo;
		this.idUsuarioPerfilModulo = idUsuarioPerfilModulo;
		//this.idUsuario = idUsuario;
		this.flgActivoUsuPerMod = flgActivoUsuPerMod;
	}

	public Long getIdUterritorial() {
		return idUterritorial;
	}

	public void setIdUterritorial(Long idUterritorial) {
		this.idUterritorial = idUterritorial;
	}

	public String getNomUTerritorial() {
		return nomUTerritorial;
	}

	public void setNomUTerritorial(String nomUTerritorial) {
		this.nomUTerritorial = nomUTerritorial;
	}

	public Long getIdPlataforma() {
		return idPlataforma;
	}

	public void setIdPlataforma(Long idPlataforma) {
		this.idPlataforma = idPlataforma;
	}

	public String getNomPlataforma() {
		return nomPlataforma;
	}

	public void setNomPlataforma(String nomPlataforma) {
		this.nomPlataforma = nomPlataforma;
	}

	public Long getIdAplicacion() {
		return idAplicacion;
	}

	public void setIdAplicacion(Long idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	public String getNomAplicacion() {
		return nomAplicacion;
	}

	public void setNomAplicacion(String nomAplicacion) {
		this.nomAplicacion = nomAplicacion;
	}

	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}

	public String getNomModulo() {
		return nomModulo;
	}

	public void setNomModulo(String nomModulo) {
		this.nomModulo = nomModulo;
	}

	public Long getIdUnidad() {
		return idUnidad;
	}

	public void setIdUnidad(Long idUnidad) {
		this.idUnidad = idUnidad;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNomPerfil() {
		return nomPerfil;
	}

	public void setNomPerfil(String nomPerfil) {
		this.nomPerfil = nomPerfil;
	}

	public String getCidCodigoPerfil() {
		return cidCodigoPerfil;
	}

	public void setCidCodigoPerfil(String cidCodigoPerfil) {
		this.cidCodigoPerfil = cidCodigoPerfil;
	}

	public Long getIdPerfilModulo() {
		return idPerfilModulo;
	}

	public void setIdPerfilModulo(Long idPerfilModulo) {
		this.idPerfilModulo = idPerfilModulo;
	}

	public Long getIdUsuarioPerfilModulo() {
		return idUsuarioPerfilModulo;
	}

	public void setIdUsuarioPerfilModulo(Long idUsuarioPerfilModulo) {
		this.idUsuarioPerfilModulo = idUsuarioPerfilModulo;
	}
/*
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
*/
	public int getFlgActivoUsuPerMod() {
		return flgActivoUsuPerMod;
	}

	public void setFlgActivoUsuPerMod(short flgActivoUsuPerMod) {
		this.flgActivoUsuPerMod = flgActivoUsuPerMod;
	}
	
	
	

}
