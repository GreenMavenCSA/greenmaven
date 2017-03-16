package com.sylvanaqua.farmhacker.catalog.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jooq.tools.json.JSONObject;

@XmlRootElement
public class InventoryItem {

	@XmlElement(name="id")
	private int id;
	
	@XmlElement(name="catalog_id")
	private int catalogId;
	
	@XmlElement(name="measure")
	private double measure;
	
	@XmlElement(name="time_entered")
	private Date timeEntered;
	
	public InventoryItem(int id, int catalogId, double measure) {
		this.id = id;
		this.catalogId = catalogId;
		this.measure = measure;
		this.timeEntered = new Date();
	}
	
	@Override
	public String toString() {
		try{
			JSONObject entryAsJSON = new JSONObject();
			entryAsJSON.put("id", id);
			entryAsJSON.put("category_id", catalogId);
			entryAsJSON.put("measure", measure);;
			entryAsJSON.put("time_entered", timeEntered);
			
			return entryAsJSON.toString();
		}
		catch(Exception e){
			return null;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public double getMeasure() {
		return measure;
	}

	public void setMeasure(double measure) {
		this.measure = measure;
	}

	public Date getTimeEntered() {
		return timeEntered;
	}

	public void setTimeEntered(Date timeEntered) {
		this.timeEntered = timeEntered;
	}
	
	

}
