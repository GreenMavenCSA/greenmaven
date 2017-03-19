package com.sylvanaqua.farmhacker.catalog.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jooq.tools.json.JSONObject;

@XmlRootElement
public class CatalogEntry {

	@XmlElement(name="id")
	private Integer id;
	
	@XmlElement(name="category")
	private String category;
	
	@XmlElement(name="name")
	private String name;
	
	@XmlElement(name="retailPrice")
	private double retailPrice;
	
	@XmlElement(name="wholesalePrice")
	private double wholesalePrice;

	@XmlElement(name="unitsAvailable")
	private long unitsAvailable;
	
	@Override
	public String toString(){
		try{
			JSONObject entryAsJSON = new JSONObject();
			entryAsJSON.put("id", id);
			entryAsJSON.put("category", category);
			entryAsJSON.put("name", name);;
			entryAsJSON.put("retailPrice", retailPrice);
			entryAsJSON.put("wholesalePrice", wholesalePrice);
			entryAsJSON.put("unitsAvailable", unitsAvailable);
			
			return entryAsJSON.toString();
		}
		catch(Exception e){
			return null;
		}
	}
	
	public CatalogEntry(Integer id, String category, String name, 
			            double retailPrice, double wholesalePrice, long unitsAvailable){
		this.id = id;
		this.category = category;
		this.name = name;
		this.retailPrice = retailPrice;
		this.wholesalePrice = wholesalePrice;
		this.unitsAvailable = unitsAvailable;
	}
	
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public double getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}
	
	public long getUnitsAvailable() {
		return unitsAvailable;
	}

	public void setUnitsAvailable(long unitsAvailable){
		this.unitsAvailable = unitsAvailable;
	}
	
}
