package com.sylvanaqua.farmhacker.useraccount.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONObject;

@XmlRootElement
public class MarketEntity {

	@XmlElement(name="market_id")
	private int marketId;
	
	@XmlElement(name="market_name")
	private String marketName;

	public MarketEntity(int marketId, String marketName) {
		this.marketId = marketId;
		this.marketName = marketName;
	}
	
	@Override
	public String toString() {
		try{
			JSONObject entryAsJSON = new JSONObject();
			entryAsJSON.put("market_id", marketId);
			entryAsJSON.put("market_name", marketName);
			
			return entryAsJSON.toString();
		}
		catch(Exception e){
			return null;
		}
	}
	
	public int getMarketId() {
		return marketId;
	}

	public void setMarketId(int marketId) {
		this.marketId = marketId;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	
	
	
}
