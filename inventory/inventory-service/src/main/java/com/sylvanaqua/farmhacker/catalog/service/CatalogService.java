package com.sylvanaqua.farmhacker.catalog.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import com.sylvanaqua.farmhacker.catalog.entity.CatalogEntry;
import com.sylvanaqua.farmhacker.database.tables.Catalog;
import com.sylvanaqua.farmhacker.database.tables.records.CatalogRecord;

public class CatalogService {

	/**
	 * Default constructor.
	 */
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
        	create.insertInto(Catalog.CATALOG, Catalog.CATALOG.CATEGORY, 
        			          Catalog.CATALOG.NAME, Catalog.CATALOG.RETAIL_PRICE,
        			          Catalog.CATALOG.WHOLESALE_PRICE)
        		  .values(catalogEntry.getCategory(), catalogEntry.getName(),
        				  catalogEntry.getRetailPrice(), catalogEntry.getWholesalePrice())
        		  .execute();
        }
        catch (DataAccessException dae) {
            throw dae;
        }
	}
	
	/**
	 * Returns all catalog items where the name or category of the catalog
	 * item partially match the search string.
	 * 
	 * @param searchString Search string
	 * @return List of catalog entries matcing the search string.
	 * @throws Exception
	 */
	public List<CatalogEntry> searchEntries(String searchString) throws Exception {
		
		String userName = "root";
        String password = "F@rmhack3r";
        String url = "jdbc:mysql://localhost:3306/farmhacker";
        Class.forName("com.mysql.jdbc.Driver");
        
        List<CatalogEntry> searchResultsAsTransferObjects = new ArrayList<CatalogEntry>();
        
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
        	DSLContext search = DSL.using(conn, SQLDialect.MYSQL);
        	
        	Result<CatalogRecord> results = 
	        	search.selectFrom(Catalog.CATALOG)
	        		  .where(Catalog.CATALOG.NAME.like(appendLikeString(searchString))
	        	      .or(Catalog.CATALOG.CATEGORY.like(appendLikeString(searchString))))
	        		  .fetch();
        
        	for(CatalogRecord result : results){
        		CatalogEntry catalogEntry = 
        				new CatalogEntry(result.getCategory(), result.getName(), 
        						         result.getRetailPrice(), result.getWholesalePrice());
        		
        		searchResultsAsTransferObjects.add(catalogEntry);
        	}
        	
        	return searchResultsAsTransferObjects;
        	
        }
        catch (DataAccessException dae) {
            throw dae;
        }
		
	}
	
	/**
	 * Adds % before and after a string for database LIKE search clauses
	 * 
	 * @param searchString
	 * @return null if the searchString is null
	 */
	private String appendLikeString(String searchString){
		
		return searchString == null ? searchString : ("%"+searchString+"%");
	}
}
