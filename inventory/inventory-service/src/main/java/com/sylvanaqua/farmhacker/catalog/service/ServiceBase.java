package com.sylvanaqua.farmhacker.catalog.service;

import java.sql.Connection;
import java.sql.DriverManager;

import com.sylvanaqua.farmhacker.database.DatabaseProperties;

public class ServiceBase {

	protected DatabaseProperties dbProperties;
	
	ServiceBase(){
		dbProperties = DatabaseProperties.getInstance();
	}
	
	protected Connection getConnection() throws Exception {
		
		String userName = this.dbProperties.getDatabaseUser();
        String password = this.dbProperties.getDatabasePassword();
        String url = this.dbProperties.getDatabaseUrl();
        Class.forName(this.dbProperties.getDatabaseDriverClass());
        
        return DriverManager.getConnection(url, userName, password);
	}
}
