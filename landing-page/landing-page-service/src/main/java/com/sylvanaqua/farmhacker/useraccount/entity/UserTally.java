package com.sylvanaqua.farmhacker.useraccount.entity;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jooq.tools.json.JSONObject;

@XmlRootElement
public class UserTally {

	@XmlElement(name="numGrowers")
	private int numGrowers;
	
	@XmlElement(name="numEaters")
	private int numEaters;
	
	@XmlElement(name="zip")
	private int zip;
	
	public UserTally(int numGrowers, int numEaters, int zip) {
		
		// TODO Auto-generated constructor stub
		this.numGrowers = numGrowers;
		this.numEaters = numEaters;
		this.zip = zip;
		
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
	
	public int getZip(int zip) {
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
			
			return entryAsJSON.toString();
		}
		catch(Exception e){
			return null;
		}
	}
	
}
