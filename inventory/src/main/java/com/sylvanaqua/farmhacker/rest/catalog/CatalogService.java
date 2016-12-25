package com.sylvanaqua.farmhacker.rest.catalog;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/catalogService")
public class CatalogService  {

	@GET
    @Path("ping")
    public String getServerTime() {
        System.out.println("RESTful Service 'catalogService' is running ==> ping");
        return "received ping on "+ new Date().toString();
    }
	
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {

		String output = "Jersey say : " + msg;

		return Response.status(200).entity(output).build();

	}

}
