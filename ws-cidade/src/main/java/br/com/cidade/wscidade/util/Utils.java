package br.com.cidade.wscidade.util;

import java.math.BigDecimal;
import java.util.Collection;

public class Utils {

	private static final String STRING_EMPTY = "";

	public static boolean isNull(Object object) {
		return object == null;
	}

	public static boolean isEmptyOrNull(String pSString) {

		return (pSString == null || pSString.trim().equals(STRING_EMPTY));
	}

	public static boolean isEmptyOrNull(Object object) {
		boolean retorno = false;

		if (isNull(object)) {
			return true;
		}

		if (object instanceof Collection) {
			retorno = ((Collection<?>) object).isEmpty();
		}

		else if (object instanceof String) {
			retorno = object.toString().trim().equals(STRING_EMPTY);
		}

		return retorno;
	}

	public static Long convertToLong(Object param) {

		try {
			return Long.valueOf(param.toString());
		} catch (Exception e) {
			throw new RuntimeException("Não converte para Long");
		}

	}

	public static Boolean convertToBoolean(Object param) {
		
		if (isEmptyOrNull(param)) {
			return Boolean.FALSE;
		} else if(!param.toString().toLowerCase().contains("true")) {
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	public static BigDecimal convertToBigDecimal(Object param) {

		try {
			return new BigDecimal(param.toString());
		} catch (Exception e) {
			throw new RuntimeException("Não converte para BigDecimal");
		}
		
	}
	
	public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			if (unit.equals("K")) {
				dist = dist * 1.609344;
			} else if (unit.equals("N")) {
				dist = dist * 0.8684;
			}
			return (dist);
		}
	}	

}
