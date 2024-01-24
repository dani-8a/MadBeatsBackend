package com.madbeats.model;

public class Spot {
	
	private int idSpot;
	private String nameSpot;
	private String addressSpot;
	private String urlSpot;

	public Spot(int idSpot, String nameSpot, String addressSpot, String urlSpot) {
		
		this.idSpot = idSpot;
		this.nameSpot = nameSpot;
		this.addressSpot = addressSpot;
		this.urlSpot = urlSpot;
	}

	public int getIdSpot() {
		return idSpot;
	}

	public void setIdSpot(int idSpot) {
		this.idSpot = idSpot;
	}

	public String getNameSpot() {
		return nameSpot;
	}

	public void setNameSpot(String nameSpot) {
		this.nameSpot = nameSpot;
	}

	public String getAddressSpot() {
		return addressSpot;
	}

	public void setAddressSpot(String addressSpot) {
		this.addressSpot = addressSpot;
	}

	public String getUrlSpot() {
		return urlSpot;
	}

	public void setUrlSpot(String urlSpot) {
		this.urlSpot = urlSpot;
	}
}
