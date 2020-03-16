package com.pais.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

public class Util {

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Value("${jwt.originUrl}")
	public static String originUrl;
	
	public static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

	
	
	public static String getIp() {
		String ipRemoto =null;
		try {
			ipRemoto= InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			LoggerCustom.errorApp("", "", e);
		}
		return ipRemoto;
    }
	
	public static void ejecutarWS() {
		
		HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://www.pais.gob.pe:3000/emitting");
        // HttpPost post = new HttpPost(wsUrl + "/emitting");

        // Create some NameValuePair for HttpPost parameters
        List<NameValuePair> arguments = new ArrayList<>(1);
        arguments.add(new BasicNameValuePair("channel", "reload-atenciones"));

        try {
            post.setEntity(new UrlEncodedFormEntity(arguments));
            HttpResponse response = client.execute(post);

            // Print out the response message
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
        	LoggerCustom.errorApp("", "", e);
        }
	}
	
	
	public static boolean  estaVacio(Object valor) {
		if(toStr(valor).trim().equals("")){
			return true;
		}else{
			return false;
		}

	}


	public static boolean  isEqualNoCaseSentivive(Object valor1, Object valor2) {
		if(toStrUpperCase(valor1).equals(toStrUpperCase(valor2))){
			return true;
		}else{
			return false;
		}	
	}

	public static boolean  isEqualCaseSentivive(Object valor1, Object valor2) {
		if(toStrTrim(valor1).equals(toStrTrim(valor2))){
			return true;
		}else{
			return false;
		}	
	}

	public static boolean  isStrNumero1MenorIgualQueStrNumero2(String strNum1, String strNum2) {
		if(isEmpty(strNum1)){
			return false;
		}

		if(isEmpty(strNum2)){
			return false;
		}

		if(toInteger(strNum1)<=toInteger(strNum2)){
			return true;
		}else{
			return false;
		}

	}

	public static int  toInteger(String valor) {
		int numero =  Integer.parseInt(valor);
		return numero;
	}

	public static boolean  isEmpty(Object valor) {
		return estaVacio(valor);
	}

	public static String  toStrTrim(Object valor) {
		if(valor==null){
			return "";
		}

		return valor.toString().trim();

	}

	public static String  toStr(Object valor) {
		if(valor==null){
			return "";
		}

		return valor.toString();

	}

	public static boolean  toBooleanValue(Boolean valor) {
		if(valor==null){
			return false;
		}

		return valor.booleanValue();

	}

	public static String  toStrUpperCase(Object valor) {

		return toStr(valor).toUpperCase();

	}

	public static String  toStrUpperCaseTrim(Object valor) {

		return toStr(valor).toUpperCase().trim();

	}

	public static String  toStrUpperCaseTrimWithoutExtraSpaces(Object valor) {
		String str = toStr(valor).toUpperCase().trim();
		String regex = "\\s{2,}";
		str = str.replaceAll(regex, " "); 

		return str;

	}


	public static String  toStrLowerCase(Object valor) {

		return toStr(valor).toLowerCase();

	}

	public static String  toStrLowerCaseTrim(Object valor) {

		return toStr(valor).toLowerCase().trim();

	}
	
	
	
	public static String getParam(Object campo) {
		String parametro = getString(campo);
		if (isNullOrEmpty(parametro)) {
			return Constantes.CADENA_VACIO;
		}		
		return parametro;
	}
	
	
	public static boolean isNullInteger(Integer campo) {
		if (!isNull(campo) && campo > 0) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isNull(Object campo) {
		if (campo == null) {
			return true;
		} else {
			return false;
		}
	}	

	public static Integer getInteger(String campo) {
		Integer valor = null;
		if (!isNullOrEmpty(campo)) {
			valor =	Integer.parseInt(campo);	
		}
		return valor; 	
	}
	
	public static String getString(Object campo) {
		if (campo != null) {
			if (campo instanceof Integer) {
				return String.valueOf((Integer) campo);
			} else if (campo instanceof Long) {
				return String.valueOf((Long) campo);
			} else if (campo instanceof BigDecimal) {
				return String.valueOf((BigDecimal) campo);
			} else {
				return (String) campo;
			}
		}
		return Constantes.EMPTY; 	
	}
	
	public static boolean isNullOrEmpty(String campo) { 
	    return campo == null || campo.trim().length() == 0 || campo.equals("null") ;
	}
	
	public static int getAnioActual(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static int getMesActual(){
		return Calendar.getInstance().get(Calendar.MONTH)+1;
	}
	
	public static int getDiaActual(){
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}
	
	
	public static String getObtenerRutaCarpetas(){
		return  "documento"+ System.getProperty("file.separator")+ Util.getAnioActual() 
     	+ System.getProperty("file.separator")+ Util.getMesActual()  + System.getProperty("file.separator") + Util.getDiaActual() +System.getProperty("file.separator");
	}
	
}
