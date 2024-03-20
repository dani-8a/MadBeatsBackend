package com.madbeats.model;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;

//@Document
public class Spot {
	
	//@Id
	private int idSpot;
	//@Field
	private String nameSpot;
	//@Field
	private String addressSpot;
	//@Field
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

	public String toString() {
		
		return "Spot{" +
				"idSpot=" + idSpot +
				", nameSpot='" + nameSpot + '\'' +
				", addressSpot='" + addressSpot + '\'' +
				", urlSpot='" + urlSpot + '\'' +
				'}';
	}
}
