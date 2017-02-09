package com.sylvanaqua.farmhacker.useraccount.service;

import java.sql.Connection;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import com.sylvanaqua.farmhacker.core.service.ServiceBase;
import com.sylvanaqua.farmhacker.database.tables.FarmhackerUser;
import com.sylvanaqua.farmhacker.useraccount.entity.UserAccount;
import com.sylvanaqua.farmhacker.useraccount.entity.UserTally;

public class UserAccountService extends ServiceBase {

	/**
	 * Default constructor.
	 */
	public UserAccountService(){
		super();
	}
	
	/**
	 * Creates a new user account entry in the database.
	 * 
	 * @param accountInformation Includes email and password,
	 * or Facebook user id.
	 */
	public void createAccount(UserAccount accountInformation) throws Exception {
		
		// TODO: Check for existing account first; return false if the
		// userid already exists and true otherwise.
		
		try (Connection conn = getConnection()) {
        	DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
        	create.insertInto(FarmhackerUser.FARMHACKER_USER, 
        					  FarmhackerUser.FARMHACKER_USER.USERNAME,
        					  FarmhackerUser.FARMHACKER_USER.PASSWORD,
        					  FarmhackerUser.FARMHACKER_USER.IS_EATER,
        					  FarmhackerUser.FARMHACKER_USER.IS_GROWER,
        					  FarmhackerUser.FARMHACKER_USER.IS_FACEBOOK_USER,
        					  FarmhackerUser.FARMHACKER_USER.ZIP)
        		  .values(accountInformation.getUserid(), 
        				  accountInformation.getPassword(),
        				  accountInformation.getIsEater(), 
        				  accountInformation.getIsGrower(),
        				  accountInformation.getIsFacebookUser(),
        				  accountInformation.getZip())
        		  .execute();
        }
        catch (DataAccessException dae) {
            throw dae;
        }
		
	}
	
	/**
	 * Returns a JSON object with the number of growers and eaters in a
	 * given zip code.
	 * 
	 * @param zip The zip code to return results for.
	 * @return JSON object with num_growers and num_eaters properties
	 * 
	 * @throws Exception
	 */
	public UserTally getNumberOfGrowersOrEatersForZipCode(int zip) throws Exception {
		
		// TODO: GHatz - implement this here.
		return null;
	}
}
