package org.jobeet.config;

import java.util.Properties;

public class AppConfig {
	private static Properties properties;

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public static void printProperties() {
		System.out.println("PROPERTIES="+properties.toString());
	}
	
	public static int getMaxTrabajosIndex(){
		return Integer.valueOf(properties.getProperty("app.max_trabajos_index")).intValue();
	}
	
	public static int getMaxTrabajosCategoria(){
		return Integer.valueOf(properties.getProperty("app.max_trabajos_categoria")).intValue();
	}
	
	public static int getDiasActivos(){
		return Integer.valueOf(properties.getProperty("app.dias_activos")).intValue();
	}
	
	public static int getDiasAdvExpiracion(){
		return Integer.valueOf(properties.getProperty("app.dias_advertencia_expiracion")).intValue();
	}
}
