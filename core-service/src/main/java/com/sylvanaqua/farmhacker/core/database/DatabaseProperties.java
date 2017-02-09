package com.sylvanaqua.farmhacker.core.database;

import java.util.Properties;

import org.bitbucket.krausening.Krausening;

public class DatabaseProperties {

	private static DatabaseProperties instance;
	
	private String databaseUrl;
	private String databaseUser;
	private String databasePassword;
	private String databaseDriverClass;
	
	DatabaseProperties(){
		
		Krausening krausening = Krausening.getInstance();
		Properties properties = krausening.getProperties("farmhacker.properties");
		
		databaseUrl = properties.getProperty("database.url");
		databaseUser = properties.getProperty("database.user");
		databasePassword = properties.getProperty("database.password");
		databaseDriverClass = properties.getProperty("database.driver.class");
	}
	
	public static DatabaseProperties getInstance() {
		
		if(instance == null) {
			instance = new DatabaseProperties();
		}
		
		return instance;
	}

	public String getDatabaseUrl() {
		return databaseUrl;
	}

	public void setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}

	public String getDatabaseUser() {
		return databaseUser;
	}

	public void setDatabaseUser(String databaseUser) {
		this.databaseUser = databaseUser;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}

	public String getDatabaseDriverClass() {
		return databaseDriverClass;
	}

	public void setDatabaseDriverClass(String databaseDriverClass) {
		this.databaseDriverClass = databaseDriverClass;
	}

	public static void setInstance(DatabaseProperties instance) {
		DatabaseProperties.instance = instance;
	}
	
	
}
