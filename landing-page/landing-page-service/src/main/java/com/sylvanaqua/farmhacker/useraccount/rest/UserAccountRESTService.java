package com.sylvanaqua.farmhacker.useraccount.rest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.sylvanaqua.farmhacker.useraccount.entity.UserAccount;
import com.sylvanaqua.farmhacker.useraccount.entity.UserTally;
import com.sylvanaqua.farmhacker.useraccount.service.UserAccountService;

@Path("/accountService")
public class UserAccountRESTService {

	/**
	 * Create user account from SSO or email login info
	 * 
	 * @param userid User ID
	 * @param password Password
	 * @param isEater Flag user account type as eater
	 * @param isGrower Flag user account type as grower
	 * @param isFacebookUser User is logging in via Facebook SSO
	 * 
	 * @return
	 */
	@GET
	@Path("/create")
	public Response createAccount(@QueryParam("userid") String userId,
								  @QueryParam("password") String password,
								  @QueryParam("is_eater") int isEater,
								  @QueryParam("is_grower") int isGrower,
								  @QueryParam("is_facebook_user") int isFacebookUser,
								  @QueryParam("zip") int zip) {
		
		// TODO: Update this method to check for a userid that already
		// exists before creating a new one. Return a JSON object as a string
		// with a response code instead of a Response object.
		
		String output = "Account created!";
		
		UserAccount accountInformation = 
				new UserAccount(userId, password, isEater, isGrower, isFacebookUser, zip);
		
		UserAccountService userAccountService = new UserAccountService();
		
		try {
			userAccountService.createAccount(accountInformation);
		}
		catch (Exception e) {
			output = "Something went wrong...";
		}
		
		return Response.status(200).entity(output).build();
	}
	
	/**
	 * Returns the number of growers or eaters for a given zip code.
	 *
	 * Ex: http://localhost:8080/inventory/rest/accountService/getNumGrowersEaters?zip=22936
	 *
	 * @param zip The zip code for which to return the number of growers and eaters.
	 * @return
	 */
	@GET
	@Produces("application/json")
	@Path("/getNumGrowersEaters")
	public String getNumberOfGrowersOrEatersForZipCode(@QueryParam("zip") int zip) throws Exception {
		
		UserAccountService userAccountService = new UserAccountService();
		UserTally userTally = userAccountService.getNumberOfGrowersOrEatersForZipCode(zip);
		return userTally.toString();
	}
	
	/**
	 * Test method just to make sure the service app is running.
	 * 
	 * @return
	 */
	@GET
    @Path("ping")
    public String getServerTime() {
        System.out.println("RESTful Service 'accountService' is running ==> ping");
        return "received ping on "+ new Date().toString();
    }
								  
}
