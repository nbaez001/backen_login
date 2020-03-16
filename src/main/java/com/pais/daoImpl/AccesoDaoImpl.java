package com.pais.daoImpl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.pais.bean.AuthUsuario;
import com.pais.bean.ContadorMenu;
import com.pais.bean.Menu;
import com.pais.bean.UsuarioPerfilModuloBean;
import com.pais.dao.AccesoDao;
import com.pais.util.Constantes;
import com.pais.util.LoggerCustom;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Repository
public class AccesoDaoImpl implements AccesoDao {
	
	private  final Log log = LogFactory.getLog(getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;

	@SuppressWarnings("unchecked")
	@Override
	public AuthUsuario autenticacionUsuario(String username) {
		log.info("[DAO autenticacionUsuario] INICIO  ");	
		List<UsuarioPerfilModuloBean> usuarioPerfilModulos = new ArrayList<>();
		AuthUsuario usuario = null;

		try {
			jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.withSchemaName("SEGURIDAD").withProcedureName("autenticacionUsuario")
					.declareParameters(new SqlParameter("USERNAME", Types.VARCHAR));

			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("USERNAME", username);

			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);

			if (rs.size() > 0) {
				for (Map<String, Object> map : rs) {

					usuario = new AuthUsuario();

					usuario.setIdCodigo((long) map.get("ID_CODIGO"));
					usuario.setCidUsuario((String) map.get("CID_USUARIO").toString().trim());
					usuario.setCidClave((String) map.get("CID_CLAVE").toString().trim());
					usuario.setCidNombre((String) map.get("CID_NOMBRE").toString().trim());
					usuario.setPuesto((String) map.get("PUESTO").toString().trim());
					usuario.setIdPuesto((long) map.get("ID_PUESTO"));
				}
			}
			
			if(usuario.getIdCodigo() > 0) {
				try {
					SimpleJdbcCall jdbcCall2 = new SimpleJdbcCall(jdbcTemplate.getDataSource());
					jdbcCall2.withSchemaName("SEGURIDAD")
					.withProcedureName("listaModuloPerfilesPorUsuario")
					.declareParameters(
							new SqlParameter("ID_USUARIO", 		Types.BIGINT),
							new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
							new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
						);
			
					MapSqlParameterSource parametros2 = new MapSqlParameterSource();
					parametros2.addValue("ID_USUARIO",usuario.getIdCodigo());
					
					Map<String, Object> results2 = jdbcCall2.execute(parametros2);
					
					List<Map<String, Object>> rs2 = (List<Map<String, Object>>) results2.get(Constantes.RETURN_RESULT_SET_PREFIX);
					
//					codigoRpta=Integer.parseInt(results2.get("COD_RESULTADO").toString());
//					mensajeRpta=results2.get("MSG_RESULTADO").toString();
//					
//					if (results2.isEmpty()||results2.size()<1) {
//						log.info("Sin registros, verificar el procedimiento listaModuloPerfilesPorUsuario>>");
//						return  new ApiOutResponse();
//					}
//					
//					if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
//						log.error("[listarUnidadTerritorial] Ocurrio un error en la operacion del Procedimiento almacenado listaModuloPerfilesPorUsuario : "+mensajeRpta);
//						outResponse.setCodResultado(codigoRpta);
//						outResponse.setTotal(0);
//						outResponse.setResponse("Error en la BD");
//						outResponse.setMsgResultado(mensajeRpta);
//						return outResponse;
//					}
//					  
					if(rs2!=null) {
						if (rs2.size() > 0) {
							for (Map<String, Object> mapeo : rs2) {
								UsuarioPerfilModuloBean item = new UsuarioPerfilModuloBean();
								
								item.setIdAplicacion(mapeo.get("ID_APLICACION")!=null ? (long)mapeo.get("ID_APLICACION"):0);
								item.setNomAplicacion(mapeo.get("NOM_APLICACION")!=null ? mapeo.get("NOM_APLICACION").toString() : "");
								item.setIdModulo(mapeo.get("ID_MODULO")!=null ? (long)mapeo.get("ID_MODULO"):0);
								item.setNomModulo(mapeo.get("NOM_MODULO")!=null ? mapeo.get("NOM_MODULO").toString() : "");
								item.setIdUnidad(mapeo.get("ID_UNIDAD")!=null ? (long)mapeo.get("ID_UNIDAD"):0);
								item.setCodUnidad(mapeo.get("COD_UNIDAD")!=null ? mapeo.get("COD_UNIDAD").toString() : "");
								item.setNomUnidad(mapeo.get("NOM_UNIDAD")!=null ? mapeo.get("NOM_UNIDAD").toString() : "");
								item.setIdPerfil(mapeo.get("ID_PERFIL")!=null ? (long)mapeo.get("ID_PERFIL"):0);
								item.setNomPerfil(mapeo.get("NOM_PERFIL")!=null ? mapeo.get("NOM_PERFIL").toString() : "");
								item.setCidCodigoPerfil(mapeo.get("COD_PERFIL")!=null ? mapeo.get("COD_PERFIL").toString() : "");
								item.setIdPerfilModulo(mapeo.get("ID_PERFIL_MODULO")!=null ? (long)mapeo.get("ID_PERFIL_MODULO"):0);
								item.setIdUsuarioPerfilModulo(mapeo.get("ID_USU_PERF_MOD")!=null ? (long)mapeo.get("ID_USU_PERF_MOD"):0);
								//item.setIdUsuario(mapeo.get("ID_USUARIO")!=null ? (long)mapeo.get("ID_USUARIO"):0);
								item.setFlgActivoUsuPerMod(mapeo.get("FLG_ACTIVO_USU_PERF_MOD")!=null ? (short)mapeo.get("FLG_ACTIVO_USU_PERF_MOD"):0);

								if (mapeo.get("ID_UTERRITORIALP")!=null) {
									item.setIdUterritorial(mapeo.get("ID_UTERRITORIALP")!=null ? (long)mapeo.get("ID_UTERRITORIALP"):0);
									item.setNomUTerritorial(mapeo.get("NOMBRE_UTP")!=null ? mapeo.get("NOMBRE_UTP").toString() : "");									
								}else {
									item.setIdUterritorial(mapeo.get("ID_UTERRITORIAL")!=null ? (long)mapeo.get("ID_UTERRITORIAL"):0);
									item.setNomUTerritorial(mapeo.get("NOMBRE_UT")!=null ? mapeo.get("NOMBRE_UT").toString() : "");
								}
								item.setIdPlataforma(mapeo.get("ID_PLAT")!=null ? (long)mapeo.get("ID_PLAT"):0);
								item.setNomPlataforma(mapeo.get("NOMBRE_PLAT")!=null ? mapeo.get("NOMBRE_PLAT").toString() : "");
								
								item.setIdArea(mapeo.get("ID_AREA")!=null ? (long)mapeo.get("ID_AREA"):0);
								item.setCodArea(mapeo.get("COD_AREA")!=null ? mapeo.get("COD_AREA").toString() : "");
								item.setNomArea(mapeo.get("NOM_AREA")!=null ? mapeo.get("NOM_AREA").toString() : "");
								item.setIdEntidad(mapeo.get("ID_ENTIDAD")!=null ? (long)mapeo.get("ID_ENTIDAD"):0);
								item.setNomEntidad(mapeo.get("NOM_ENTIDAD")!=null ? mapeo.get("NOM_ENTIDAD").toString() : "");
								item.setIdSector(mapeo.get("ID_SECTOR")!=null ? (long)mapeo.get("ID_SECTOR"):0);
								item.setNomCortoSector(mapeo.get("NOM_SECTOR_CORTO")!=null ? mapeo.get("NOM_SECTOR_CORTO").toString() : "");
								item.setNomLargoSector(mapeo.get("NOM_SECTOR_LARGO")!=null ? mapeo.get("NOM_SECTOR_LARGO").toString() : "");
								item.setIdTipoGobierno(mapeo.get("ID_TIPO_GOBIERNO")!=null ? (long)mapeo.get("ID_TIPO_GOBIERNO"):0);
								item.setCodTipoGobierno(mapeo.get("COD_TIPO_GOBIERNO")!=null ? mapeo.get("COD_TIPO_GOBIERNO").toString() : "");
								item.setNomTipoGobierno(mapeo.get("NOM_TIPO_GOBIERNO")!=null ? mapeo.get("NOM_TIPO_GOBIERNO").toString() : "");
								
	

								
								usuarioPerfilModulos.add(item);
							}
							usuario.setUsuarioPerfilModulos(usuarioPerfilModulos);
//							outResponse.setResponse(unidadTs);
//							outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
//							outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
						}else {
//							outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
//							outResponse.setTotal(0);
//							outResponse.setResponse("");
//							outResponse.setMsgResultado("¡No existen registros!");
							log.info("[DAO autenticacionUsuario] >>>> Consulta listaModuloPerfilesPorUsuario retorna ¡No existen registros!");
						}
					}else {
						log.info("[DAO autenticacionUsuario] >>>> Consulta listaModuloPerfilesPorUsuario retorna null");
					}
				} catch (Exception e) {
					log.error("DAO autenticacionUsuario>>>>"+this.getClass().getName(), e);
//					outResponse.setCodResultado(500);
//					outResponse.setMsgResultado( "Error en la Base de datos!.");
				}
			
			}
		} catch (Exception e) {
			LoggerCustom.errorApp("", "", e);
		}

		return usuario;
	}

	@Override
	public List<Menu> menu(int idUsuario) {

		List<Menu> listaMenu = new ArrayList<>();
		Menu menu = null;

		try {
			jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.withSchemaName("SEGURIDAD").withProcedureName("coleccionMenu")
					.declareParameters(new SqlParameter("ID_USUARIO", Types.INTEGER));

			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("ID_USUARIO", idUsuario);

			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);

			if (rs.size() > 0) {
				for (Map<String, Object> map : rs) {

					menu = new Menu();

					menu.setIdCodigo((int) map.get("ID_CODIGO"));
					menu.setFidMenu((int) map.get("FID_MENU"));
					menu.setCidCodigo((String) map.get("CID_CODIGO").toString().trim());
					menu.setCidNombre((String) map.get("CID_NOMBRE").toString().trim());
					menu.setCidUrl((String) map.get("CID_URL").toString().trim());
					menu.setCidIcono((String) map.get("CID_ICONO").toString().trim());

					listaMenu.add(menu);

				}
			}

		} catch (Exception e) {
			LoggerCustom.errorApp("", "", e);
		}

		return listaMenu;
	}

	@Override
	public List<ContadorMenu> contadoresMenu(int idUsuario, String codigo) {

		List<ContadorMenu> contadores = new ArrayList<>();

		try {
			jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.withSchemaName("SEGURIDAD").withProcedureName("menuContadores")
					.declareParameters(new SqlParameter("ID_USUARIO", Types.INTEGER))
					.declareParameters(new SqlParameter("CID_CODIGO", Types.VARCHAR));

			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("ID_USUARIO", idUsuario);
			parametros.addValue("CID_CODIGO", codigo);

			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);

			if (rs.size() > 0) {
				for (Map<String, Object> map : rs) {

					ContadorMenu contador = new ContadorMenu();
					contador.setCodigo(map.get("codigo").toString());
					contador.setCantidad((int) map.get("cantidad"));
					contador.setColor(map.get("color").toString());
					contador.setMensaje(map.get("mensaje").toString());

					contadores.add(contador);

				}
			}

		} catch (Exception e) {
			LoggerCustom.errorApp("", "", e);
		}

		return contadores;
	}

}
