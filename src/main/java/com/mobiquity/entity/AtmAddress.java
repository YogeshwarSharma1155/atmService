package com.mobiquity.entity;

public class AtmAddress {

	
	String street;
	String houseNumber;
	String postalcode;
	String city;
	AtmGeoLocation geoLocation;
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public AtmGeoLocation getGeoLocation() {
		return geoLocation;
	}
	public void setGeoLocation(AtmGeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}
	
	
	
}
