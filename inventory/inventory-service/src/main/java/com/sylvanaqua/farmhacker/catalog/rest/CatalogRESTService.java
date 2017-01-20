package com.sylvanaqua.farmhacker.catalog.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.sylvanaqua.farmhacker.catalog.entity.CatalogEntry;
import com.sylvanaqua.farmhacker.catalog.service.CatalogService;

@Path("/catalogService")
public class CatalogRESTService  {

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
	@Path("/create")
	public Response createCatalogEntry(@QueryParam("category") String category, 
					       		   	   @QueryParam("name") String name,
					       		   	   @QueryParam("retailPrice") String retailPrice,
					       		   	   @QueryParam("wholesalePrice") String wholesalePrice ) {

		String output = "Catalog entry added!";
		
		CatalogEntry catalogEntry = 
				new CatalogEntry(category, name, Double.parseDouble(retailPrice),
						         Double.parseDouble(wholesalePrice));
		
		CatalogService catalogService = new CatalogService();
		
		try{
			catalogService.create(catalogEntry);
		}
		catch(Exception e){
			output = "Something went wrong...";
		}
		
		return Response.status(200).entity(output).build();

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
		catch(Exception e){} // Don't leave this here, dammit.
		
		return catalogEntries.toString();
	}

	@GET
    @Path("ping")
    public String getServerTime() {
        System.out.println("RESTful Service 'catalogService' is running ==> ping");
        return "received ping on "+ new Date().toString();
    }
	
}
