package com.sylvanaqua.farmhacker.core.service;

import java.sql.Connection;
import java.sql.DriverManager;

import com.sylvanaqua.farmhacker.core.database.DatabaseProperties;

public class ServiceBase {

	protected DatabaseProperties dbProperties;
	
	public ServiceBase(){
		dbProperties = DatabaseProperties.getInstance();
	}
	
	public Connection getConnection() throws Exception {
		
		String userName = this.dbProperties.getDatabaseUser();
        String password = this.dbProperties.getDatabasePassword();
        String url = this.dbProperties.getDatabaseUrl();
        Class.forName(this.dbProperties.getDatabaseDriverClass()).newInstance();
        
        return DriverManager.getConnection(url, userName, password);
	}
}
