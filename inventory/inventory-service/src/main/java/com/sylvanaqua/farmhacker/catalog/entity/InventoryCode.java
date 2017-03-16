package com.sylvanaqua.farmhacker.catalog.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jooq.tools.json.JSONObject;

@XmlRootElement
public class InventoryCode {

	public InventoryCode(String code) {
		this.code = code;
	}
	
	@XmlElement(name="code")
	private String code;

	public String getCode(){
		return this.code;
	}

	public void setCode(String code){
		this.code = code;
	}

	@Override
	public String toString(){
		try{
			JSONObject entryAsJSON = new JSONObject();
			entryAsJSON.put("code", code);
			
			return entryAsJSON.toString();
		}
		catch(Exception e){
			return null;
		}
	}
}