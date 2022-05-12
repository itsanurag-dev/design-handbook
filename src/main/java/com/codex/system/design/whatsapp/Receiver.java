package com.codex.system.design.whatsapp;

public class Receiver extends SystemUser{
	int userId;
	String name;
	String City;
	String Country;
	// getter setter
	
	public boolean isReceiverAvailable(Receiver receiver, String msg) {
		
		return true;
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

}
