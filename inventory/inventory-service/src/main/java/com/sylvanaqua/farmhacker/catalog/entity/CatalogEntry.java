package com.sylvanaqua.farmhacker.catalog.entity;

public class CatalogEntry {

	private String category;
	private String name;
	
	public CatalogEntry(String category, String name){
		this.category = category;
		this.name = name;
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
	
	
}
