package com.sylvanaqua.farmhacker.useraccount.service;

import java.sql.Connection;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import com.sylvanaqua.farmhacker.core.security.SecurityUtil;
import com.sylvanaqua.farmhacker.core.service.ServiceBase;
import com.sylvanaqua.farmhacker.database.Farmhacker;
import com.sylvanaqua.farmhacker.database.tables.Catalog;
import com.sylvanaqua.farmhacker.database.tables.FarmhackerUser;
import com.sylvanaqua.farmhacker.database.tables.records.CatalogRecord;
import com.sylvanaqua.farmhacker.database.tables.records.FarmhackerUserRecord;
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
	 * 
	 * @return True if the account was created, false otherwise.
	 */
	public boolean createAccount(UserAccount accountInformation) throws Exception {
		
		boolean success = false;
		
		try (Connection conn = getConnection()) {
        	DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
        	
        	if(getUserAccount(accountInformation) == null) {
        		
        		if(accountInformation.getIsFacebookUser()==0) {
        			accountInformation.setPassword(SecurityUtil.hashPassword(accountInformation.getPassword()));
        		}
        		else {
        			// Make this null just in case someone throws in a password anyway.
        			accountInformation.setPassword(null);
        		}
        		
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
	        	
	        	success = true;
        	}
        	else {
        		success = false;
        	}
        	
        	return success;
        }
        catch (DataAccessException dae) {
            throw dae;
        }
		
	}
	
	/**
	 * Determines if the user exists in the database with the account
	 * information given.
	 * 
	 * @param accountInformation Username and password (hashed) to authenticate.
	 * @return UserAccount object with authenticated user info, or null if user not authenticated
	 * or user not found.
	 * @throws Exception
	 */
	public UserAccount authenticateAccount(UserAccount accountInformation) throws Exception {
		
    	UserAccount userToAuthenticate = getUserAccount(accountInformation);
    	
    	if(userToAuthenticate != null &&
    	   SecurityUtil.checkPassword(accountInformation.getPassword(), userToAuthenticate.getPassword())) {
    		return userToAuthenticate;
    	}
    	else {
    		return null;
    	}
	}
	
	/**
	 * Check to see if user account already exists, according to userid.
	 * 
	 * @param userAccount The user account to check for
	 * @throws Throwable
	 * @return UserAccount info for found user, null otherwise.
	 */
	public UserAccount getUserAccount(UserAccount userAccount) throws Exception {
		
		try (Connection conn = getConnection()) {
        	DSLContext search = DSL.using(conn, SQLDialect.MYSQL);
        	
        	Result<FarmhackerUserRecord> results =
	        	search.selectFrom(FarmhackerUser.FARMHACKER_USER)
	        	      .where(FarmhackerUser.FARMHACKER_USER.USERNAME.equalIgnoreCase(userAccount.getUserid()))
	        	      .fetch();
        	
        	if(results.size() == 1) {
        		FarmhackerUserRecord authenticatedDbRecord = results.get(0);
        		
        		UserAccount foundUserAccount = 
        				new UserAccount(authenticatedDbRecord.getUsername());
        		
        		foundUserAccount.setIsEater(authenticatedDbRecord.getIsEater());
        		foundUserAccount.setIsGrower(authenticatedDbRecord.getIsGrower());
        		foundUserAccount.setZip(authenticatedDbRecord.getZip());
        		foundUserAccount.setIsFacebookUser(authenticatedDbRecord.getIsFacebookUser());
        		foundUserAccount.setPassword(authenticatedDbRecord.getPassword());
        		
        		return foundUserAccount;
        	}
        	else {
        		return null;
        	}
        
		}
		catch(DataAccessException dae){
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
		
		/**
		 * Retrieve number of growers and eaters based on zip code.
		 * 
		 * @param userAccount The user account to check for
		 * @throws Throwable
		 * @return UserAccount info for found user, null otherwise.
		 */
			try (Connection conn = getConnection()) {
	        	DSLContext search = DSL.using(conn, SQLDialect.MYSQL);
	        	
	        	Record1<Integer> growersByZipCount =
	        			DSL.selectCount()
	        			   .from(FarmhackerUser.FARMHACKER_USER)
	        			   .where(FarmhackerUser.FARMHACKER_USER.ZIP.equal(zip))
	        			   .and(FarmhackerUser.FARMHACKER_USER.IS_GROWER.equal(1))
	        			   .fetchOne();
	        	
	        	Record1<Integer> eatersByZipCount =
	        			DSL.selectCount()
	        			.from(FarmhackerUser.FARMHACKER_USER)
	        			.where(FarmhackerUser.FARMHACKER_USER.ZIP.equal(zip))
	        			.and(FarmhackerUser.FARMHACKER_USER.IS_EATER.equal(1))
	        			.fetchOne();
	        	
	        	int numGrowers = growersByZipCount.value1();
	        	int numEaters = eatersByZipCount.value1();
	        	
	        	
	        	UserTally userTally = new UserTally(numGrowers, numEaters, zip);
	        	
	        	return userTally;
	        	
			}
			catch(DataAccessException dae){
				throw dae;
			}
	
	}
}
