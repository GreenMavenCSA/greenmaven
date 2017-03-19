package com.sylvanaqua.farmhacker.catalog.service;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import com.sylvanaqua.farmhacker.catalog.entity.CatalogEntry;
import com.sylvanaqua.farmhacker.catalog.entity.InventoryCode;
import com.sylvanaqua.farmhacker.catalog.entity.InventoryItem;
import com.sylvanaqua.farmhacker.core.id.InventoryIDGenerator;
import com.sylvanaqua.farmhacker.core.service.ServiceBase;
import com.sylvanaqua.farmhacker.database.tables.records.CatalogInventoryVwRecord;
import com.sylvanaqua.farmhacker.database.tables.records.CatalogRecord;

import static com.sylvanaqua.farmhacker.database.tables.Catalog.CATALOG;
import static com.sylvanaqua.farmhacker.database.tables.CatalogInventoryVw.CATALOG_INVENTORY_VW;
import static com.sylvanaqua.farmhacker.database.tables.Inventory.INVENTORY;

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
	public boolean createCatalogEntry(CatalogEntry catalogEntry) throws Exception {
		
		boolean success = false;
		
        try (Connection conn = getConnection()) {
        	DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
        	
        	if(!catalogEntryExists(catalogEntry)){
	        	create.insertInto(CATALOG, CATALOG.CATEGORY, 
	        			          CATALOG.NAME, CATALOG.RETAIL_PRICE,
	        			          CATALOG.WHOLESALE_PRICE)
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
	        	search.selectFrom(CATALOG)
	        	      .where(CATALOG.NAME.equalIgnoreCase(entry.getName())
	        	      .and(CATALOG.CATEGORY.equalIgnoreCase(entry.getCategory())))
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
        	
        	Result<CatalogInventoryVwRecord> results = 
	        	search.selectFrom(CATALOG_INVENTORY_VW)
	        		  .where(CATALOG_INVENTORY_VW.NAME.like(appendLikeString(searchString))
	        	      .or(CATALOG_INVENTORY_VW.CATEGORY.like(appendLikeString(searchString))))
	        		  .fetch();
        
        	for(CatalogInventoryVwRecord result : results){
        		CatalogEntry catalogEntry = 
        				new CatalogEntry(result.getId(),
        								 result.getCategory(), result.getName(), 
        						         result.getRetailPrice(), result.getWholesalePrice(),
        						         result.getUnitsAvailable());
        		
        		searchResultsAsTransferObjects.add(catalogEntry);
        	}
        	
        	return searchResultsAsTransferObjects;
        	
        }
        catch (DataAccessException dae) {
            throw dae;
        }
		
	}
	
	/**
	 * Creates an inventory item in the database.
	 * 
	 * @param inventoryItem The inventory item to create.
	 * @return The new inventory code if the item was successfully created, null otherwise.
	 */
	public InventoryCode createInventoryItem(InventoryItem inventoryItem) throws Exception {
		
        try (Connection conn = getConnection()) {
        	DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
        	
        	InventoryCode code = getNextInventoryCode();
        	
        	create.insertInto(INVENTORY, INVENTORY.CATALOG_ID, INVENTORY.MEASURE, 
        					  INVENTORY.TIME_ENTERED, INVENTORY.INVENTORY_CODE)
        		  .values(inventoryItem.getCatalogId(), inventoryItem.getMeasure(), 
        				  new Timestamp(inventoryItem.getTimeEntered().getTime()),
        				  code.getCode())
        		  .execute();
        	
        	return code;
        
        }
        catch (Exception e) {
            throw e;
        }
		
	}
	
	/**
	 * Gets the next inventory code based on the codes already in the system.
	 * 
	 * @return The next inventory code, or 0000 if this is the first code generated.
	 * @throws Exception
	 */
	private InventoryCode getNextInventoryCode() throws Exception {

		try (Connection conn = getConnection()){
			DSLContext search = DSL.using(conn, SQLDialect.MYSQL);

			Result<Record1<String>> result = 
				  search.select(INVENTORY.INVENTORY_CODE.max())
				  .from(INVENTORY)
				  .fetch();
			
			String latestCode = result.get(0).value1();
			
			if(latestCode == null) {
				return new InventoryCode("0000");
			}
			else {
				InventoryIDGenerator idGenerator = new InventoryIDGenerator(latestCode);
				InventoryCode newCode = new InventoryCode(idGenerator.getNextID());
				
				return newCode;
			}
		}
		catch (Exception e){
			throw e;
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
