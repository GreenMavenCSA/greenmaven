package com.sylvanaqua.farmhacker.useraccount.entity;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONObject;

@XmlRootElement
public class UserTally {

	@XmlElement(name="num_growers")
	private int numGrowers;
	
	@XmlElement(name="num_eaters")
	private int numEaters;
	
	@XmlElement(name="zip")
	private int zip;
	
	@XmlElement(name="market")
	private MarketEntity market;
	
	public UserTally(int numGrowers, int numEaters, int zip, MarketEntity market) {
		
		this.numGrowers = numGrowers;
		this.numEaters = numEaters;
		this.zip = zip;
		this.market = market;
		
	}

	public int getNumGrowers() {
		return numGrowers;
	}

	public void setNumGrowers(int numGrowers) {
		this.numGrowers = numGrowers;
	}

	public int getNumEaters() {
		return numEaters;
	}

	public void setNumEaters(int numEaters) {
		this.numEaters = numEaters;
	}
	
	public int getZip() {
		return zip;
	}
	
	public void setZip(int zip) {
		this.zip = zip;
	}
	

	@Override
	public String toString(){
		try{
			JSONObject entryAsJSON = new JSONObject();
			entryAsJSON.put("num_eaters", numEaters);
			entryAsJSON.put("num_growers", numGrowers);
			entryAsJSON.put("zip", zip);
			entryAsJSON.put("market_id", market.getMarketId());
			entryAsJSON.put("market_name", market.getMarketName());
			
			return entryAsJSON.toString();
		}
		catch(Exception e){
			return null;
		}
	}
	
}
