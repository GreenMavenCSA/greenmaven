package com.sylvanaqua.farmhacker.catalog.service;

import java.sql.Connection;
import java.sql.DriverManager;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import com.sylvanaqua.farmhacker.catalog.entity.CatalogEntry;
import com.sylvanaqua.farmhacker.database.tables.Catalog;

public class CatalogService {

	public CatalogService(){
		
	}
	
	/**
	 * Create a new entry in the product catalog.
	 * TODO: This needs to be refactored to get some sanity around creating the
	 * connection and the context object.
	 * 
	 * @param catalogEntry Catalog entity from client
	 * @throws Exception
	 */
	public void create(CatalogEntry catalogEntry) throws Exception {
		
        String userName = "root";
        String password = "F@rmhack3r";
        String url = "jdbc:mysql://localhost:3306/farmhacker";
        Class.forName("com.mysql.jdbc.Driver");
        
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
        	DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
        	create.insertInto(Catalog.CATALOG, Catalog.CATALOG.CATEGORY, Catalog.CATALOG.NAME)
        		  .values(catalogEntry.getCategory(), catalogEntry.getName())
        		  .execute();
        }
        catch (DataAccessException dae) {
            throw dae;
        }
	}
	
	
}
