package com.sylvanaqua.farmhacker.useraccount.rest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.sylvanaqua.farmhacker.core.rest.RESTServiceBase;
import com.sylvanaqua.farmhacker.core.security.SecurityUtil;
import com.sylvanaqua.farmhacker.useraccount.entity.UserAccount;
import com.sylvanaqua.farmhacker.useraccount.entity.UserTally;
import com.sylvanaqua.farmhacker.useraccount.service.UserAccountService;

@Path("/accountService")
public class UserAccountRESTService extends RESTServiceBase {

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
	public String createAccount(@QueryParam("userid") String userId,
								@QueryParam("password") String password,
								@QueryParam("is_eater") int isEater,
								@QueryParam("is_grower") int isGrower,
								@QueryParam("is_driver") int isDriver,
								@QueryParam("is_facebook_user") int isFacebookUser,
								@QueryParam("zip") int zip,
								@QueryParam("market_id") int marketId) {
		
		JSONObject response = new JSONObject();
		
		UserAccount accountInformation = 
				new UserAccount(userId, password, isEater, isGrower, isDriver, isFacebookUser, zip, marketId);
		
		UserAccountService userAccountService = new UserAccountService();
		
		try {
			if(userAccountService.createAccount(accountInformation)) {
				response.put("result", 0);
				response.put("message", "User account created!");
			}
			else {
				response.put("result", 1);
				response.put("message", "This account already exists.");
			}
		}
		catch (Exception e) {
			logException(e);
		}
		
		return response.toString();
	}
	
	/**
	 * Authenticates the given username and password against the database.
	 * 
	 * @param userId The user ID to authenticate
	 * @param password The provided plaintext password
	 * @return JSON object with authentication results (property: result)
	 */
	@GET
	@Path("/authenticate")
	public String authenticateUser(@QueryParam("userid") String userId,
								   @QueryParam("password") String password) {
		
		JSONObject response = new JSONObject();
		
		UserAccount accountInformation = new UserAccount(userId, password);
		UserAccountService userAccountService = new UserAccountService();
		
		try {
			UserAccount authenticatedAccount = 
					userAccountService.authenticateAccount(accountInformation);
			
			if(authenticatedAccount != null) {
				response.put("result", 0);
				response.put("userInfo", authenticatedAccount);
			}
			else {
				response.put("result", 1);
				response.put("userInfo", "");
			}
		}
		catch (Exception e) {
			logException(e);
		}
		
		return response.toString();
		
	}
	
	/**
	 * Returns the number of growers or eaters in the market serviced by a given
	 * zip code.
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
