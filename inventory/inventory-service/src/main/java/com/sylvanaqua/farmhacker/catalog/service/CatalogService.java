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

public class CatalogService extends ServiceBase {

	/**
	 * Default constructor.
	 */
	public CatalogService(){
		super();
	}
	
	/**
	 * Create a new entry in the product catalog. Returns true if the item was created,
	 * false if not created because of a duplicate item.
	 * 
	 * @param catalogEntry Catalog entity from client
	 * @throws Exception
	 */
	public boolean create(CatalogEntry catalogEntry) throws Exception {
		
		boolean success = false;
		
        try (Connection conn = getConnection()) {
        	DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
        	
        	if(!catalogEntryExists(catalogEntry)){
	        	create.insertInto(Catalog.CATALOG, Catalog.CATALOG.CATEGORY, 
	        			          Catalog.CATALOG.NAME, Catalog.CATALOG.RETAIL_PRICE,
	        			          Catalog.CATALOG.WHOLESALE_PRICE)
	        		  .values(catalogEntry.getCategory(), catalogEntry.getName(),
	        				  catalogEntry.getRetailPrice(), catalogEntry.getWholesalePrice())
	        		  .execute();
	        	
	        	success = true;
        	}
        	else{
        		success = false;
        	}
        	
        	return success;
        
        }
        catch (DataAccessException dae) {
            throw dae;
        }
	}
	
	/**
	 * Check if the catalog entry exists in the database. Returns true if so,
	 * false otherwise.
	 * 
	 * @param entry The catalog entry to check for existence
	 * @throws Throwable
	 */
	public boolean catalogEntryExists(CatalogEntry entry) throws Exception {
		
		try (Connection conn = getConnection()) {
        	DSLContext search = DSL.using(conn, SQLDialect.MYSQL);
        	
        	Result<CatalogRecord> results =
	        	search.selectFrom(Catalog.CATALOG)
	        	      .where(Catalog.CATALOG.NAME.equalIgnoreCase(entry.getName())
	        	      .and(Catalog.CATALOG.CATEGORY.equalIgnoreCase(entry.getCategory())))
	        	      .fetch();
        	
        	return (results.size() > 0);
        	      
		}
		catch(DataAccessException dae){
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
		
        List<CatalogEntry> searchResultsAsTransferObjects = new ArrayList<CatalogEntry>();
        
        try (Connection conn = getConnection()) {
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
