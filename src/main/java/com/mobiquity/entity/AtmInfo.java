package com.mobiquity.entity;

import java.util.List;

public class AtmInfo {

	AtmAddress address ;
	List<AtmOpeningHours> openingHoursList;
	String functionality;
	String type;
	long distance;
	
	public AtmAddress getAddress() {
		return address;
	}
	public void setAddress(AtmAddress address) {
		this.address = address;
	}
	
	
	public List<AtmOpeningHours> getOpeningHoursList() {
		return openingHoursList;
	}
	public void setOpeningHoursList(List<AtmOpeningHours> openingHoursList) {
		this.openingHoursList = openingHoursList;
	}
	public String getFunctionality() {
		return functionality;
	}
	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getDistance() {
		return distance;
	}
	public void setDistance(long distance) {
		this.distance = distance;
	}
	
	
}
