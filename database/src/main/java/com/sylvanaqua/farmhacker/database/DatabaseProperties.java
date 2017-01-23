package com.sylvanaqua.farmhacker.database;

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

	public static DatabaseProperties getInstance(){

		if(instance == null){
			instance = new DatabaseProperties();
		}

		return instance;
	}

	public String getDatabaseUrl(){
		return this.databaseUrl;
	}
	public String getDatabaseUser(){
		return this.databaseUser;
	}
	public String getDatabasePassword(){
		return this.databasePassword;
	}
	public String getDatabaseDriverClass(){
		return this.databaseDriverClass;
	}

	public void setDatabaseUrl(String pDatabaseUrl){
		this.databaseUrl = pDatabaseUrl;
	}
	public void setDatabaseUser(String pDatabaseUser){
		this.databaseUser = pDatabaseUser;
	}
	public void setDatabasePassword(String pDatabasePassword){
		this.databasePassword = pDatabasePassword;
	}
	public void setDatabaseDriverClass(String pDatabaseDriverClass){
		this.databaseDriverClass = pDatabaseDriverClass;
	}
}