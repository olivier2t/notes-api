package com.agendup.yoda;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import com.agendup.yoda.cassandra.dao.ApiRequest;
import com.agendup.yoda.config.ApiConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	private final static Logger logger = LoggerFactory.getLogger(Utils.class);

	private static ApiConfig config;

	/**
	 * This method can be used to copy all intersected properties from a class
	 * instance A into a new instance of a class B.
	 * 
	 * @param obja
	 *            Object A from which the properties are copied
	 * @param Class<?>
	 *            The class of the new instance where the properties are copied
	 * @return Object Object B after copying attribute values
	 */
	public static Object copyProperties(Object obja, Class<?> c) {
		if (obja == null)
			return null;

		Object objb;
		try {
			objb = c.newInstance();

		} catch (InstantiationException | IllegalAccessException e1) {
			logger.error("Failed to instantiate {}", c.getName(), e1);
			return null;
		}

		Method m[] = obja.getClass().getMethods();
		for (int i = 0; i < m.length; i++) {
			try {
				if ((m[i].getName().startsWith("get") || m[i].getName().startsWith("is"))
						&& !m[i].getName().equals("getClass")) {
					Class rtype = m[i].getReturnType();
					String setter = m[i].getName().replaceFirst("^(get|is)", "set");
					Class s = objb.getClass();
					Method method = s.getMethod(setter, rtype);
					Object[] args = new Object[1];
					args[0] = m[i].invoke(obja);
					method.invoke(objb, args[0]);
				}
			} catch (Exception e2) {
			}
		}
		return objb;
	}

	/**
	 * This method can be used to copy all intersected properties from a class
	 * instance A to a class instance B.
	 * 
	 * @param obja
	 *            Object A from which the properties are copied
	 * @param objb
	 *            Object B from which the properties are copied
	 * @return Object Object B after copying attribute values
	 */
	public static Object copyProperties(Object obja, Object objb) {
		if (obja == null || objb == null)
			return null;

		Method m[] = obja.getClass().getMethods();
		for (int i = 0; i < m.length; i++) {
			try {
				if ((m[i].getName().startsWith("get") || m[i].getName().startsWith("is"))
						&& !m[i].getName().equals("getClass")) {
					Class rtype = m[i].getReturnType();
					String setter = m[i].getName().replaceFirst("^(get|is)", "set");
					Class s = objb.getClass();
					Method method = s.getMethod(setter, rtype);
					Object[] args = new Object[1];
					args[0] = m[i].invoke(obja);
					method.invoke(objb, args[0]);
				}
			} catch (Exception e2) {
			}
		}
		return objb;
	}

	/**
	 * This method can be used to copy hidden Db properties from a class instance A
	 * to a class instance B.
	 * 
	 * @param obja
	 *            Object A from which the Db properties are copied
	 * @param objb
	 *            Object B from which the Db properties are copied
	 * @param c
	 *            API Class
	 * @return Object Object B after copying Db attribute values
	 */
	public static Object copyDbProperties(Object obja, Object objb, Class<?> c) {
		if (obja == null || objb == null)
			return null;

		boolean dbProp;
		Method mapis[] = c.getMethods();
		Method mdbs[] = obja.getClass().getMethods();
		for (Method getter : mdbs) {
			if ((getter.getName().startsWith("get") || getter.getName().startsWith("is"))
					&& !getter.getName().equals("getClass")) {
				dbProp = true;
				for (Method mapi : mapis) {
					if (getter.getName().equals(mapi.getName())) {
						dbProp = false;
					}
				}
				if (dbProp) {
					try {
						Class rtype = getter.getReturnType();
						String setterName = getter.getName().replaceFirst("^(get|is)", "set");
						Class s = objb.getClass();
						Method setter = s.getMethod(setterName, rtype);
						Object value = getter.invoke(obja);
						setter.invoke(objb, value);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
							| NoSuchMethodException | SecurityException e) {
						logger.error("Unexpected exception while copying Db properties using method {} on object {}",
								getter, objb, e);
					}
				}
			}
		}
		return objb;
	}

	/**
	 * This method can be used to check if a method returning a certain object type
	 * exists in a class.
	 * 
	 * @param c
	 *            Class from which the method is checked for existence.
	 * @param method
	 *            The method to be checked if it exists.
	 * @param ptype
	 *            Type of object returned by the method.
	 * @return boolean True if such method exists
	 */
	public static boolean isMethod(Class<?> c, String method, Class<?>... ptype) {
		try {
			c.getMethod(method, ptype);
			return true;
		} catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}

	/**
	 * This method returns a failed API Response for the error cases
	 * 
	 * @param code
	 *            HTTP code to return
	 * @param message
	 *            Message to return
	 * @return ApiRequest with response code and message
	 */
	@SuppressWarnings("rawtypes")
	public static ApiRequest getApiRequest(int code, String message) {
		ApiRequest request = new ApiRequest();
		request.setResponseCode(code);
		request.setResponseMessage(message);
		logger.debug("Returning API Response with HTTP {} {}", code, message);
		return request;
	}

	/**
	 * This method returns a new time based identifier
	 * 
	 * @return BigDecimal A new time based id
	 */
	public static BigDecimal newId() {
		BigDecimal id = new BigDecimal(System.currentTimeMillis())
				.subtract(new BigDecimal("1495491120000"))
				.multiply(new BigDecimal(1000))
				.add(new BigDecimal(new Random().nextInt(999)));
		return id;
	}

	public static String dateFormatter(Date date, String timezone, String format) {
		if (format == null)
			format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat dfm = new SimpleDateFormat(format);
		dfm.setTimeZone(TimeZone.getTimeZone(timezone));
		return dfm.format(date);
	}

	public static String dateFormatter(Date date, String timezone) {
		return dateFormatter(date, timezone, "yyyy-MM-dd HH:mm:ss");
	}
	 
	public static ApiConfig getConfig() {
		return config;
	}

	public static void setConfig(ApiConfig config) {
		Utils.config = config;
	}

}
