package com.sylvanaqua.farmhacker.useraccount.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jooq.tools.json.JSONObject;

@XmlRootElement
public class UserAccount {

	@XmlElement(name="userid")
	private String userid;
	
	@XmlElement(name="password")
	private String password;
	
	@XmlElement(name="is_eater")
	private int isEater;
	
	@XmlElement(name="is_grower")
	private int isGrower;
	
	@XmlElement(name="is_facebook_user")
	private int isFacebookUser;
	
	@XmlElement(name="zip")
	private int zip;
	
	public UserAccount(String userid) {
		this.userid = userid;
	}
	
	public UserAccount(String userid, String password) {
		this.userid = userid;
		this.password = password;
	}
	
	public UserAccount(String userid, String password, int isEater, int isGrower,
			           int isFacebookUser, int zip) {
		
		this.userid = userid;
		this.password = password;
		this.isEater = isEater;
		this.isGrower = isGrower;
		this.isFacebookUser = isFacebookUser;
		this.zip = zip;
	}
	
	@Override
	public String toString(){
		try{
			JSONObject entryAsJSON = new JSONObject();
			entryAsJSON.put("userid", userid);
			entryAsJSON.put("password", password);
			entryAsJSON.put("is_eater", isEater);
			entryAsJSON.put("is_grower", isGrower);
			entryAsJSON.put("is_facebook_user", isFacebookUser);
			entryAsJSON.put("zip", zip);
			
			return entryAsJSON.toString();
		}
		catch(Exception e){
			return null;
		}
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsEater() {
		return isEater;
	}

	public void setIsEater(int isEater) {
		this.isEater = isEater;
	}

	public int getIsGrower() {
		return isGrower;
	}

	public void setIsGrower(int isGrower) {
		this.isGrower = isGrower;
	}

	public int getIsFacebookUser() {
		return isFacebookUser;
	}

	public void setIsFacebookUser(int isFacebookUser) {
		this.isFacebookUser = isFacebookUser;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
	
	
}