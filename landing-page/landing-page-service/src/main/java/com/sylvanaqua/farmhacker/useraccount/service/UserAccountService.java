package com.sylvanaqua.farmhacker.useraccount.service;

import java.sql.Connection;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import com.sylvanaqua.farmhacker.core.security.SecurityUtil;
import com.sylvanaqua.farmhacker.core.service.ServiceBase;
import com.sylvanaqua.farmhacker.database.Farmhacker;
import com.sylvanaqua.farmhacker.database.tables.Catalog;
import com.sylvanaqua.farmhacker.database.tables.FarmhackerUser;
import com.sylvanaqua.farmhacker.database.tables.Market;
import com.sylvanaqua.farmhacker.database.tables.MarketZip;
import com.sylvanaqua.farmhacker.database.tables.records.CatalogRecord;
import com.sylvanaqua.farmhacker.database.tables.records.FarmhackerUserRecord;
import com.sylvanaqua.farmhacker.database.tables.records.MarketRecord;
import com.sylvanaqua.farmhacker.useraccount.entity.MarketEntity;
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
	        					  FarmhackerUser.FARMHACKER_USER.ZIP,
	        					  FarmhackerUser.FARMHACKER_USER.MARKET_ID)
	        		  .values(accountInformation.getUserid(), 
	        				  accountInformation.getPassword(),
	        				  accountInformation.getIsEater(), 
	        				  accountInformation.getIsGrower(),
	        				  accountInformation.getIsFacebookUser(),
	        				  accountInformation.getZip(),
	        				  accountInformation.getMarketId())
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
	 * Returns a JSON object with the number of growers and eaters in the
	 * market serviced by the given zip code.
	 * 
	 * @param zip The zip code to return results for.
	 * @return JSON object with num_growers and num_eaters properties
	 * 
	 * @throws Exception
	 */
	public UserTally getNumberOfGrowersOrEatersForZipCode(int zip) throws Exception {
		
		try (Connection conn = getConnection()) {
			
			UserTally userTally = null;
			int numGrowers = 0;
			int numEaters = 0;
			
        	DSLContext search = DSL.using(conn, SQLDialect.MYSQL);
        	Result<Record2<Integer, String>> marketRecord = 
        		search.select(Market.MARKET.ID, Market.MARKET.MARKET_NAME)
        		       .from(Market.MARKET.join(MarketZip.MARKET_ZIP)
        				                       .on(Market.MARKET.ID.equal(MarketZip.MARKET_ZIP.MARKET_ID)))
        		      .where(MarketZip.MARKET_ZIP.POSTAL_CODE.equal(zip))
        		      .fetch();
        	
        	if(marketRecord != null && marketRecord.size() > 0) {
        		Record2<Integer, String> foundMarket = marketRecord.get(0);
        		Integer marketID = foundMarket.value1();
        		String marketName = foundMarket.value2();
        		
	        	Record1<Integer> growersByZipsMarketCount =
	        			search.selectCount()
	        			      .from(FarmhackerUser.FARMHACKER_USER)
	        			      .where(FarmhackerUser.FARMHACKER_USER.MARKET_ID.equal(marketID))
	        			      .and(FarmhackerUser.FARMHACKER_USER.IS_GROWER.equal(1))
	        			      .fetchOne();
	        	
	        	Record1<Integer> eatersByZipsMarketCount =
	        			search.selectCount()
	        			      .from(FarmhackerUser.FARMHACKER_USER)
	        		     	  .where(FarmhackerUser.FARMHACKER_USER.MARKET_ID.equal(marketID))
	        			      .and(FarmhackerUser.FARMHACKER_USER.IS_EATER.equal(1))
	        			      .fetchOne();
	        	
	        	MarketEntity market = new MarketEntity(marketID, marketName);
	        	numGrowers = growersByZipsMarketCount.value1();
	        	numEaters = eatersByZipsMarketCount.value1();
	        	
	        	userTally = new UserTally(numGrowers, numEaters, zip, market);
        	}
        	else {
        		userTally = new UserTally(0, 0, zip, new MarketEntity(-1, ""));
        	}
        	
        	return userTally;
        	
		}
		catch(DataAccessException dae){
			throw dae;
		}
	
	}
}
