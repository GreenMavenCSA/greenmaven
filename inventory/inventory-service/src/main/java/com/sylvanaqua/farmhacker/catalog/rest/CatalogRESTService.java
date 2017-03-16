package com.sylvanaqua.farmhacker.catalog.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.JSONObject;

import com.sylvanaqua.farmhacker.catalog.entity.CatalogEntry;
import com.sylvanaqua.farmhacker.catalog.entity.InventoryCode;
import com.sylvanaqua.farmhacker.catalog.entity.InventoryItem;
import com.sylvanaqua.farmhacker.catalog.service.CatalogService;
import com.sylvanaqua.farmhacker.core.rest.RESTServiceBase;

@Path("/catalogService")
public class CatalogRESTService extends RESTServiceBase {

	/**
	 * Create product catalog from category and name.
	 * 
	 * Ex: http://localhost:8080/inventory/rest/catalogService/create?category=pork&name=Tenderloin
	 * 
	 * @param category Catalog entry category
	 * @param name Catalog entry name
	 * @return
	 */
	@GET
	@Produces("application/json")
	@Path("/create")
	public String createCatalogEntry(@QueryParam("category") String category, 
					       		   	 @QueryParam("name") String name,
					       		   	 @QueryParam("retailPrice") String retailPrice,
					       		   	 @QueryParam("wholesalePrice") String wholesalePrice ) {

		JSONObject response = new JSONObject();
		
		CatalogEntry catalogEntry = 
				new CatalogEntry(0, category, name, Double.parseDouble(retailPrice),
						         Double.parseDouble(wholesalePrice), 0);
		
		CatalogService catalogService = new CatalogService();
		
		try{
			if(catalogService.createCatalogEntry(catalogEntry)){
				response.put("result", 0);
				response.put("message", "Catalog entry created!");
			}
			else{
				response.put("result", 1);
				response.put("message", "This catalog entry already exists.");
			}
		}
		catch(Exception e){
			logException(e);
		}
		
		return response.toString();

	}
	
	/**
	 * Create inventory item
	 * 
	 * @param categoryId Category for the inventory item being created
	 * @param measure Measure (usually weight) of item
	 * @return
	 */
	@GET
	@Produces("application/json")
	@Path("/createInventory")
	public String createInventoryItem(@QueryParam("catalog_id") Integer catalogId, 
									  @QueryParam("measure") Double measure) {
		
		JSONObject response = new JSONObject();
		
		InventoryItem inventoryItem = 
				new InventoryItem(0, catalogId.intValue(), measure.doubleValue());
		
		CatalogService catalogService = new CatalogService();
		
		try{
			InventoryCode newInventoryItemCode = catalogService.createInventoryItem(inventoryItem);
			
			if(newInventoryItemCode != null){
				response.put("result", 0);
				response.put("message", "Inventory item created!");
				response.put("code", newInventoryItemCode.getCode());
			}
			else{
				response.put("result", 1);
				response.put("message", "There was an error creating this inventory item.");
			}
		}
		catch(Exception e){
			logException(e);
			response.put("result", 1);
			response.put("message", "There was an error creating this inventory item.");
		}
		
		return response.toString();
	}
	
	/**
	 * Return all catalog entries matching the search string in either the catalog
	 * item's category or name.
	 *
	 * Ex: http://localhost:8080/inventory/rest/catalogService/getCatalogEntries?searchString=Tend
	 *
	 * @param searchString The substring to search
	 * @return
	 */
	@GET
	@Produces("application/json")
	@Path("/getCatalogEntries")
	public String getCatalogEntries(@QueryParam("searchString") String searchString) {

		CatalogService catalogService = new CatalogService();
		List<CatalogEntry> catalogEntries = new ArrayList<CatalogEntry>();
		
		try{
			catalogEntries =
					catalogService.searchEntries(searchString.equalsIgnoreCase("all") ? "" : 
						                         searchString);
			
		}
		catch(Exception e){
			logException(e);
		}
		
		return catalogEntries.toString();
	}


	@GET
    @Path("ping")
    public String getServerTime() {
        System.out.println("RESTful Service 'catalogService' is running ==> ping");
        return "received ping on "+ new Date().toString();
    }
	
}
